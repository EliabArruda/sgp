package com.eliab.sistemas.sgp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Requerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "O Campo 'nome' precisa ter no mínimo 2 caracteres.")
    private String nome;


    @ManyToOne
    private Endereco endereco;

    @NotNull
    @Email(message = "O email deve ser válido")
    private String email;

    @NotNull
    @Size(min = 11, max = 11, message = "Número de Telefone Inválido")
    private String telefone;

    }



