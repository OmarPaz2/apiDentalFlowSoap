package com.dentalflow.pe.auth.serviceImpl;

import com.dentalflow.pe.auth.entity.Rol;
import com.dentalflow.pe.auth.entity.Usuario;
import com.dentalflow.pe.auth.repository.IRolRepository;
import com.dentalflow.pe.auth.repository.IUsuarioRepository;
import com.dentalflow.pe.auth.service.AuthService;
import com.dentalflow.pe.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtService.generateToken(usuario);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario createUser(
            String username,
            String password,
            Integer rolId
    ) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol(rol);
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario updateUser(
            Integer userId,
            String username,
            Integer rolId,
            Boolean activo
    ) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setUsername(username);
        usuario.setRol(rol);
        usuario.setActivo(activo);

        return usuarioRepository.save(usuario);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario disableUser(Integer userId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setActivo(false);

        return usuarioRepository.save(usuario);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }
}