package com.br.fatec.at1_t1_tiagodelararodrigues;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atv1")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Tiago de Lara Rodrigues";
    }

}