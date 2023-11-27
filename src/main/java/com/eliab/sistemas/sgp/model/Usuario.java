package com.eliab.sistemas.sgp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String username;

    private String password;


}
