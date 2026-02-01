package com.example.demo.configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
				.addFilterBefore(debugFilter(), SecurityContextPersistenceFilter.class)
				/* ---------- API → NO CSRF ---------- */
				.csrf(csrf -> csrf.disable())

				/* ---------- CORS (adjust later if needed) ---------- */
				.cors(cors -> cors.disable())

				/* ---------- AUTHORIZATION ---------- */
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/assets/**","/wms_sem4/users/debug/auth", "/wms_sem4/users/login", "/wms_sem4/users/logout")
						.permitAll()
						.requestMatchers("/wms_sem4/products/createProduct", "/wms_sem4/products/updateProduct")
						.hasAnyAuthority("ROLE_COMPANY_ADMIN")
						.requestMatchers("/wms_sem4/users/createCompanyStaff", "/wms_sem4/products/**")
						.hasAnyAuthority("ROLE_PORTAL_ADMIN", "ROLE_COMPANY_ADMIN")
						.requestMatchers("/wms_sem4/users/find-by-username/**")
						.hasAnyAuthority("ROLE_COMPANY_ADMIN", "ROLE_COMPANY_STAFF")
						.requestMatchers("/wms_sem4/users/**", "/wms_sem4/companies/**")
						.hasAnyAuthority("ROLE_PORTAL_ADMIN").anyRequest().authenticated())

				/* ---------- SESSION MANAGEMENT ---------- */
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				
				/* ---------- AUTH STORAGE ---------- */
				.securityContext(securityContext ->
	            securityContext.securityContextRepository(
	                new HttpSessionSecurityContextRepository())
				)	
				/* ---------- EXCEPTION HANDLING ---------- */
				.exceptionHandling(ex -> ex

						/* Not authenticated → 401 */
						.authenticationEntryPoint((request, response, exAuth) -> {
							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							response.setContentType("application/json");
							response.getWriter().write("""
									    {
									      "error": "Authentication required"
									    }
									""");
						})

						/* Authenticated but forbidden → 403 */
						.accessDeniedHandler((request, response, exDenied) -> {
							response.setStatus(HttpServletResponse.SC_FORBIDDEN);
							response.setContentType("application/json");
							response.getWriter().write("""
									    {
									      "error": "Access denied"
									    }
									""");
						}))

				/* ---------- LOGOUT ---------- */
				.logout(logout -> logout.logoutUrl("/wms_sem4/users/logout").invalidateHttpSession(true)
						.deleteCookies("JSESSIONID").logoutSuccessHandler((req, res, auth) -> {
							res.setStatus(HttpServletResponse.SC_OK);
							res.setContentType("application/json");
							res.getWriter().write("""
									    {
									      "message": "Logout successful"
									    }
									""");
						}))

				/* ---------- NO formLogin ---------- */
				.build();
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
