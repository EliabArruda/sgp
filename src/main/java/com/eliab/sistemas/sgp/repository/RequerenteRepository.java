package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Requerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequerenteRepository extends CrudRepository<Requerente, Long> {
}
