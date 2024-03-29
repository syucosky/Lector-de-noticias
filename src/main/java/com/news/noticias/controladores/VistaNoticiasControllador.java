package com.news.noticias.controladores;

import com.news.noticias.entidades.Noticia;
import com.news.noticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
@RequestMapping("/VistaNoticia")
public class VistaNoticiasControllador {
    
    @Autowired
    NoticiaServicio noticiaServicio;
    
    @GetMapping("/{titulo}")
    public String verNoticia(@PathVariable("titulo") String titulo, ModelMap modelo){
        try {
            Noticia noticia = noticiaServicio.buscarNoticia(titulo);
            modelo.addAttribute("noticia",noticia);
        } catch (Exception e) {
            return "inicio.html";
        }
              
        return "VistaNoticia.html";
    }
    
    
}
