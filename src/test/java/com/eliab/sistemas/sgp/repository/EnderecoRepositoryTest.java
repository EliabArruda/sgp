package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;
    private Endereco enderecoCopia;

    @BeforeEach
    public void setUp(){
        this.endereco = Endereco.builder()
                .cep("52156541")
                .logradouro("Rua Tal")
                .uf("PE")
                .bairro("Janga")
                .localidade("Paulista")
                .numeroCasa("121")
                .build();
        this.enderecoCopia = Endereco.builder()
                .cep("52156541")
                .logradouro("Rua Tal")
                .uf("PE")
                .bairro("Janga")
                .localidade("Paulista")
                .numeroCasa("121")
                .build();
    }

    @Test
    public void saveTest() {

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        assertNotNull(enderecoSalvo);
        assertNotNull(enderecoSalvo.getId());

        assertEquals(endereco.getCep(), enderecoSalvo.getCep());
        assertEquals(endereco.getLogradouro(), enderecoSalvo.getLogradouro());
        assertEquals(endereco.getUf(), enderecoSalvo.getUf());
        assertEquals(endereco.getBairro(), enderecoSalvo.getBairro());
        assertEquals(endereco.getLocalidade(), enderecoSalvo.getLocalidade());
        assertEquals(endereco.getNumeroCasa(), enderecoSalvo.getNumeroCasa());

        Endereco enderecoSalvo2 = enderecoRepository.save(enderecoCopia);

        assertNotNull(enderecoSalvo2);
        assertNotNull(enderecoSalvo2.getId());

        assertNotEquals(enderecoSalvo.getId(), enderecoSalvo2.getId());

    }

    @Test
    public void findAllTest() {
        List<Endereco> all = enderecoRepository.findAll();
        Endereco enderecoSalvo1 = enderecoRepository.save(endereco);
        Endereco enderecoSalvo2 = enderecoRepository.save(endereco);
        assertTrue(all.size() >= 2);
    }

    @Test
    public void findAllByCepTest() {
        List<Endereco> all = enderecoRepository.findAllByCep(endereco.getCep());
        all.stream().forEach(e -> e.getCep().equals(endereco.getCep()));
    }

}
