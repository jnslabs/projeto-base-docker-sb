package io.jnslabs.sbbase.model.request.springSecurity;

import lombok.Data;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:48
 */
@Data
public class LoginRequest {
    private String userName;
    private String password;
}
