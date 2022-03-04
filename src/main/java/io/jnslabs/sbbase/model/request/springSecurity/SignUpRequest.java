package io.jnslabs.sbbase.model.request.springSecurity;

import io.jnslabs.sbbase.model.entity.Role;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:50
 */

@Data
public class SignUpRequest {
    private String nome;
    private String sobrenome;
    private String userName;
    private String email;
    private String password;
    private List<Role> roles;
}
