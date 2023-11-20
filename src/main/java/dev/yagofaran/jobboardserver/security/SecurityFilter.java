package dev.yagofaran.jobboardserver.security;

import dev.yagofaran.jobboardserver.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if (header != null) {
            var subject = this.jwtProvider.validateToken(header);
            if (subject.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            request.setAttribute("companyId", subject);
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}