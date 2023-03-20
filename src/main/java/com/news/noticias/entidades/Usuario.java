
package com.news.noticias.entidades;

import com.news.noticias.enumeraciones.Rol;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private String contrasenia;
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToOne
    private Imagen imagen;
    
    
    public Usuario(String nombre, String contrasenia, String email, Rol rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
        this.rol = rol;
    }
    public Usuario(){
        
    }
    
}
