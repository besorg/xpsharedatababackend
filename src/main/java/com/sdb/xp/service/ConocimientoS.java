package com.sdb.xp.service;

import com.sdb.xp.model.Conocimiento;
import com.sdb.xp.repository.ConocimientoR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConocimientoS implements ConocimientoIS {
    
    @Autowired
    public ConocimientoR conocimientoR;
    

    @Override
    public void add(Conocimiento conocimiento) {
        conocimientoR.save(conocimiento);
    }

    @Override
    public void del(Long id) {
        conocimientoR.deleteById(id);
    }

    @Override
    public Boolean checkNombreExist(String nombre) {
        return conocimientoR.findByNombre(nombre) != null;
    }

    @Override
    public Conocimiento findByNombre(String nombre) {
        return conocimientoR.findByNombre(nombre);
    }

    @Override
    public List<Conocimiento> findConocimiento(String nombre, String descripcion, String nivel, String tiempo) {
        return conocimientoR.findByNombreAndDescripcionAndNivelAndTiempo(nombre, descripcion, nivel, tiempo);
    }

    @Override
    public Conocimiento getConocimiento(Long id) {
        return conocimientoR.findById(id).orElse(null);
    }

    
}