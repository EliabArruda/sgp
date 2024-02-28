package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.exception.EnderecoNotFoundException;
import com.eliab.sistemas.sgp.model.Endereco;
import com.eliab.sistemas.sgp.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnderecoServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        this.endereco = Endereco.builder()
                .cep("53437320")
                .logradouro("Rua Joao Alfredo")
                .uf("PE")
                .localidade("Paulista")
                .bairro("Janga")
                .numeroCasa("248")
                .build();

    }

    @Test
    public void salvarTest() {

        assertEquals(null, endereco.getId());
        Endereco enderecoSalvo = enderecoService.salvar(endereco);
        assertNotNull(endereco.getId());
        assertNotNull(enderecoSalvo.getId());
        assertEquals(enderecoSalvo, endereco);
    }

    //@Test(expectedException = EnderecoNotFoundException.class)
    @Test
    public void buscarPorIdNullArgumentExceptionTest() {

        try {
            Endereco endereco1 = enderecoService.buscarPorId(null);
            fail("Uma exceção devia ter sido lançada!");
        } catch (InvalidDataAccessApiUsageException e) {
            assertEquals(e.getCause().getClass(), IllegalArgumentException.class);

        }
    }

    @Test
    public void buscarPorIdNaoExistenteTest() {
        Long idNaoExistente = -1L;
        try {
            enderecoService.buscarPorId(idNaoExistente);
            fail("Uma exceção devia ter sido lançada!");
        } catch (EnderecoNotFoundException e) {
            assertEquals("Endereco não encontrado com o ID: " + idNaoExistente, e.getMessage());
        }
    }

    @Test
    public void buscarTodosTest() {

        enderecoRepository.deleteAll();
        enderecoService.salvar(endereco);

        assertEquals(enderecoRepository.findAll(), enderecoService.buscarTodos());
        assertNotNull(enderecoService.buscarTodos());
    }
}
