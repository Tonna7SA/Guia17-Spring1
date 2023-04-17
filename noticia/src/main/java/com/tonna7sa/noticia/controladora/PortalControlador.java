
package com.tonna7sa.noticia.controladora;

import com.tonna7sa.noticia.entidades.Noticia;
import com.tonna7sa.noticia.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    NoticiaServicio ns;
    
        
    @GetMapping("/")
    public String index(ModelMap modelo){
        List<Noticia> noticias = ns.ListarNoticias();
        modelo.put("noticias", noticias);
        return "index.html";
    }

}
