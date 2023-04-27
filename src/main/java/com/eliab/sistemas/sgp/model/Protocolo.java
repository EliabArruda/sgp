package com.eliab.sistemas.sgp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.Set;

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
