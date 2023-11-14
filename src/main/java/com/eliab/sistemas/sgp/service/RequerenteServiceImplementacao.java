package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.Endereco;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.repository.EnderecoRepository;
import com.eliab.sistemas.sgp.repository.RequerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RequerenteServiceImplementacao implements RequerenteService{

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
        String cep = requerente.getEndereco().getCep();

        /**Verifica se CEP do cliente já existe*/
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            /**Se não existir, integrar com o ViaCEP e retornar novo endereço*/
            Endereco novoEndereco = enderecoService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        requerente.setEndereco(endereco);
        return requerenteRepository.save(requerente);
    }


    @Override
    public Requerente salvar(Requerente requerente) {
        return salvarRequerenteComCep(requerente);

    }
}
