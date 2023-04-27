package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.repository.ProtocoloRepository;
import com.eliab.sistemas.sgp.repository.RequerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProtocoloServiceImplementacao implements ProtocoloService{

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private RequerenteService requerenteService;

    //@Autowired
    //private Requerente requerente;


    @Override
    public Iterable<Protocolo> buscarTodos() {
        return protocoloRepository.findAll();
    }

    @Override
    public Protocolo buscarPorId(@PathVariable Long id) {
        Optional<Protocolo> busca = protocoloRepository.findById(id);
        return busca.get();
    }


    @Override
    public Protocolo salvar(Protocolo protocolo) {
        Requerente requerenteSalvo = requerenteService.salvar(protocolo.getRequerente());
        protocolo.setRequerente(requerenteSalvo);
        return protocoloRepository.save(protocolo);
    }

    @Override
    public void atualizar(Long id, Protocolo protocolo) {

    }

    @Override
    public void deletar(Long id) {

    }
}
