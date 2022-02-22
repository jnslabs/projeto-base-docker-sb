package io.jnslabs.sbbase.controller;

import io.jnslabs.sbbase.model.entity.Usuario;
import io.jnslabs.sbbase.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 22/02/2022 - 13:34
 */
@RestController
@RequestMapping("usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Usuario> findById(@PathVariable(value = "uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(uuid).get());
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<Usuario> update(@PathVariable(value = "uuid") UUID uuid, @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(uuid, usuario));
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable(value = "uuid") UUID uuid) {
        usuarioService.delete(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
