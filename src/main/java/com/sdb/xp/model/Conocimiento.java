package com.sdb.xp.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Conocimiento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;
    
    private String nivel;
    private String tiempo;
}
