package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Endereco;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.repository.RequerenteRepository;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class RequerenteServiceImplementacao implements RequerenteService {

    @Autowired
    private RequerenteRepository requerenteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public Iterable<Requerente> buscarTodos() {
        return requerenteRepository.findAll();
    }

    @Override
    public Requerente buscarPorId(Long id) {
        Optional<Requerente> busca = requerenteRepository.findById(id);
        return busca.get();
    }
    @Override
    public Requerente salvar(Requerente requerente) {
        Endereco enderecoRegistrado = enderecoService.salvar(requerente.getEndereco());
        requerente.setEndereco(enderecoRegistrado);

        return requerenteRepository.save(requerente);

    }
}