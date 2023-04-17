package com.tonna7sa.ejrciciobiblioteca.repositorio;

import com.tonna7sa.ejrciciobiblioteca.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    @Query("select l from Libro l where l.titulo  = :titulo")
    public Libro BuscarPorTitulo(@Param("titulo") String titulo);

    @Query("select l from Libro l where l.autor.nombre = :nombre")
    public List<Libro> BuscarPorAutor(@Param("nombre") String nombre);

    @Query("select l from Libro l where l.editorial.nombre = :nombre")
    public List<Libro> BuscarPorEditorial(@Param("nombre") String nombre);
}
