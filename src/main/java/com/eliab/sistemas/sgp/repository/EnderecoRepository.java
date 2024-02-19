package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByCep(String cep);
}
