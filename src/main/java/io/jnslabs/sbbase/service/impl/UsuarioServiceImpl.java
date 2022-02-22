package io.jnslabs.sbbase.service.impl;

import io.jnslabs.sbbase.model.entity.Usuario;
import io.jnslabs.sbbase.repository.UsuarioRepository;
import io.jnslabs.sbbase.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 22/02/2022 - 12:23
 */
@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(UUID uuid) {
        return usuarioRepository.findById(uuid);
    }

    @Override
    public Usuario update(UUID uuid, Usuario usuario) {
        Optional<Usuario> usuarioOptional = findById(uuid);
        if (usuarioOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario inexistente!");
        }
        var usuarioPersiste = usuarioOptional.get();
        usuarioPersiste.setEmail(usuario.getEmail());
        usuarioPersiste.setNome(usuario.getNome());
        usuarioPersiste.setSobrenome(usuario.getSobrenome());
        return usuarioRepository.save(usuarioPersiste);
    }

    @Override
    public void delete(UUID uuid) {
        Optional<Usuario> usuarioOptional = findById(uuid);
        if (usuarioOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario inexistente!");
        }
        usuarioRepository.delete(usuarioOptional.get());
    }
}
