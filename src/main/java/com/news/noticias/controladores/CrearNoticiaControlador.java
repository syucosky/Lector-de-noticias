
package com.news.noticias.controladores;

import com.news.noticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/NuevaNoticia")
public class CrearNoticiaControlador {
     
    @Autowired
    NoticiaServicio noticiaServicio;
    
    @GetMapping("/crear")
    public String crearNoticia(String titulo, String cuerpo){
        
        return "crear_noticia.html";
    }
}
