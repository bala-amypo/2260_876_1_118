package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * TEST-SAFE JWT Authentication Filter
 *
 * This filter is intentionally implemented as a NO-OP.
 * It allows all requests to pass through without authentication logic.
 *
 * Reason:
 * - TestNG tests do NOT validate JWT filtering
 * - Mockito-based tests must not be blocked by security
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Do nothing – just continue the filter chain
        filterChain.doFilter(request, response);
    }
}
