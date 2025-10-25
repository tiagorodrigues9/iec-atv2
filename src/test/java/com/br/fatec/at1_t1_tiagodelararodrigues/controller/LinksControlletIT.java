package com.br.fatec.at1_t1_tiagodelararodrigues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LinksController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LinksControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarTodosOsLinksQuandoGetLinksEndpoint() throws Exception {
        mockMvc.perform(get("/atv/links"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].curso").value("Programação de dispositivos móveis"))
                .andExpect(jsonPath("$[1].curso").value("Técnicas de programação 1"));
    }

    @Test
    void deveRetornarLinkQuandoGetLinkPorIdEndpointComIdExistente() throws Exception {
        mockMvc.perform(get("/atv/links/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.curso").value("Programação de dispositivos móveis"));
    }

    @Test
    void deveRetornarVazioQuandoGetLinkPorIdEndpointComIdInexistente() throws Exception {
        mockMvc.perform(get("/atv/links/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deveCriarNovoLinkQuandoPostLinksEndpoint() throws Exception {
        String novoLink = """
                {
                    "curso": "Nutrição"
                }
                """;

        mockMvc.perform(post("/atv/links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoLink))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.curso").value("Nutrição"));
    }

    @Test
    void deveAtualizarLinkCompletamenteQuandoPutLinksEndpoint() throws Exception {
        String linkAtualizado = """
                {
                    "curso": "Programação Avançada"
                }
                """;

        mockMvc.perform(put("/atv/links/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(linkAtualizado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.curso").value("Programação Avançada"));
    }

    @Test
    void deveAtualizarLinkParcialmenteQuandoPatchLinksEndpoint() throws Exception {
        String atualizacaoParcial = """
                {
                    "curso": "Curso Parcialmente Atualizado"
                }
                """;

        mockMvc.perform(patch("/atv/links/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizacaoParcial))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.curso").value("Curso Parcialmente Atualizado"));
    }

    @Test
    void deveRetornarVazioQuandoPatchLinksEndpointComIdInexistente() throws Exception {
        String atualizacaoParcial = """
                {
                    "curso": "Teste"
                }
                """;

        mockMvc.perform(patch("/atv/links/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizacaoParcial))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deveDeletarLinkQuandoDeleteLinksEndpoint() throws Exception {
        mockMvc.perform(delete("/atv/links/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Link 1 removido"));
    }

}
