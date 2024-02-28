package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Endereco;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private RequerenteRepository requerenteRepository;
    @Autowired
    private ProtocoloRepository protocoloRepository;

    private Endereco endereco;
    private Endereco enderecoCopia;

    private List<Endereco> enderecos;

    @BeforeAll
    public void before() {
        enderecoRepository.deleteAll();
    }

    @BeforeEach
    public void setUp() {
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

        enderecos = List.of(endereco, enderecoCopia);
    }

    @AfterEach
    public void tearDown() {
        enderecoRepository.deleteAll();
    }

    @Test
    public void saveTest() {

        assertEquals(null, endereco.getId());
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        assertNotNull(endereco.getId());
        assertEquals(1, enderecoRepository.findAll().size());

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


        assertEquals(2, enderecoRepository.findAll().size());

    }

    @Test
    public void findByIdTest() {


        saveAll(enderecos);
        assertNotEquals(0, enderecoRepository.findAll().size());
        assertEquals(endereco, enderecoRepository.findById(endereco.getId()).get());
        assertEquals(enderecoCopia, enderecoRepository.findById(enderecoCopia.getId()).get());
        assertNotEquals(enderecoCopia, enderecoRepository.findById(endereco.getId()).get());

    }


    @Test
    public void findAllTest() {
        assertEquals(0, enderecoRepository.findAll().size());
        enderecoRepository.save(endereco);
        assertEquals(1, enderecoRepository.findAll().size());
        enderecoRepository.save(enderecoCopia);
        assertEquals(2, enderecoRepository.findAll().size());
    }

    @Test
    public void findAllByCepTest() {

        saveAll(enderecos);
        List<Endereco> all = enderecoRepository.findAllByCep(endereco.getCep());
        assertEquals(enderecos.size(), enderecoRepository.findAll().size());
        all.stream().forEach(e -> e.getCep().equals(endereco.getCep()));

    }

    private void saveAll(List<Endereco> enderecos) {
        enderecoRepository.saveAll(enderecos);
    }

    @Test
    public void deleteAllTest() {

        enderecoRepository.deleteAll();
        assertEquals(0, enderecoRepository.findAll().size());

        enderecoRepository.save(endereco);
        enderecoRepository.deleteAll();
        assertEquals(0, enderecoRepository.findAll().size());

        saveAll(enderecos);
        enderecoRepository.deleteAll();
        assertEquals(0, enderecoRepository.findAll().size());

    }

}
