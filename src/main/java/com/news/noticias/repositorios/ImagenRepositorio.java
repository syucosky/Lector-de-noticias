
package com.news.noticias.repositorios;

import com.news.noticias.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Long>{
    
} 

