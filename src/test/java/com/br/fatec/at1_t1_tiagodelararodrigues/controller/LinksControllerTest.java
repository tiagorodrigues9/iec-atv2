package com.br.fatec.at1_t1_tiagodelararodrigues;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários simples para o LinksController.
 *
 * Esta classe testa os métodos do controller diretamente,
 * sem usar o contexto do Spring ou simulação HTTP.
 *
 * Ideal para testar a lógica de negócio isoladamente.
 */
class LinksControllerTest {

    private final LinksController controller = new LinksController();

    // 1. Teste para listar todos os links
    @Test
    void deveRetornarTodosOsLinks() {
        List<Map<String, String>> links = controller.getLinks();
        assertEquals(2, links.size());
        assertEquals("Desenvolvimento de sistemas", links.get(0).get("curso"));
    }

    // 2. Teste para buscar link por ID existente
    @Test
    void deveRetornarLinkQuandoIdExiste() {
        Map<String, String> link = controller.getLinkById(1);
        assertEquals("1", link.get("id"));
        assertEquals("Desenvolvimento de sistemas", link.get("curso"));
    }

    // 3. Teste para buscar link por ID inexistente
    @Test
    void deveRetornarNullQuandoIdNaoExiste() {
        Map<String, String> link = controller.getLinkById(999);
        assertEquals(null, link);
    }

    // 4. Teste para criar novo link
    @Test
    void deveCriarNovoLinkComIdGerado() {
        Map<String, String> novoLink = Map.of("curso", "Nutrição");
        Map<String, String> resultado = controller.createLink(novoLink);

        assertEquals("Nutrição", resultado.get("curso"));
        assertEquals("3", resultado.get("id")); // Próximo ID
    }

    // 5. Teste para atualizar link existente
    @Test
    void deveAtualizarLinkExistenteCompletamente() {
        Map<String, String> dadosAtualizacao = Map.of("curso", "Novo Curso");
        Map<String, String> resultado = controller.updateLink(1, dadosAtualizacao);

        assertEquals("Novo Curso", resultado.get("curso"));
    }

    // 6. Teste para atualizar parcialmente (PATCH)
    @Test
    void deveAtualizarLinkParcialmente() {
        Map<String, String> dadosParciais = Map.of("curso", "Curso Atualizado");
        Map<String, String> resultado = controller.patchLink(2, dadosParciais);

        assertEquals("Curso Atualizado", resultado.get("curso"));
    }

    // 7. Teste para PATCH com link inexistente
    @Test
    void deveRetornarNullQuandoPatchLinkInexistente() {
        Map<String, String> dadosParciais = Map.of("curso", "Teste");
        Map<String, String> resultado = controller.patchLink(999, dadosParciais);

        assertEquals(null, resultado);
    }

    // 8. Teste para deletar link
    @Test
    void deveDeletarLinkERetornarMensagemConfirmacao() {
        String resultado = controller.deleteLink(1);
        assertEquals("Link 1 removido", resultado);
    }

}
