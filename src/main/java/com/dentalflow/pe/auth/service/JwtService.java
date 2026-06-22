package com.dentalflow.pe.auth.service;

import com.dentalflow.pe.auth.entity.Usuario;

public interface JwtService {
    String generateToken(Usuario usuario);
    String extractUsername(String token);
    boolean validateToken(String token);
}