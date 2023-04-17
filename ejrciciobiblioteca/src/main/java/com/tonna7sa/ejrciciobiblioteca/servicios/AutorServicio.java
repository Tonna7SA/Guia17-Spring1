
package com.tonna7sa.ejrciciobiblioteca.servicios;

import com.tonna7sa.ejrciciobiblioteca.entidades.Autor;
import com.tonna7sa.ejrciciobiblioteca.exception.miexception;
import com.tonna7sa.ejrciciobiblioteca.repositorio.AutorRepositorio;
import com.tonna7sa.ejrciciobiblioteca.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio ap;

    @Autowired
    private LibroRepositorio lp;
    
    @Transactional
    public void CrearAutor(String nombre) throws miexception{
        if(nombre.isEmpty() || nombre == null){
            throw new miexception("El autor no puede estar vacio o ser nulo");
        }
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        
        ap.save(autor);
       
    }
    
    public List<Autor> ListarAutores(){
        List<Autor> autores = new ArrayList();
        autores = ap.findAll();
        return autores;
    }
    
    public void EditarAutor(String id, String nombre) throws miexception{
        
        if(nombre.isEmpty() || nombre == null){
            throw new miexception("El autor no puede estar vacio o ser nulo");
        }
        
        Optional<Autor> respuestaautor = ap.findById(id);
        
        if(respuestaautor.isPresent()){
            
            Autor autor = respuestaautor.get();
            autor.setNombre(nombre);
            
            ap.save(autor);
        }
    }
    public Autor getone(String id){
        return ap.getOne(id);
    }
    
    public void BorrarAutor(String id) throws miexception{
        try {
            Optional<Autor> respuestaautor = ap.findById(id);
            if (lp.BuscarPorAutor(respuestaautor.get().getNombre()) != null) {
                if (respuestaautor.isPresent()) {
                    Autor autor = respuestaautor.get();
                    ap.delete(autor);
                }
            }
        }catch(Exception ex){
            throw new miexception("El autor tiene un libro asignado, por lo cual no se puede borrar...");
    }
}
            
            
            
            
            
    
    
}
