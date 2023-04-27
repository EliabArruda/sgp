package com.eliab.sistemas.sgp.model;

import lombok.Data;

@Data
public abstract class Pessoa {

    private String nome;
    private String endereco;
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
