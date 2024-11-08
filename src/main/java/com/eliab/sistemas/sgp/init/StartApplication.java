package com.eliab.sistemas.sgp.init;
import com.eliab.sistemas.sgp.model.Usuario;
import com.eliab.sistemas.sgp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UsuarioRepository repository;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = repository.findByUsername("admin");
        if(usuario ==null){
            usuario = new Usuario();
            usuario.setName("ADMIN");
            usuario.setUsername("admin");
            usuario.setPassword("master123");
            usuario.getRoles().add("MANAGERS");
            repository.save(usuario);
        }
        usuario = repository.findByUsername("user");
        if(usuario ==null){
            usuario = new Usuario();
            usuario.setName("USER");
            usuario.setUsername("user");
            usuario.setPassword("user123");
            usuario.getRoles().add("USERS");
            repository.save(usuario);
        }
    }
}