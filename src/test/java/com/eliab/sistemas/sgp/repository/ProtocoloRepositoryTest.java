package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Protocolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class ProtocoloRepositoryTest {

    @Autowired
    private ProtocoloRepository protocoloRepository;

    private Protocolo protocolo;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void saveTest(){
            Protocolo protocolo = new Protocolo(); // Inicializa o objeto protocolo

            protocolo.setProtocolo("01");
            protocolo.setId(1L);
            protocolo.setData("01/05/2022");
            protocolo.setStatus("PENDENTE");
        Protocolo protocoloSalvo = protocoloRepository.save(protocolo);

       assertNotNull(protocoloSalvo);

    }
}
