package com.eliab.sistemas.sgp.controller;

import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.service.ProtocoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/protocolos")
public class ProtocoloController {

    @Autowired
    private ProtocoloService protocoloService;

    @GetMapping("/busca-todos")
    public ResponseEntity<Iterable<Protocolo>> buscarTodos(){
        return ResponseEntity.ok(protocoloService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Protocolo> buscarPorId(@PathVariable Long id){
        return  ResponseEntity.ok(protocoloService.buscarPorId(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Protocolo> salvar(@RequestBody Protocolo protocolo){
        protocoloService.salvar(protocolo);
        return ResponseEntity.ok(protocolo);
    }
}
