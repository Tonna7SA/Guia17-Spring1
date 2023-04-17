package com.tonna7sa.ejrciciobiblioteca.controladora;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Controller
@RequestMapping("/") // localhost:8080/
public class PortalConrtrolador {

    @GetMapping("/")
    public String index() {

        return "index.html";
    }
}
