

package com.news.noticias.servicios;

import com.news.noticias.entidades.Imagen;
import com.news.noticias.entidades.Usuario;
import com.news.noticias.enumeraciones.Rol;
import com.news.noticias.excepciones.MiExcepcion;
import com.news.noticias.repositorios.ImagenRepositorio;
import com.news.noticias.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    UsuarioRepositorio usuarioRepo;
    
    @Autowired
    ImagenServicio imagenServicio;
    
    @Transactional
    public void crearUsuario(String nombre, String contrasenia,String contrasenia2, String email, MultipartFile img) throws MiExcepcion, Exception{
        
        validar(nombre,contrasenia,contrasenia2,email);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        
        usuario.setContrasenia(new BCryptPasswordEncoder().encode(contrasenia));
        
        usuario.setEmail(email);
        
        usuario.setRol(Rol.USER);
        
        Imagen imagen = imagenServicio.guardar(img);
        
        usuario.setImagen(imagen);
        
        usuarioRepo.save(usuario);
    }
    
 
    
    private void validar(String nombre, String contrasenia,String contrasenia2, String email) throws MiExcepcion{
        
        if(nombre.isEmpty() || nombre == null || nombre.length() < 4){
            if(nombre.length() > 4){
                throw new MiExcepcion("El nombre no puede estar vacio");
            }else{
                throw new MiExcepcion("El nombre debe tener mas de 4 letras");
            }
        }          
        
        if(email.isEmpty() || email == null){
            throw new MiExcepcion("El email no puede estar vacio");
        }
        
        if(contrasenia.isEmpty() || contrasenia == null || contrasenia.length() < 5){
            if(contrasenia.length() > 5){
                throw new MiExcepcion("La contraseña no puede estar vacia");
            }else{
                throw new MiExcepcion("La contraseña debe tener mas de 5 digitos");
            }           
        }  
        if(!contrasenia.equals(contrasenia2)){
            throw new MiExcepcion("Las contraseñas deben coincidir");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepo.buscarPorEmail(email);
        
        if(usuario != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); // ROLE_USER
            
            permisos.add(p);
            
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession sesion = attr.getRequest().getSession(true);
            
            sesion.setAttribute("usuarioSesion", usuario);
            
                       
            return new User(usuario.getEmail(), usuario.getContrasenia(), permisos);
            
        }else{
            return null;
        }   
    }
    
    public Usuario getOne(Long id){      
        return usuarioRepo.getOne(id);
    }
}
