package com.eliab.sistemas.sgp.model;

import com.eliab.sistemas.sgp.model.format.Formatado;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

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

    @NotNull(message = "The status is required.")
    private EnumStatus status;

    @NotNull(message = "The description is required.")
    @Size(min = 2, message = "O campo 'descrição' precisa ter no mínimo 2 caracteres.")
    private String descricao;

    private String data;


}








