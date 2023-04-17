package com.tonna7sa.ejrciciobiblioteca.controladora;

import com.tonna7sa.ejrciciobiblioteca.entidades.Editorial;
import com.tonna7sa.ejrciciobiblioteca.exception.miexception;
import com.tonna7sa.ejrciciobiblioteca.servicios.EditorialServicio;
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
@RequestMapping("/editorial") // localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio es;

    @GetMapping("/registrar") // localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_forms.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        // System.out.println("Nombre: "+nombre);
        try {
            es.CrearEditorial(nombre);
            modelo.put("exito", "La editorial ha sido cargada correctamente");
        } catch (miexception ex) {
            modelo.put("fracaso", ex.getMessage());
            // Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_forms.html";
        }
        return "editorial_forms.html";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        List<Editorial> editoriales = es.ListarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_list.html";
    }

    @GetMapping("/listarm")
    public String listarm(ModelMap modelo) {
        List<Editorial> editoriales = es.ListarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_listm.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", es.getone(id));
        return "editorial_modify.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, RedirectAttributes redirectAttributes) {
        try {
            es.EditarEditorial(id, nombre);
            redirectAttributes.addFlashAttribute("exito", "La editorial ha sido modificada correctamente");
            
        } catch (miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso", "La editorial no puede ser modificada");
            return "redirect:../listarm";
        }
        return "redirect:../listarm"; 
    }
@GetMapping("/listarb")
    public String listarb(ModelMap modelo) {
        List<Editorial> editoriales = es.ListarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_listb.html";
    }
    
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable String id, ModelMap modelo ){
    modelo.put("editorial", es.getone(id)); 
        return "editorial_borrar.html";
    }
 
    @PostMapping("/borrar/{id}")
    public String borrar(@PathVariable String id, RedirectAttributes redirectAttributes){
        try {
            es.BorrarEditorial(id);
            redirectAttributes.addFlashAttribute("exito", "La editorial ha sido eliminada");
            return "redirect:../listarb"; 
        } catch (miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso", ex.getMessage()); 
            return "redirect:../listarb";
        }
    }
}

