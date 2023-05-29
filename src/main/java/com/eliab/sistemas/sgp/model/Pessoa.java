package com.eliab.sistemas.sgp.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public abstract class Pessoa {

    @NotNull
    @Size(min=2, message="O Campo 'nome' precisa ter no mínimo 2 caracteres.")
    private String nome;


    private String endereco;

    @NotNull
    @Email
    private String email;


    private String telefone;

    public Pessoa(){

    }

    public Pessoa(String nome, String endereco, String email, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    public Pessoa(String nome){
       this.nome = nome;

    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                '}';
    }
}
