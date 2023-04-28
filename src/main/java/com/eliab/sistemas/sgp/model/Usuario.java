package com.eliab.sistemas.sgp.model;

import lombok.Data;
import javax.persistence.*;

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
