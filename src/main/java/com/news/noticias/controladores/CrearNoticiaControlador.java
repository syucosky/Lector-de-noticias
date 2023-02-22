
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
    
    @GetMapping("")
    public String crearNoticia(){        
        return "NuevaNoticia.html";
    }
    
    @PostMapping("/guardar")
    public String guardarNoticia(@RequestParam String titulo,@RequestParam String cuerpo) throws Exception{       
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo);
            return "redirect:/";
        } catch (Exception e) {
            return "NuevaNoticia.html";
        }        
    }
}
