package com.dentalflow.pe.auth.service;

import com.dentalflow.pe.auth.entity.Usuario;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface AuthService {

    @WebMethod
    String login(String username, String password);

    @WebMethod
    Usuario createUser(
            String username,
            String password,
            Integer rolId
    );

    @WebMethod
    Usuario updateUser(
            Integer userId,
            String username,
            Integer rolId,
            Boolean activo
    );

    @WebMethod
    Usuario disableUser(Integer userId);

    @WebMethod
    List<Usuario> getAllUsers();
}