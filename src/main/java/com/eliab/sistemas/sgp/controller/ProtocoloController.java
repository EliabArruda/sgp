package com.eliab.sistemas.sgp.controller;

import com.eliab.sistemas.sgp.model.EnumStatus;
import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.service.ProtocoloService;
import com.eliab.sistemas.sgp.service.RequerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/protocolo")
public class ProtocoloController {

    @Autowired
    private ProtocoloService protocoloService;

    @Autowired
    private RequerenteService requerenteService;

    @GetMapping("/busca-todos")
    public ResponseEntity<Iterable<Protocolo>> buscarTodos(){
        return ResponseEntity.ok(protocoloService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Protocolo> buscarPorId(@PathVariable Long id){
        return  ResponseEntity.ok(protocoloService.buscarPorId(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Protocolo> salvar(@Valid @RequestBody Protocolo protocolo){
            protocoloService.salvar(protocolo);
            return ResponseEntity.ok(protocolo);
    }
    @PutMapping("/{id}/mudar-status")
    public ResponseEntity<EnumStatus> mudarStatus(@PathVariable Long id, EnumStatus status){
        protocoloService.mudarStatus(id, status);
        return ResponseEntity.ok(status);
    }

    //OUTRAS FORMAS DE VALIDAÇÃO

       /* ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Protocolo>> protocoloValidacao = validator.validate(protocolo);
        if(!protocoloValidacao.isEmpty())
            throw new ProtocoloNotBlankException();

        */
/*
        for (ConstraintViolation error : protocoloValidacao) {
            String msgError = error.getMessageTemplate();
            System.out.println(msgError);

            }
 */
            /*if(!protocoloValidacao.isEmpty())
                ResponseEntity.badRequest().body(msgError);

             */
}
