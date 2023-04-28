package com.eliab.sistemas.sgp.model;


import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Protocolo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Requerente requerente;

    private String status;
    //private String descricao;
    //private Date data;

}
