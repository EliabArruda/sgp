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

       Protocolo protocoloSalvo = protocoloRepository.save(protocolo);

       assertNotNull(protocoloSalvo);


    }
}
