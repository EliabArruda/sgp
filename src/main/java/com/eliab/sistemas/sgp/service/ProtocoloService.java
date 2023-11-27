package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.EnumStatus;
import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.model.Requerente;
import org.springframework.stereotype.Service;

@Service
public interface ProtocoloService {

    Iterable<Protocolo> buscarTodos();

    Protocolo buscarPorId(Long id);

    Protocolo salvar(Protocolo protocolo);

    void atualizar(Long id, Protocolo protocolo);

    void deletar(Long id);

    EnumStatus mudarStatus(Long id, EnumStatus status);
}
