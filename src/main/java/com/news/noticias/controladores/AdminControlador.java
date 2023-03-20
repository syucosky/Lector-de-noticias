
package com.news.noticias.controladores;

import com.news.noticias.entidades.Noticia;
import com.news.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    NoticiaServicio noticiaServicio;
    
    @GetMapping("/dashboard")
    public String panelAdm(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        return "panel.html";
    }
}
