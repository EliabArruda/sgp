package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.exception.EnderecoNotFoundException;
import com.eliab.sistemas.sgp.model.Endereco;
import com.eliab.sistemas.sgp.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class EnderecoServiceImplementacao implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Override
    public Iterable<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco buscarPorId(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNotFoundException("Endereco não encontrado com o ID: " + id));
    }
    @Override
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}
