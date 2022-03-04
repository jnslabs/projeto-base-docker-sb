package io.jnslabs.sbbase.service.impl;

import io.jnslabs.sbbase.exceptions.MyCustomException;
import io.jnslabs.sbbase.model.entity.Usuario;
import io.jnslabs.sbbase.model.reposnse.springSecurity.LoginResponse;
import io.jnslabs.sbbase.model.reposnse.springSecurity.UserResponse;
import io.jnslabs.sbbase.model.request.springSecurity.SignUpRequest;
import io.jnslabs.sbbase.repository.SecureUserRepository;
import io.jnslabs.sbbase.security.IJwtTokenProviderService;
import io.jnslabs.sbbase.service.ISecureUserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 17:03
 */
@Slf4j
@AllArgsConstructor
@Service
public class SecureUserService implements ISecureUserService {

    private final SecureUserRepository secureUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final IJwtTokenProviderService jwtTokenProviderService;

    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(String username, String password) {
        log.info("Realizando o login com username: {}", username);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            Usuario user = secureUserRepository.findByUsername(username);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setNome(user.getNome());
            loginResponse.setEmail(user.getEmail());
            loginResponse.setUserName(user.getUsername());
            loginResponse.setAccessToken(jwtTokenProviderService.createToken(username, user.getRoles()));

            log.info("Login successfully");

            return loginResponse;
        } catch (AuthenticationException e) {
            throw new MyCustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Usuario signUp(SignUpRequest request) {
        if(secureUserRepository.existsByUsername(request.getUserName())){
            throw new MyCustomException("User already exists in system", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Usuario user = new Usuario();
        user.setNome(request.getNome());
        user.setSobrenome(request.getSobrenome());
        user.setUsername(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        request.setPassword(user.getPassword());

        secureUserRepository.save(user);
        log.info("Register successfully");

        return user;
    }

    @Override
    public void removeUser(String username) {
        if(!secureUserRepository.existsByUsername(username)){
            throw new RuntimeException("User doesn't exists");
        }
        secureUserRepository.deleteByUsername(username);
        log.info("User remove successfully");
    }

    @Override
    public UserResponse searchUser(String username) {
        Usuario user = secureUserRepository.findByUsername(username);
        if (user == null) {
            throw new MyCustomException("Provided user doesn't exist", HttpStatus.NOT_FOUND);
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUsername());

        return userResponse;
    }

    @Override
    public List<Usuario> getAllUser() {
        return secureUserRepository.findAll();
    }

    @Override
    public String refreshToken(String username) {
        return jwtTokenProviderService.createToken(username, secureUserRepository.findByUsername(username).getRoles());
    }
}
