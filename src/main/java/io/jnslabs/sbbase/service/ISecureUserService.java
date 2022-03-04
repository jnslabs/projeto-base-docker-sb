package io.jnslabs.sbbase.service;

import io.jnslabs.sbbase.model.entity.Usuario;
import io.jnslabs.sbbase.model.reposnse.springSecurity.LoginResponse;
import io.jnslabs.sbbase.model.reposnse.springSecurity.UserResponse;
import io.jnslabs.sbbase.model.request.springSecurity.SignUpRequest;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:58
 */
public interface ISecureUserService {

    LoginResponse login(String username, String password);

    Usuario signUp(SignUpRequest request);

    void removeUser(String username);

    UserResponse searchUser(String userName);

    List<Usuario> getAllUser();

    String refreshToken(String userName);
}
