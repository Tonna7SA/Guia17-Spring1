package com.tonna7sa.noticia.controladora;

import com.tonna7sa.noticia.entidades.Noticia;
import com.tonna7sa.noticia.excepciones.Miexception;
import com.tonna7sa.noticia.repositorios.NoticiaRepositorio;
import com.tonna7sa.noticia.servicios.NoticiaServicio;
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
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    NoticiaServicio ns;

    @Autowired
    NoticiaRepositorio nr;

    @GetMapping("/registrar")
    public String registrar() {
        return "formulario.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) throws Miexception {
        try {
            ns.CrearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia ha sido creada exitosamente");
            return "formulario.html";
        } catch (Miexception ex) {
            modelo.put("fracaso", ex.getMessage());
            return "formulario.html";
        }
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        List<Noticia> noticias = ns.ListarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "noticia_list.html";
    }

    @GetMapping("/listarm")
    public String listarm(ModelMap modelo) {
        List<Noticia> noticias = ns.ListarNoticias1();
        modelo.addAttribute("noticias", noticias);
        return "noticia_listm.html";
    }
    
    @GetMapping("/listarb")
    public String listarb(ModelMap modelo) {
        List<Noticia> noticias = ns.ListarNoticias1();
        modelo.addAttribute("noticias", noticias);
        return "noticia_listb.html";
    }

    @GetMapping("/vernoticia/{id}")
    public String vernoticia(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", nr.getOne(id));
        return "ver_noticia.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", nr.getOne(id));
        return "noticia_modify.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo, RedirectAttributes dirigir) throws Miexception {
        try {
            ns.ModificarNoticia(id, titulo, cuerpo);
            dirigir.addFlashAttribute("exito", "La noticia ha sido modificada exitosamente...");
            return "redirect:../listarm";
        } catch (Miexception ex) {
            dirigir.addFlashAttribute("fracaso", ex.getMessage());
            return "redirect:../listarm";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", nr.getOne(id));
        return "noticia_borrar.html";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) throws Miexception{
        try {
            ns.EliminarNoticia(id);
            redirectAttributes.addFlashAttribute("exito", "El autor ha sido eliminado");
            return "redirect:../listarb";
        } catch (Miexception ex) {
            redirectAttributes.addFlashAttribute("fracaso", ex.getMessage());
            return "redirect:../listarb";
        }

    }

}