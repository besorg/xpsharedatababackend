package com.sdb.xp.controller;

import com.sdb.xp.model.Conocimiento;
import com.sdb.xp.model.Login;
import com.sdb.xp.model.Usuario;
import com.sdb.xp.service.ConocimientoIS;
import com.sdb.xp.service.LoginIS;
import com.sdb.xp.service.UsuarioIS;
import static java.lang.System.console;
import java.net.HttpURLConnection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//(origins = "http://localhost:4200/")
@RepositoryRestResource(path = "usuarios", collectionResourceRel = "usuarios")
public class Controller {

    @Autowired
    private UsuarioIS usuarioIS;

    @Autowired
    private LoginIS loginIS;
    
    @Autowired
    private ConocimientoIS conocimientoIS;

    @GetMapping("/mundo")
    public String HolaMundo() {
        return "Hola mundo";
    }

    @GetMapping("/get/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioIS.getUsuario(id);
    }

    
    
    @GetMapping("/login/{nombre}/{passcode}")
    public Login checkLogin(@PathVariable String nombre, @PathVariable String passcode) {
        if (nombre == "" || nombre == null) return null;
        
        System.out.println("nombre = " + nombre);
        System.out.println("passcode = " + passcode);
                
        return loginIS.findByNombreAndPasscode(nombre, passcode);
    }

    @GetMapping("/getusers")
    public List<Usuario> getUsers() {
        return usuarioIS.getUsuariosList();
    }

    
    @PutMapping("/{id}") // función llamada desde Angular para establecer los nuevos valores del usuario al editarlo
    public void SetUsuarioData(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        System.out.println("Testeo del id y el PathVariable como asignación del id " + usuario.getId());

        // if(id != usuario.getId())return;
        Usuario us = usuarioIS.getUsuario(usuario.getId());
        if (us == null) {
            return;
        }
        us.setNombre(usuario.getNombre());
        us.setApellido(usuario.getApellido());
        us.setEmail(usuario.getEmail());
        us.setConocimientos(usuario.getConocimientos());
        
        usuarioIS.addUsuario(us);
    }
    
    @PutMapping("/l/{id}") // función llamada desde Angular para establecer los nuevos valores del usuario al editarlo
    public int SetLoginData(@PathVariable Long id, @RequestBody Login login, @RequestBody Login nuevologin) {
        if(id == null)return HttpURLConnection.HTTP_NOT_FOUND;
        if(id <=0)return HttpURLConnection.HTTP_NOT_FOUND;
         
        System.out.println("Testeo del id y el PathVariable como asignación del id " + login.getId() + " en el login");

        Login l = loginIS.findByNombreAndPasscode(login.getNombre(), login.getPasscode());
        
        if(l == null) return HttpURLConnection.HTTP_NOT_FOUND;
        
        // La ID es constante...
        if(l.getId() != login.getId() || l.getId() != nuevologin.getId()) {
            return HttpURLConnection.HTTP_NOT_FOUND;
        }
        
        if(!loginIS.checkNombreExist(login.getNombre())){
            
            l.setNombre(nuevologin.getNombre());
            l.setPasscode(nuevologin.getPasscode());
        
            loginIS.add(l);
            return HttpURLConnection.HTTP_CREATED;
        }else return HttpURLConnection.HTTP_FORBIDDEN;
    }

    @PostMapping("/new")
    public void NewUsuario(@RequestBody Usuario usuario) {
        if (usuario == null) {
            return;
        }
        
        System.out.println("usuarioIS.existsByNombre(usuario.getNombre() --> " + usuarioIS.checkNombreExist(usuario.getNombre()));
        if(usuarioIS.checkNombreExist(usuario.getNombre())==true) return;

        System.out.println("id = " + usuario.getId());
        System.out.println("nombre = " + usuario.getNombre());
        System.out.println("apellido = " + usuario.getApellido());
        System.out.println("email = " + usuario.getEmail());
        
        usuario.setConocimientos("");
        
        usuarioIS.addUsuario(usuario);

    }
    
    @PostMapping("/newlogin")
    public void NewLogin(@RequestBody Login login) {
        if (login.getNombre() == "" || login.getNombre() == null) {
            return;
        }
        
        System.out.println("nombre = " + login.getNombre());
        System.out.println("passcode = " + login.getPasscode());
                
        loginIS.add(login);
    }
    
    
    @PostMapping("/newconocimiento")
    public int NewConocimiento(@RequestBody Conocimiento con) {
        if (con.getNombre() == "" || con.getNombre() == null) {
            return HttpURLConnection.HTTP_FORBIDDEN;
        }
        
        List<Conocimiento> c = conocimientoIS.findConocimiento(con.getNombre(), con.getDescripcion(), con.getNivel(), con.getTiempo());
        if(c.size()>0)return HttpURLConnection.HTTP_CONFLICT; // si el conocimiento existe no se debe crear uno nuevo
        
        System.out.println("Creado conocimiento = " + con.getNombre());
        
        conocimientoIS.add(con); 
        
        return HttpURLConnection.HTTP_CREATED;
    }
    
    
    @PutMapping("/addcon/{nombre}/{descripcion}/{nivel}/{tiempo}")
    public int AddConocimiento(@RequestBody Login login, @PathVariable String nombre, @PathVariable String descripcion, @PathVariable String nivel, @PathVariable String tiempo) {
        System.out.println("Add conocimiento is running");
        
        if (login.getNombre() == null) return HttpURLConnection.HTTP_NOT_FOUND;     if (login.getNombre() == "") return HttpURLConnection.HTTP_NOT_FOUND;
        
        Login l = loginIS.findByNombreAndPasscode(login.getNombre(), login.getPasscode());
        
        if(l.getId()==null)return HttpURLConnection.HTTP_UNAUTHORIZED;              if(l.getId()==0)return HttpURLConnection.HTTP_UNAUTHORIZED;
        
        
        System.out.println("Comprobada la validación del login como index " + l.getId());
                
        System.out.println("nombre = " + login.getNombre());        System.out.println("passcode = " + login.getPasscode());
                
        Usuario u = usuarioIS.findByNombre(l.getNombre());
        
        System.out.println("Comprobando la validación de Usuario");
        
        if(u == null)return HttpURLConnection.HTTP_NOT_FOUND;        if(u.getNombre() == null)return HttpURLConnection.HTTP_NOT_FOUND;  if(u.getNombre() == "")return HttpURLConnection.HTTP_NOT_FOUND;
                
        List<Conocimiento> con = conocimientoIS.findConocimiento(nombre, descripcion, nivel, tiempo);
        
        System.out.println("Número de indices dentro de la lista " + con.size());
        
        if(con.isEmpty())return HttpURLConnection.HTTP_NOT_FOUND;
        
                
        System.out.println("con = " + con);
        System.out.println("con = " + con.get(0).getId());
          
        usuarioIS.addConocimiento(u, con.get(0).getId());
        
        System.out.println("Agregando Conocimiento al registro del Usuario... o inversa");
        
        return HttpURLConnection.HTTP_CREATED;
    }
    
    @GetMapping("/getcon/{id}")
    public Conocimiento GetConocimiento(@PathVariable Long id){
        System.out.println(" ------> id " + id);
        Conocimiento c = conocimientoIS.getConocimiento(id);
        
        //if(c == null)return null;
        
        return c;
    }
    
    @DeleteMapping("delcon/{id}")
    public void DeleteConocimiento(@PathVariable Long id) {
        conocimientoIS.del(id);
    }
    
    @DeleteMapping("del/{id}")
    public void DeleteUsuario(@PathVariable Long id) {
        usuarioIS.deleteUsuario(id);
    }
    
    

}
