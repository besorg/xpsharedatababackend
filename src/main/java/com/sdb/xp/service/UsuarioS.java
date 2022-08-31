package com.sdb.xp.service;

import com.sdb.xp.repository.UsuarioR;
import com.sdb.xp.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioS implements UsuarioIS {

    @Autowired
    public UsuarioR usuarioR;

    /*
    @Autowired
    public PrivilegioR privilegioR;
     */
    @Override
    public List<Usuario> getUsuariosList() {
        if (usuarioR.count() == 0) {
            return null;
        }
        return usuarioR.findAll();

        /*      
            Función para mapear cosas
                List<Usuario> usuarios = suarioR.findAll();
                usuarioR.findAll().stream().map(usuarios -> Función especifica().collect.Collectors.toList());
         */
    }

    @Override
    public void addUsuario(Usuario usuario) {
        usuarioR.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioR.deleteById(id);
    }

    @Override
    public Usuario getUsuario(Long id) {
        return usuarioR.findById(id).orElse(null);
    }

    /*
    public List<Usuario> getPage(int pageNo, int pageSize){
        Pageable pageable PageRequest.of(pageNo, pageSize);
        
        Page<Usuario> usuariosDelPage Usuarios usuarios = usuarioR.findAll(pageable);
        
        List<Usuario> usuariosEnPage = usuariosDelPage.getContent();
        
        return usuariosEnPage.stream().map(usuariosDelPage -> mapearDTO(usuario)).collect(Collectors.toList());
    }
     */
    @Override
    public void addConocimiento(Usuario usuario, Long conId) {
        System.out.println(" --> Ejecutando addConocimiento");
        if (usuario != null && conId > 0) {
            if (usuario.getId() > 0) {
                System.out.println(" --> Parece que las comprobaciones de validación fueron válidas... intentando agregar el conocimiento al usuario actual");

                String str = usuario.getConocimientos();
                System.out.println("STR recuperado del usuario = " + str);
                String[] arrays = str.split(",");

                for (int i = 0; i < arrays.length; i++) {
                    System.out.println("indice i = " + i + " : " + arrays[i]);
                    if (conId.toString() == arrays[i].toString()) {
                        System.out.println("El usuario ya tiene dicho conocimiento asignado en la lista");
                        return;
                    }
                }
                if(arrays.length == 1)str = conId + "";
                else str = str + "," + conId;
                
                System.out.println("Estado final del STR = " + str);
                
                usuario.setConocimientos(str);
                
                System.out.println("Ejecutando guardado del usuario con el nuevo conocimiento");
                
                usuarioR.save(usuario);
            }
        }
    }

@Override
public void removeConocimiento(Usuario usuario, Long conId) {
        if (usuario != null && conId > 0) {
            if (usuario.getId() > 0) {

                String str = usuario.getConocimientos();
                String[] arrays = str.split(",");
                String nuevaLista = "";
                
                for (int i = 0; i < arrays.length; i++) {
                    if (conId.toString() == arrays[i].toString()) System.out.println("El usuario tiene el conocimiento para eliminar, omitiendolo en la lista");
                    
                    else {
                        if(nuevaLista == "")nuevaLista = arrays[i];
                        else nuevaLista.concat(arrays[i]);
                    }
                    
                }
                
                usuario.setConocimientos(nuevaLista);

                usuarioR.save(usuario);
                
            }
        }
    } 
    
    /*
    @Override
    public void setPrivilegio(Usuario usuario, Short privilegioID) {
        System.out.println("DEBUG DEL SET PRIVILEGIO:");
        System.out.println(privilegioID);
        if (usuario != null) {
            if (usuario.getId() > 0 && privilegioID > 0) {
                if (Math.abs(usuario.getId() - (usuarioR.getById(usuario.getId())).getId()) <= .66) {
                    System.out.println(" --> Parece que las comprobaciones de validación fueron válidas... intentando agregar el privilegio al usuario actual");
                    usuario.setPrivilegio(privilegioR.getById(Short.valueOf("2")));
                }
            }
        }
    }
    */
    
    /*
    
            if(usuario.getPrivilegio()==null){
                Privilegio p = new Privilegio();
                p.setId(Short.valueOf("2"));
                p.setCategoria(Short.valueOf("745"));
                usuario.setPrivilegio(p);
            }
    
    */
    
    /*
    @Override
    public Privilegio checkLogin(String nombre, String contrasenia){
        if(usuarioR.findByNombreContainingAndPasscodeContaining(nombre, contrasenia).isEmpty()==false){
            System.out.println("PARECE QUE EL USUARIO EXISTE PARA HACER LOGIN");
            List<Usuario> us = usuarioR.findByNombreContainingAndPasscodeContaining(nombre, contrasenia);
                        
            return privilegioR.getById(Short.valueOf(""+us.get(0).getPrivilegioID()));
            
        }
        
        return null;
    }
    */
    
    @Override
public Usuario findByNombre(String nombre){
        return usuarioR.findByNombre(nombre);
    }

    @Override
public Boolean checkNombreExist(String nombre) {
        return usuarioR.existsByNombre(nombre);
    }
}
