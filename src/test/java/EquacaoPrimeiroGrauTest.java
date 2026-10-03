import model.Entrada;
import model.Resultado;
import operacao.primeiroGrau.EquacaoPrimeiroGrau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquacaoPrimeiroGrauTest {

    private Entrada montar(double a, double b) {
        Entrada entrada = new Entrada();
        entrada.adicionar("a", a);
        entrada.adicionar("b", b);
        return entrada;
    }

    @Test
    void deveEncontrarSolucaoUnicaQuandoAForDiferenteDeZero() {
        EquacaoPrimeiroGrau equacao = new EquacaoPrimeiroGrau();

        // 2x - 10 = 0  ->  x = 5
        Resultado resultado = equacao.executar(montar(2, -10));

        assertEquals(5.0, resultado.getValor("x"), 0.0001);
    }

    @Test
    void deveTerInfinitasSolucoesQuandoAEBForemZero() {
        EquacaoPrimeiroGrau equacao = new EquacaoPrimeiroGrau();

        // 0x + 0 = 0  ->  0 = 0, verdadeiro sempre
        Resultado resultado = equacao.executar(montar(0, 0));

        // Nao existe um valor de x para mostrar -- a resposta e a mensagem,
        // nao um numero. Mesma ideia do Delta negativo na equacao do 2o grau.
        assertTrue(resultado.getValores().isEmpty());
        assertTrue(resultado.getMensagem().contains("Infinitas"));
    }

    @Test
    void naoDeveTerSolucaoQuandoAForZeroEBForDiferenteDeZero() {
        EquacaoPrimeiroGrau equacao = new EquacaoPrimeiroGrau();

        // 0x + 5 = 0  ->  5 = 0, sempre falso
        Resultado resultado = equacao.executar(montar(0, 5));

        assertTrue(resultado.getValores().isEmpty());
        assertTrue(resultado.getMensagem().contains("Nenhuma"));
    }

    @Test
    void deveAceitarResultadoNegativo() {
        EquacaoPrimeiroGrau equacao = new EquacaoPrimeiroGrau();

        // 3x + 9 = 0  ->  x = -3
        Resultado resultado = equacao.executar(montar(3, 9));

        assertEquals(-3.0, resultado.getValor("x"), 0.0001);
    }
}