package com.sdb.xp.service;

import com.sdb.xp.model.Conocimiento;
import java.util.List;

public interface ConocimientoIS {
    
    public void add(Conocimiento conocimiento);
    public void del(Long id);
    
    public Boolean checkNombreExist(String nombre);
    
    public Conocimiento getConocimiento(Long id);
    public Conocimiento findByNombre(String nombre);
    
    public List<Conocimiento> findConocimiento(String nombre, String descripcion, String nivel, String tiempo);
}
