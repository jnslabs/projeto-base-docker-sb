package io.jnslabs.sbbase.service;

import io.jnslabs.sbbase.model.entity.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 22/02/2022 - 12:18
 */
public interface UsuarioService {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Optional<Usuario> findById(UUID uuid);
    Usuario update(UUID uuid, Usuario usuario);
    void delete(UUID uuid);
}
