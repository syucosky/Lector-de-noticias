package com.news.noticias.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Modificar")
public class ModificarControlador {
    
    @GetMapping("{titulo}")
    public String modificar(@PathVariable("titulo") String titulo ){
        
        
        return "redirect::index.html";
    }
    
    
}
