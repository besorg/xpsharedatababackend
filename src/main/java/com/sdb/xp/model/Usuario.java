package com.sdb.xp.model;

//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"id","nombre"})})
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String email;

    
    private String conocimientos;
    
}
