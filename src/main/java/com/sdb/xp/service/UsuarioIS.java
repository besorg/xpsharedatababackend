package com.sdb.xp.service;

import com.sdb.xp.model.Usuario;
import java.util.List;

public interface UsuarioIS {
    
    public List<Usuario> getUsuariosList();

    public void addUsuario(Usuario usuario);
    public void deleteUsuario(Long id);
    public Usuario getUsuario(Long id);
    
    public void addConocimiento(Usuario usuario, Long conId);
    public void removeConocimiento(Usuario usuario, Long conId);
    
    public Usuario findByNombre(String nombre);
    
    public Boolean checkNombreExist(String nombre);
}
