package com.news.noticias.controladores;

import com.news.noticias.entidades.Noticia;
import com.news.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class NoticiaControlador {
    

    @Autowired
    private NoticiaServicio noticiaServicio;
    

    @GetMapping("")
    public String listaNoticias(ModelMap modelo){
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        return "index.html";
    }
    
}
