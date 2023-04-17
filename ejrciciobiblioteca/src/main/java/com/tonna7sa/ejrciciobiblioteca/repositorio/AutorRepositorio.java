/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonna7sa.ejrciciobiblioteca.repositorio;

import com.tonna7sa.ejrciciobiblioteca.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tonna/SA FR34K
 */
@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    

}
