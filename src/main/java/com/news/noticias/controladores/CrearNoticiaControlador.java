
package com.news.noticias.controladores;

import com.news.noticias.excepciones.MiExcepcion;
import com.news.noticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/NuevaNoticia")
public class CrearNoticiaControlador {
     
    @Autowired
    NoticiaServicio noticiaServicio;
    
    @GetMapping("")
    public String crearNoticia(){        
        return "NuevaNoticia.html";
    }
    
    @PostMapping("/guardar")
    public String guardarNoticia(@RequestParam String titulo,@RequestParam String cuerpo, ModelMap modelo) throws Exception{       
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La Noticia fue creada");
            return "redirect:/inicio";
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "NuevaNoticia.html";
        }        
    }
}
