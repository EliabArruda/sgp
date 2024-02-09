package com.eliab.sistemas.sgp.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Protocolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String protocolo;

    @ManyToOne
    private Requerente requerente;

    private String status;

    @NotNull(message = "The description is required.")
    @Size(min = 2, message = "O campo 'descrição' precisa ter no mínimo 2 caracteres.")
    private String descricao;

    private String data;

    public Protocolo(){
        this.status = "PENDENTE";
    }

}