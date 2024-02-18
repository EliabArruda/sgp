package com.eliab.sistemas.sgp.controller;

import com.eliab.sistemas.sgp.handle.ErrorDetails;
import com.eliab.sistemas.sgp.model.Endereco;
import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;


    @PostMapping(value = "/salvar")
    public ResponseEntity<?> salvar(@Valid @RequestBody Endereco endereco){
        try {
            enderecoService.salvar(endereco);
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(++i).append(": ").append(constraintViolation.getMessage()).append(" \n ");
            }
            ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), sb.toString(), e.getMessage());
            return ResponseEntity.badRequest().body(ed);
        }
        return ResponseEntity.ok(endereco);
    }

    @GetMapping(value = "/busca-todos")
    public ResponseEntity<Iterable<Endereco>> buscarTodos(){
        return ResponseEntity.ok(enderecoService.buscarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }
}
