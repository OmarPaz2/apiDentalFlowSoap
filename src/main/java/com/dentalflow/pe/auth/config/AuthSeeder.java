package com.dentalflow.pe.auth.config;

import com.dentalflow.pe.auth.entity.Rol;
import com.dentalflow.pe.auth.entity.Usuario;
import com.dentalflow.pe.auth.repository.IRolRepository;
import com.dentalflow.pe.auth.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthSeeder implements CommandLineRunner {

    private final IRolRepository rolRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        boolean adminExists =
                usuarioRepository.existsByRol_Nombre("ADMIN");

        if (adminExists) {
            return;
        }

        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("RECEPCIONISTA");
        createRoleIfNotExists("ODONTOLOGO");
        //createRoleIfNotExists("PATIENT");

        Rol adminRole = rolRepository.findByNombre("ADMIN")
                .orElseThrow(() ->
                        new RuntimeException("Rol ADMIN no encontrado")
                );

        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(
                passwordEncoder.encode("Admin123*")
        );
        admin.setRol(adminRole);
        admin.setActivo(true);

        usuarioRepository.save(admin);
    }

    private void createRoleIfNotExists(String roleName) {
        if (rolRepository.findByNombre(roleName).isEmpty()) {
            Rol rol = new Rol();
            rol.setNombre(roleName);

            rolRepository.save(rol);
        }
    }
}