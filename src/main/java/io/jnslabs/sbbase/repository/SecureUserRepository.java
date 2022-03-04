package io.jnslabs.sbbase.repository;

import io.jnslabs.sbbase.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 16:00
 */
@Repository
public interface SecureUserRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByUsername(String username);

    Usuario findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
