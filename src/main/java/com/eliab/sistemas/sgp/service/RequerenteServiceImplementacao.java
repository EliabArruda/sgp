package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Endereco;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.repository.EnderecoRepository;
import com.eliab.sistemas.sgp.repository.RequerenteRepository;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class RequerenteServiceImplementacao implements RequerenteService {

    @Autowired
    private RequerenteRepository requerenteRepository;

    @Override
    public Iterable<Requerente> buscarTodos() {
        return requerenteRepository.findAll();
    }

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public Requerente buscarPorId(Long id) {
        Optional<Requerente> busca = requerenteRepository.findById(id);
        return busca.get();
    }

    @Override
    public Requerente salvarRequerenteComCep(Requerente requerente) {
        registrarEndereco(requerente);
        return requerenteRepository.save(requerente);
    }

    @Override
    public void registrarEndereco(Requerente requerente) {
        String cep = requerente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCep(cep);

            try {
                novoEndereco = enderecoService.consultarCep(cep);
            } catch (RetryableException e) {
                e.printStackTrace();
            }

            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        requerente.setEndereco(endereco);
    }

    @Override
    public Requerente salvar(Requerente requerente) {
        return salvarRequerenteComCep(requerente);

    }
}