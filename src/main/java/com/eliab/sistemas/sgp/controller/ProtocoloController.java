package com.eliab.sistemas.sgp.controller;

import com.eliab.sistemas.sgp.handle.ErrorDetails;
import com.eliab.sistemas.sgp.model.EnumStatus;
import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.service.ProtocoloService;
import com.eliab.sistemas.sgp.service.RequerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.*;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/protocolo")
public class ProtocoloController {

    @Autowired
    private ProtocoloService protocoloService;

    @Autowired
    private RequerenteService requerenteService;


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            //@Override
            public void addCorsMapping(CorsRegistry registry){
                registry.addMapping("/**").allowedOrigins("*");
            };
        };
    }

    @GetMapping("/busca-todos")
    public ResponseEntity<Iterable<Protocolo>> buscarTodos(){
        return ResponseEntity.ok(protocoloService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Protocolo> buscarPorId(@PathVariable Long id){
        return  ResponseEntity.ok(protocoloService.buscarPorId(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@Valid @RequestBody Protocolo protocolo) {
        try {
            protocoloService.salvar(protocolo);
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();
            int i=0;
            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(++i).append(": ").append(constraintViolation.getMessage()).append(" \n ");
            }
            ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), sb.toString(), e.getMessage());
            return ResponseEntity.badRequest().body(ed);
        }
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
