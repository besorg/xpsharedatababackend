package com.sdb.xp.repository;

import com.sdb.xp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.query.Param;


@Repository
//@RepositoryRestResource(path="usuarios", collectionResourceRes="usuarios")
public interface UsuarioR extends JpaRepository<Usuario, Long>{
         
    Usuario findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);
}
