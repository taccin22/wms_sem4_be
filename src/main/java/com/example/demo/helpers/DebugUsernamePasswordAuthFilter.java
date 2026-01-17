package com.example.demo.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DebugUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

@Override
public Authentication attemptAuthentication(
    HttpServletRequest request,
    HttpServletResponse response) {

System.out.println("AUTH FILTER TRIGGERED");
return super.attemptAuthentication(request, response);
}
}
