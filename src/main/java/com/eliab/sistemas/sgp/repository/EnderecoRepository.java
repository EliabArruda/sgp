package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
