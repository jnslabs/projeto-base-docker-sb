package io.jnslabs.sbbase.security;

import io.jnslabs.sbbase.exceptions.MyCustomException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:29
 */
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final IJwtTokenProviderService jwtTokenProviderService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProviderService.parseToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProviderService.validateToken(token)) {
                Authentication auth = jwtTokenProviderService.validateUserAndGetAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (MyCustomException ex) {
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
