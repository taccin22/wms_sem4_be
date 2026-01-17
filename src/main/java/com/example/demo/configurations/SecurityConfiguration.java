package com.example.demo.configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entities.Users;
import com.example.demo.helpers.DebugUsernamePasswordAuthFilter;
import com.example.demo.services.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserService userService;

	static {
		System.out.println("🔥 SECURITY CONFIG LOADED");
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	     return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.cors(c -> c.disable()).csrf(c -> c.disable())
				.sessionManagement(session -> session
			            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			        )
				.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/assets/**",
//																		"/wms_sem4/users/login",
						"/wms_sem4/users/loginProcess").permitAll()
						.requestMatchers("/wms_sem4/users/createCompanyStaff", "/wms_sem4/wms_sem4/products/**")
						.hasAnyAuthority("ROLE_PORTAL_ADMIN", "ROLE_COMPANY_ADMIN")
						.requestMatchers("/wms_sem4/users/**", "/wms_sem4/companies/**")
						.hasAnyAuthority("ROLE_PORTAL_ADMIN")
						.anyRequest().authenticated()
//												   .requestMatchers("/patient/**").hasAnyRole("PATIENT")
				).formLogin(form -> form
//									   .loginPage("/users/login")
						.loginProcessingUrl("/wms_sem4/users/loginProcess")
						.usernameParameter("username")
						.passwordParameter("password")
						.successHandler((request, response, authentication) -> {
							System.out.println("LOGIN SUCCESS");
							String username = authentication.getName();

							Users user = userService.findByUsername(username);

							HttpSession session = request.getSession();
							session.setAttribute("userId", user.getId());
							session.setAttribute("roleId", user.getRoles().getId());
							if (user.getCompanies() != null && user.getCompanies().getId() != null) {
								session.setAttribute("companyId", user.getCompanies().getId());
							}

							String accept = request.getHeader("Accept");

							if (accept != null && accept.contains("application/json")) {
								// Flutter / API
								response.setStatus(200);
								response.setContentType("application/json");
								response.getWriter().write("""
										{
										  "message": "Login successful",
										  "userId": %d,
										  "role": "%s"
										}
										""".formatted(user.getId(), user.getRoles().getRoleName()));

								response.flushBuffer();
							} else {
								// Spring MVC
//				                                Long roleId = user.getRoles().getId();
//				                                if (roleId == 1) {
//				                                    response.sendRedirect("/admin/dashboard");
//				                                } else if (roleId == 2) {
//				                                    response.sendRedirect("/company/dashboard");
//				                                } else {
//				                                    response.sendRedirect("/users/access-denied");
//				                                }
							}
						}).failureHandler((request, response, exception) -> {
							System.out.println("LOGIN FAILED");
							String accept = request.getHeader("Accept");

							if (accept != null && accept.contains("application/json")) {
								response.setStatus(401);
								response.getWriter().write("{\"error\":\"login failed\"}");
							} else {
//										        response.sendRedirect("/users/login?error");
							}
						}))
				// LOGOUT
				.logout(logout -> logout.logoutUrl("/users/logout")
						.logoutSuccessHandler((request, response, authentication) -> {
							response.setStatus(200);
						}))
				// ACCESS DENIED → 403
				.exceptionHandling(ex -> ex.accessDeniedHandler((request, response, accessDeniedException) -> {
					response.setStatus(403);
					response.setContentType("application/json");
					response.getWriter().write("""
							{ "error": "This function is not allowed for this account" }
							""");
				}).authenticationEntryPoint((request, response, authException) -> {
					System.out.println("LOGIN AUTH ENTRY");
					String accept = request.getHeader("Accept");
					if (accept != null && accept.contains("application/json")) {
						response.setStatus(401);
						response.setContentType("application/json");
						response.getWriter().write("""
								{ "error": "Authentication required" }
								""");
					}
				})).build();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService);
	}

	@Bean
	public Filter debugFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {

				System.out.println("INCOMING REQUEST: " + request.getMethod() + " " + request.getRequestURI());

				filterChain.doFilter(request, response);
			}
		};
	}

	@Bean
	public ApplicationRunner runner(ApplicationContext ctx) {
		return args -> {
			System.out.println("SECURITY CHAINS:");
			ctx.getBeansOfType(SecurityFilterChain.class).forEach((k, v) -> System.out.println(" - " + k));
		};
	}

}
