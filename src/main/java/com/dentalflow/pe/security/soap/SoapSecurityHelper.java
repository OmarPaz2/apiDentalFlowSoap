package com.dentalflow.pe.security.soap;

import com.dentalflow.pe.auth.service.JwtService;
import com.dentalflow.pe.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoapSecurityHelper {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public void authenticate(String token) {

        if (token == null || token.isBlank()) {
            throw new RuntimeException("Token requerido");
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (!jwtService.validateToken(token)) {
            throw new RuntimeException("Token inválido");
        }

        String username = jwtService.extractUsername(token);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }

    public void clear() {
        SecurityContextHolder.clearContext();
    }
}