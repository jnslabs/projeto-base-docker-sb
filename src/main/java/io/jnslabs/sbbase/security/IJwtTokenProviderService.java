package io.jnslabs.sbbase.security;

import io.jnslabs.sbbase.model.entity.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:21
 */
public interface IJwtTokenProviderService {
    String createToken(String username, List<Role> roles);
    Authentication validateUserAndGetAuthentication(String token);
    String getUsername(String token);
    String parseToken(HttpServletRequest req);
    boolean validateToken(String token);
}
