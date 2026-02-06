package com.example.apirest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome não pode ser vázio ou nulo!")
    @Size(max = 100, message = "Número máximo de caracteres permitido para nome é de 100 caracteres!")
    private String nome;
    @Column(unique = true)
    @NotBlank(message = "Email não pode ser vázio ou nulo!")
    @Size(max = 70, message = "Número máximo de caracteres permitido para e-mail é de 70 caracteres!")
    private String email;
}
