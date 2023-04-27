package com.eliab.sistemas.sgp.controller;

import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.service.ProtocoloService;
import com.eliab.sistemas.sgp.service.RequerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requerentes")
public class RequerenteController {


    @Autowired
    private RequerenteService requerenteService;

    @GetMapping("/busca-todos")
    public ResponseEntity<Iterable<Requerente>> buscarTodos(){
        return ResponseEntity.ok(requerenteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requerente> buscarPorId(@PathVariable Long id){
        return  ResponseEntity.ok(requerenteService.buscarPorId(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Requerente> salvar(@RequestBody Requerente requerente){
        requerenteService.salvar(requerente);
        return ResponseEntity.ok(requerente);
    }
}
