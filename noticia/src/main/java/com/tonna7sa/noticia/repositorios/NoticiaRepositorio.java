/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonna7sa.noticia.repositorios;

import com.tonna7sa.noticia.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tonna/SA FR34K
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long> {

    @Query(value = "select * from Noticia order by id desc", nativeQuery = true)
    List<Noticia> findAllOrderByidDesc();
    
    @Query(value = "select * from Noticia order by id asc", nativeQuery = true)
    List<Noticia> findAllOrderByidAsc();
      
}
