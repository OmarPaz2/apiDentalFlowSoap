package com.dentalflow.pe.auth.soap;

import com.dentalflow.pe.auth.entity.Usuario;
import com.dentalflow.pe.auth.service.AuthService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@WebService(serviceName = "AuthService")
@Component
@RequiredArgsConstructor
public class AuthEndpoint {

    private final AuthService authService;

    @WebMethod
    public String login(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    ) {
        return authService.login(username, password);
    }

    @WebMethod
    public Usuario createUser(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "rolId") Integer rolId
    ) {
        return authService.createUser(username, password, rolId);
    }

    @WebMethod
    public Usuario updateUser(
            @WebParam(name = "userId") Integer userId,
            @WebParam(name = "username") String username,
            @WebParam(name = "rolId") Integer rolId,
            @WebParam(name = "activo") Boolean activo
    ) {
        return authService.updateUser(userId, username, rolId, activo);
    }

    @WebMethod
    public Usuario disableUser(
            @WebParam(name = "userId") Integer userId
    ) {
        return authService.disableUser(userId);
    }

    @WebMethod
    public List<Usuario> getAllUsers() {
        return authService.getAllUsers();
    }
}