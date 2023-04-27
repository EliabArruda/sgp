package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Protocolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocoloRepository extends CrudRepository<Protocolo, Long> {
}
