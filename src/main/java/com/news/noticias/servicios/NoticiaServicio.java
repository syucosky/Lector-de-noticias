package com.news.noticias.servicios;

import com.news.noticias.entidades.Noticia;
import com.news.noticias.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpoNoticia) throws Exception{
        if(titulo.isEmpty() || titulo == null){
            throw new Exception("Falta el titulo");
        }
        if(cuerpoNoticia.isEmpty() || cuerpoNoticia == null){
            throw new Exception("Falta el cuerpo de la noticia");
        }
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpoNoticia(cuerpoNoticia);
        
        noticiaRepositorio.save(noticia);
    }
    
    public List<Noticia> listarNoticias(){
        
        List<Noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAll();
        
        return noticias;
    }
    
    public Noticia buscarNoticia(String titulo) throws Exception{
              
        Noticia noticia = noticiaRepositorio.buscarPorTitulo(titulo);
        
        if(noticia == null){
            throw new Exception("No existe noticia con ese titulo");
        }else{
            return noticia;
        }
    }
    
    @Transactional
    public void modificarNoticia(String titulo, String nuevoTitulo, String nuevoCuerpo) throws Exception{
        
        Noticia noticia = buscarNoticia(titulo);
        if(noticia != null){                
            noticia.setTitulo(nuevoTitulo);
            noticia.setCuerpoNoticia(nuevoCuerpo);
            
            noticiaRepositorio.save(noticia);
        }else{
            throw new Exception("noticia no existe");
        }     
    }
}
