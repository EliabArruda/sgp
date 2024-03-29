package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Protocolo;
import org.springframework.stereotype.Service;

@Service
public interface ProtocoloService {

    Iterable<Protocolo> buscarTodos();

    Protocolo buscarPorId(Long id);

    Protocolo salvar(Protocolo protocolo);

    void atualizar(Long id, Protocolo protocolo);

    void deletar(Long id);

    String mudarStatus(Long id, String status);
}
