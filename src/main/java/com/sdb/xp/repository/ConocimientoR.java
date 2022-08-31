package com.sdb.xp.repository;

import com.sdb.xp.model.Conocimiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConocimientoR extends JpaRepository<Conocimiento, Long>{
    
    Conocimiento findByNombre(String nombre);
    
    List<Conocimiento> findByNombreAndDescripcionAndNivelAndTiempo(String nombre, String descripcion, String nivel, String tiempo);
    
}
