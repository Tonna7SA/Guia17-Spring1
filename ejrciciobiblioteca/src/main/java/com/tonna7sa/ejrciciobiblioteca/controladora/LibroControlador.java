package com.tonna7sa.ejrciciobiblioteca.controladora;

import com.tonna7sa.ejrciciobiblioteca.entidades.Autor;
import com.tonna7sa.ejrciciobiblioteca.entidades.Editorial;
import com.tonna7sa.ejrciciobiblioteca.entidades.Libro;
import com.tonna7sa.ejrciciobiblioteca.exception.miexception;
import com.tonna7sa.ejrciciobiblioteca.servicios.AutorServicio;
import com.tonna7sa.ejrciciobiblioteca.servicios.EditorialServicio;
import com.tonna7sa.ejrciciobiblioteca.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio ls;
    @Autowired
    private AutorServicio as;
    @Autowired
    private EditorialServicio es;

    @GetMapping("/registrar") // localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = as.ListarAutores();
        List<Editorial> editoriales = es.ListarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_forms.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, String titulo,
            @RequestParam(required = false) Integer ejemplares, String idautor,
            String ideditorial, ModelMap modelo) {

        try {

            List<Autor> autores = as.ListarAutores();
            List<Editorial> editoriales = es.ListarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            ls.CrearLibro(isbn, titulo, ejemplares, idautor, ideditorial);
            System.out.println(idautor);
            System.out.println(ideditorial);
            System.out.println(isbn + titulo + ejemplares + idautor + ideditorial);
            modelo.put("exito", "El libro ha sido cargado correctamente");
        } catch (miexception ex) {
            List<Autor> autores = as.ListarAutores();
            List<Editorial> editoriales = es.ListarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("fracaso", ex.getMessage());
            // Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro_forms.html";
        }
        return "libro_forms.html";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        List<Libro> libros = ls.ListarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_list.html";
    }

    @GetMapping("/listarm")
    public String listarm(ModelMap modelo) {
        List<Libro> libros = ls.ListarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_listm.html";
    }

    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {
        modelo.put("libro", ls.getone(isbn));
        List<Autor> autores = as.ListarAutores();
        List<Editorial> editoriales = es.ListarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_modify.html";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@RequestParam(required = false) Long isbn, String titulo, @RequestParam(required = false) Integer ejemplares,
            String idautor, String ideditorial, RedirectAttributes redirectAttributes) {
        try {
            List<Autor> autores = as.ListarAutores();
            List<Editorial> editoriales = es.ListarEditoriales();

            redirectAttributes.addAttribute("autores", autores);
            redirectAttributes.addAttribute("editoriales", editoriales);
            redirectAttributes.addFlashAttribute("exito","El libro se modifico correctamente");

            ls.EditarLibro(isbn, titulo, ejemplares, idautor, ideditorial);
            return "redirect:../listar";
        } catch (miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso","El libro no puede ser modificado correctamente");
            return "redirect:../listarm";
        }
        
    }

    @GetMapping("/listarb")
    public String listarb(ModelMap modelo) {
        List<Libro> libros = ls.ListarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_listb.html";
    }

    @GetMapping("/borrar/{isbn}")
    public String borrar(@PathVariable Long isbn, ModelMap modelo) {
        modelo.put("libro", ls.getone(isbn));
        return "libro_borrar.html";
    }

    @PostMapping("/borrar/{isbn}")
    public String borrar(@PathVariable Long isbn, Integer ejemplares, String idautor, String ideditorial, 
            RedirectAttributes redirectAttributes) {
        try {
            ls.BorrarLibro(isbn);
            redirectAttributes.addFlashAttribute("exito", "El libro ha sido eliminado");
            
        } catch (miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso", "El libro no puede ser eliminado");
            return "libro_borrar.html";
        }
        return "redirect:../listarb";
    }
}
