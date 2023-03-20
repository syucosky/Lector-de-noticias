
package com.news.noticias.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String mime;
    
    private String nombre;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido; 
    
    public Imagen(){
        
    }

 
    
}
