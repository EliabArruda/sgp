package com.eliab.sistemas.sgp.model;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Requerente {//extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

}
