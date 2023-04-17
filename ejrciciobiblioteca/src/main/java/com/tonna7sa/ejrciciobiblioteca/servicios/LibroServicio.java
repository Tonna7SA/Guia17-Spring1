package com.tonna7sa.ejrciciobiblioteca.servicios;

import com.tonna7sa.ejrciciobiblioteca.entidades.Autor;
import com.tonna7sa.ejrciciobiblioteca.entidades.Editorial;
import com.tonna7sa.ejrciciobiblioteca.entidades.Libro;
import com.tonna7sa.ejrciciobiblioteca.exception.miexception;
import com.tonna7sa.ejrciciobiblioteca.repositorio.AutorRepositorio;
import com.tonna7sa.ejrciciobiblioteca.repositorio.EditorialRepositorio;
import com.tonna7sa.ejrciciobiblioteca.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class LibroServicio {

    @Autowired
    private LibroRepositorio lp;

    @Autowired
    private AutorRepositorio ap;

    @Autowired
    private EditorialRepositorio ep;

    public void CrearLibro(Long isbn, String titulo, Integer ejemplares, String idautor, String ideditorial) throws miexception {

        validaciones(isbn, titulo, ejemplares, idautor, ideditorial);

        Optional<Autor> respuestaautor = ap.findById(idautor);
        Optional<Editorial> respuestaeditorial = ep.findById(ideditorial);
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if (respuestaautor.isPresent()) {
            autor = respuestaautor.get();
        }

        if (respuestaeditorial.isPresent()) {
            editorial = respuestaeditorial.get();
        }
        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        lp.save(libro);
        System.out.println(libro.toString());

    }

    public List<Libro> ListarLibros() {
        List<Libro> libros = new ArrayList();
        libros = lp.findAll();
        return libros;
    }

   @Transactional
    public void EditarLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws miexception{
        
                
        Optional<Libro> respuesta = lp.findById(isbn);
       
        Optional<Autor> respuestaAutor = ap.findById(idAutor);
        
        Optional<Editorial> respuestaEditorial = ep.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial= new Editorial();
        
        if(respuestaAutor.isPresent()){
            
            autor = respuestaAutor.get();
        }
        
        if(respuestaEditorial.isPresent()){
            
            editorial = respuestaEditorial.get();
        }
        
        if(respuesta.isPresent()){
            
            validaciones(isbn, titulo, ejemplares, idAutor, idEditorial);
            
            
            Libro libro = respuesta.get();
                     
            libro.setTitulo(titulo);
            
            libro.setEjemplares(ejemplares);
            
            libro.setAutor(autor);
            
            libro.setEditorial(editorial);
            
            lp.save(libro);
            
        }
    }

    private void validaciones(Long isbn, String titulo, Integer ejemplares, String idautor, String ideditorial) throws miexception {

        if (isbn == null) {
            throw new miexception("El isbn no puede ser nulo o estar vacio");
        }
        if (titulo.isEmpty()) {
            throw new miexception("El titulo esta vacio o es nulo");
        }
        if (ejemplares == null) {
            throw new miexception("Los ejemplares no pueden ser nulos o vacios");
        }
        if (idautor.isEmpty() || idautor == null) {
            throw new miexception("El autor no pueden ser nulo o estar vacio");
        }
        if (ideditorial.isEmpty() || ideditorial == null) {
            throw new miexception("La editorial no pueden ser nula o estar vacia");
        }
    }

    public Libro getone(Long isbn) {
        return lp.getOne(isbn);
    }

    public void BorrarLibro(Long isbn) throws miexception {

        Optional<Libro> respuesta = lp.findById(isbn);
        Libro libro = new Libro();
        if (respuesta.isPresent()) {
            libro = respuesta.get();
       
        lp.delete(libro);
    }
    }
}
