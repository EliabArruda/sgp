package com.eliab.sistemas.sgp.repository;

import com.eliab.sistemas.sgp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.username= (:username)")
    public Usuario findByUsername(@Param("username") String username);
}