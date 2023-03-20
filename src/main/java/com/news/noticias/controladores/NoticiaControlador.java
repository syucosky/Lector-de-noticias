package com.news.noticias.controladores;

import com.news.noticias.entidades.Noticia;
import com.news.noticias.entidades.Usuario;
import com.news.noticias.excepciones.MiExcepcion;
import com.news.noticias.servicios.NoticiaServicio;
import com.news.noticias.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/")
public class NoticiaControlador {
    

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private UsuarioServicio usuarioService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String listaNoticias(ModelMap modelo, HttpSession sesion){
        
        Usuario logueado = (Usuario) sesion.getAttribute("usuarioSesion");
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        
        return "/inicio";
    }
    
    @GetMapping("/borrar/{titulo}")
    public String borrarNoticia(@PathVariable("titulo") String titulo){
         
        try {
            noticiaServicio.borrarNoticia(titulo);
        } catch (Exception e) {
        }
        
        return "redirect:/inicio";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "/registrar.html";
    }
    @PostMapping("/registrar/crear")
    public String crear(@RequestParam("nombre") String nombre,
                        @RequestParam("contrasenia") String contrasenia,
                        @RequestParam("contrasenia2") String contrasenia2,
                        @RequestParam("email")String email,
                        @RequestParam("imagen")MultipartFile img,
                        ModelMap modelo) throws Exception{
        try {
            usuarioService.crearUsuario(nombre, contrasenia, contrasenia2, email,img);
            modelo.put("exito","El registro se completo");
        } catch (MiExcepcion e) {
            modelo.put("error", e.getMessage());
            return "Registrar.html";
        }
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if(error != null){
            modelo.put("error","usuario o contraseña invalido");
        }
        return "/login.html";
    }
    
    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> imagen(@PathVariable Long id){
        Usuario usuario = usuarioService.getOne(id);
        
        byte[] imagen = usuario.getImagen().getContenido();
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.IMAGE_JPEG);
          
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}
