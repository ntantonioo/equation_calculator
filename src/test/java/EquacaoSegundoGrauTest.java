import operacao.segundoGrau.EquacaoSegundoGrau;

import exception.ParametroInvalidoException;
import model.Entrada;
import model.Resultado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquacaoSegundoGrauTest {

    private Entrada montar(double a, double b, double c) {
        Entrada entrada = new Entrada();
        entrada.adicionar("a", a);
        entrada.adicionar("b", b);
        entrada.adicionar("c", c);
        return entrada;
    }

    @Test
    void deveEncontrarDuasRaizesQuandoDeltaPositivo() {
        EquacaoSegundoGrau equacao = new EquacaoSegundoGrau();

        Resultado resultado = equacao.executar(montar(1, -5, 6));

        assertEquals(3.0, resultado.getValor("x1"), 0.0001);
        assertEquals(2.0, resultado.getValor("x2"), 0.0001);
    }

    @Test
    void deveEncontrarUmaRaizQuandoDeltaZero() {
        EquacaoSegundoGrau equacao = new EquacaoSegundoGrau();

        Resultado resultado = equacao.executar(montar(1, -2, 1));

        assertEquals(1.0, resultado.getValor("x"), 0.0001);
    }

    @Test
    void naoDeveTerRaizesReaisQuandoDeltaNegativo() {
        EquacaoSegundoGrau equacao = new EquacaoSegundoGrau();

        Resultado resultado = equacao.executar(montar(1, 0, 1));

        assertTrue(resultado.getValores().isEmpty());
    }

    @Test
    void deveFalharQuandoAForZero() {
        EquacaoSegundoGrau equacao = new EquacaoSegundoGrau();

        assertThrows(ParametroInvalidoException.class, () -> {
            equacao.executar(montar(0,2,3));
        });
    }
}
