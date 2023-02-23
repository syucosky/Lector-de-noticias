package com.news.noticias.controladores;

import com.news.noticias.entidades.Noticia;
import com.news.noticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Modificar")
public class ModificarControlador {

    @Autowired
    NoticiaServicio noticiaServicio;

    @GetMapping("{titulo}")
    public String modificar(@PathVariable("titulo") String titulo, ModelMap modelo) {

        try {
            modelo.put("noticia", noticiaServicio.buscarNoticia(titulo));
        } catch (Exception e) {
        }

        return "modificar.html";
    }

    @PostMapping("/{titulo}")
    public String modificarPost(@PathVariable("titulo") String titulo,
            @RequestParam String nuevoTitulo,
            @RequestParam String nuevoCuerpo) {
        try {
            if (nuevoTitulo.isEmpty() & nuevoCuerpo.isEmpty()) {
                String viejoTitulo = noticiaServicio.buscarNoticia(titulo).getTitulo();
                String viejoCuerpo = noticiaServicio.buscarNoticia(titulo).getCuerpoNoticia();
                System.out.println(viejoTitulo);
                System.out.println(viejoCuerpo);
                noticiaServicio.modificarNoticia(titulo, viejoTitulo, viejoCuerpo);
            } else if (nuevoTitulo.isEmpty()) {
                String viejoTitulo = noticiaServicio.buscarNoticia(titulo).getTitulo();
                noticiaServicio.modificarNoticia(titulo, viejoTitulo, nuevoCuerpo);
            } else if (nuevoCuerpo.isEmpty()) {
                String viejoCuerpo = noticiaServicio.buscarNoticia(titulo).getCuerpoNoticia();
                noticiaServicio.modificarNoticia(titulo, nuevoTitulo, viejoCuerpo);
            }else{
                noticiaServicio.modificarNoticia(titulo, nuevoTitulo, nuevoCuerpo);
            }
        } catch (Exception e) {
        }
        return "redirect:/";
    }

}
