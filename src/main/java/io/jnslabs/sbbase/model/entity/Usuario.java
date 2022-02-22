package io.jnslabs.sbbase.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 22/02/2022 - 12:08
 */
@Entity
@Table(name = "TB_USUARIO")
@Data
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @Column(nullable = false,unique = true, length = 50)
    private String email;
}
