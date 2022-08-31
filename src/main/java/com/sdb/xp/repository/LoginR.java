package com.sdb.xp.repository;

import com.sdb.xp.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


@Repository
//@RepositoryRestResource(path="usuarios", collectionResourceRes="usuarios")
public interface LoginR extends JpaRepository<Login, Long>{
    
    
    Login findByNombre(String nombre);
    Login findByNombreAndPasscode(String nombre, String passcode);
    
    // @Query ("select l from Libro l where l.precio>20")
    // List<Login> findByCaros();
}
