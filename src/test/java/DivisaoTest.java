import operacao.aritmetica.*;

import exception.ParametroInvalidoException;
import model.Entrada;
import model.Resultado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DivisaoTest {

    @Test
    void deveDividirDoisNumeros() {
        Divisao divisao = new Divisao();
        Entrada entrada = new Entrada();
        entrada.adicionar("a", 10);
        entrada.adicionar("b", 2);

        Resultado resultado = divisao.executar(entrada);

        assertEquals(5.0, resultado.getValor("divisao"), 0.0001);
    }

    @Test
    void deveFalharQuandoDivisorForZero() {
        Divisao divisao = new Divisao();
        Entrada entrada = new Entrada();
        entrada.adicionar("a", 10);
        entrada.adicionar("b", 0);

        assertThrows(ParametroInvalidoException.class, () -> {
            divisao.executar(entrada);
        });
    }

    @Test
    void deveGerarResultadoComCasasDecimais() {
        Divisao divisao = new Divisao();
        Entrada entrada = new Entrada();
        entrada.adicionar("a", 10);
        entrada.adicionar("b", 3);

        Resultado resultado = divisao.executar(entrada);

        assertEquals(3.3333, resultado.getValor("divisao"), 0.0001);
    }
}