package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Endereco;
import org.springframework.stereotype.Service;

@Service
public interface EnderecoService {


    Iterable<Endereco> buscarTodos();

    Endereco buscarPorId(Long id);
    Endereco salvar(Endereco endereco);
}
