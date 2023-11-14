package com.eliab.sistemas.sgp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @see <a href="https://www.jsonschema2pojo.org/">www.jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br/">viacep.com.br</a>
 */

@Entity
@Data
public class Endereco {

    @Id
    private String cep;

    private String logradouro;
    private String uf;
    private String bairro;
    private String localidade;
    private String complemento;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
