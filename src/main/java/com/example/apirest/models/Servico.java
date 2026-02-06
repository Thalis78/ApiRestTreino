package com.example.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String nome;

    private String descricao;
}
