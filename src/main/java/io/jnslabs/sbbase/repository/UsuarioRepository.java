package io.jnslabs.sbbase.repository;

import io.jnslabs.sbbase.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 22/02/2022 - 12:17
 */
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
