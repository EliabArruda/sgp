package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Requerente;
import org.springframework.stereotype.Service;

@Service
public interface RequerenteService {

   Iterable<Requerente> buscarTodos();
   Requerente buscarPorId(Long id);

   Requerente salvar(Requerente requerente);


}
