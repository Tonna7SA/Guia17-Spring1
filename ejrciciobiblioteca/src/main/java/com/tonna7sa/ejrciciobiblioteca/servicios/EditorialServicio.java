package com.tonna7sa.ejrciciobiblioteca.servicios;

import com.tonna7sa.ejrciciobiblioteca.entidades.Editorial;
import com.tonna7sa.ejrciciobiblioteca.exception.miexception;
import com.tonna7sa.ejrciciobiblioteca.repositorio.EditorialRepositorio;
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
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio ep;
    @Autowired
    private LibroRepositorio lp;

    @Transactional
    public void CrearEditorial(String nombre) throws miexception {
        if (nombre.isEmpty() || nombre == null) {
            throw new miexception("La editorial no puede estar vacia o ser nula");
        }

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        ep.save(editorial);
    }

    public List<Editorial> ListarEditoriales() {

        List<Editorial> editoriales = new ArrayList();
        editoriales = ep.findAll();
        return editoriales;
    }

    public void EditarEditorial(String id, String nombre) throws miexception {

        if (nombre.isEmpty() || nombre == null) {
            throw new miexception("La editorial no puede estar vacia o ser nula");
        }

        Optional<Editorial> respuestaeditorial = ep.findById(id);

        if (respuestaeditorial.isPresent()) {
            Editorial editorial = respuestaeditorial.get();
            editorial.setNombre(nombre);

            ep.save(editorial);

        }
    }

    public Editorial getone(String id) {
        return ep.getOne(id);
    }

    public void BorrarEditorial(String id) throws miexception {
        try {
            Optional<Editorial> respuestaautor = ep.findById(id);
            if (lp.BuscarPorAutor(respuestaautor.get().getNombre()) != null) {
                if (respuestaautor.isPresent()) {
                    Editorial editorial = respuestaautor.get();
                    ep.delete(editorial);
                }
            }
        } catch (Exception ex) {
            throw new miexception("La Editorial tiene un libro asignado, por lo cual no se puede borrar...");
        }
    }
}
