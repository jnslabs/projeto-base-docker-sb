package io.jnslabs.sbbase.model.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:10
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
