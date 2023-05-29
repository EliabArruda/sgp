package com.eliab.sistemas.sgp.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;


@Entity
@Data
public class Protocolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Requerente requerente;

    @NotNull(message = "The status is required.")
    private EnumStatus status;

    @NotNull(message = "The description is required.")
    @Size(min=2, message="O campo 'descrição' precisa ter no mínimo 2 caracteres.")
    private String descricao;






    //private Date data;



}
