package com.eliab.sistemas.sgp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
/*
    @GetMapping
    public String welcome(){
        return "Bem vindo ao Sistema de Gestão de Protocolos!";
    }
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('MANAGERS','USERS')")
    public String users(){
        return "Usuario Autorizado!";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasAnyRole('MANAGERS')")
    public String managers(){
        return "ADM Autorizado!";
    }

 */
}
