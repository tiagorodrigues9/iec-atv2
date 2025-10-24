package com.br.fatec.at1_t1_tiagodelararodrigues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerIT {

    @Autowired
    private MockMvc mockMvc; // Objeto para simular requisições HTTP

    @Test
    void deveRetornarMensagemHelloQuandoGetHelloEndpoint() throws Exception {
        // Simulamos uma chamada GET para /api/hello
        mockMvc.perform(get("/atv/hello"))
               // Verificamos se o status da resposta é 200 OK
               .andExpect(status().isOk())
               .andExpect(content().string("Tiago de Lara Rodrigues"));
    }

}