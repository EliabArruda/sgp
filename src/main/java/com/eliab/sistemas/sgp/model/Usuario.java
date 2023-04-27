package com.eliab.sistemas.sgp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario extends Pessoa{

    /**Construtores*/
    /*public Usuario(){
        super();
    }

    public Usuario(String nome) {
        super(nome);
    }
    */
   /***----------------------------*/

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;


   private String nivelAcesso;


}
