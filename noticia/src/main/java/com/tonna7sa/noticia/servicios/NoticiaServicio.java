package com.tonna7sa.noticia.servicios;

import com.tonna7sa.noticia.entidades.Noticia;
import com.tonna7sa.noticia.excepciones.Miexception;
import com.tonna7sa.noticia.repositorios.NoticiaRepositorio;
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
public class NoticiaServicio {

    @Autowired
    NoticiaRepositorio nr;

    @Transactional
    public void CrearNoticia(String titulo, String cuerpo) throws Miexception {

        Validar(titulo, cuerpo);
        
        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());

        nr.save(noticia);

    }

    public List<Noticia> ListarNoticias() {
        
        List<Noticia> noticias = new ArrayList(); 
        noticias = nr.findAllOrderByidDesc();
        return noticias;
    }
    
    public List<Noticia> ListarNoticias1() {
        
        List<Noticia> noticias = new ArrayList(); 
        noticias = nr.findAllOrderByidAsc();
        return noticias;
    }

    @Transactional
    public void ModificarNoticia(Long id, String titulo, String cuerpo) throws Miexception {

        Validar(titulo, cuerpo);
        
        Optional<Noticia> respuesta = nr.findById(id);
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);

            nr.save(noticia);
        }
    }

    public void EliminarNoticia(Long id) throws Miexception {
        
        Optional<Noticia> respuesta = nr.findById(id);
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();

            nr.delete(noticia);
        }
    }
    
    public Noticia getone(Long id){
        return nr.getOne(id);
    }
    
    private void Validar(String titulo, String cuerpo) throws Miexception{
        
        if(titulo.isEmpty() || titulo == null){
            throw new Miexception("El titulo no puede estar vacio o ser nulo");
        }
        if(cuerpo.isEmpty() || cuerpo == null){
            throw new Miexception("El cuerpo no puede estar vacio o ser nulo");
        }
    }
}
