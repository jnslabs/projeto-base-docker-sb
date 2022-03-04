package io.jnslabs.sbbase.model.reposnse.springSecurity;

import lombok.Data;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 17:00
 */
@Data
public class LoginResponse {
    private String nome;
    private String userName;
    private String email;
    private String accessToken;
}
