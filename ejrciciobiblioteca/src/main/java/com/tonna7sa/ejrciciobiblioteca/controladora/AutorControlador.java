package com.tonna7sa.ejrciciobiblioteca.controladora;

import com.tonna7sa.ejrciciobiblioteca.entidades.Autor;
import com.tonna7sa.ejrciciobiblioteca.exception.miexception;
import com.tonna7sa.ejrciciobiblioteca.servicios.AutorServicio;
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
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio as;

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_forms.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        // System.out.println("Nombre: "+nombre);
        try {
            as.CrearAutor(nombre);
            modelo.put("exito", "El autor ha sido cargado correctamente");
        } catch (miexception ex) {
            modelo.put("fracaso", ex.getMessage());
            // Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_forms.html";
        }
        return "autor_forms.html";
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        List<Autor> autores = as.ListarAutores();
        modelo.addAttribute("autores",autores);
        return "autor_list.html";
    }
    
    @GetMapping("/listarm")
    public String listarm(ModelMap modelo) {
        List<Autor> autores = as.ListarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_listm.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo ){
    modelo.put("autor", as.getone(id)); 
        return "autor_modify.html";
    }
 
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, RedirectAttributes redirectAttributes){
        try {
            as.EditarAutor(id, nombre);
            redirectAttributes.addFlashAttribute("exito","El autor se modifico correctamente !!!");
            return "redirect:../listarm"; 
        } catch (miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso","El autor no puede ser nulo o estar vacio !!!"); 
            return "redirect:../listarm";
        }
    }
    
    @GetMapping("/listarb")
    public String listarb(ModelMap modelo) {
        List<Autor> autores = as.ListarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_listb.html";
    }
    
    @GetMapping("/borrar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo ){
    modelo.put("autor", as.getone(id)); 
        return "autor_borrar.html";
    }
 
    @PostMapping("/borrar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes redirectAttributes){
        try {
            as.BorrarAutor(id);
            redirectAttributes.addFlashAttribute("exito", "El autor ha sido eliminado");
            return "redirect:../listarb"; 
        } catch (miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso", ex.getMessage()); 
            return "redirect:../listarb";
        }
    }
}
