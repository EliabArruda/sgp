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

    @NotNull
    @Size(min = 2, message = "O Campo 'endereco' precisa ter no mínimo 2 caracteres.")
    private String endereco;

    @NotNull
    @Email(message = "O email deve ser válido")
    private String email;

    @NotNull
    @Size(min = 11, max = 11, message = "Número de Telefone Inválido")
    private String telefone;

    

    /*
    public String getTelefone() {

        String format = String.format("(" + telefone.substring(0,2) + ")" + telefone.substring(2, 3) + " " +
                telefone.substring(3, 7) + "-" +
                telefone.substring(7, 11));

        return format;
    }

     */

    /*
    public static void main(String[] args) {
        String telefone = "81985007186";

        String format = String.format("(" + telefone.substring(0,2) + ")" + telefone.substring(2, 3) + " " +
                telefone.substring(3, 7) + "-" +
                telefone.substring(7, 11));

        System.out.println(format);

     */



    }



