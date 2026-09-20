import operacao.aritmetica.*;

import exception.ParametroInvalidoException;
import model.Entrada;
import model.Resultado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SomaTest {

    @Test
    void deveSomarDoisNumerosPositivos() {
        Soma soma = new Soma();
        Entrada entrada = new Entrada();
        entrada.adicionar("a", 5);
        entrada.adicionar("b", 3);

        Resultado resultado = soma.executar(entrada);

        assertEquals(8.0, resultado.getValor("soma"), 0.0001);
    }

    @Test
    void deveSomarNumerosNegativos() {
        Soma soma = new Soma();
        Entrada entrada = new Entrada();
        entrada.adicionar("a", -10);
        entrada.adicionar("b", -5);

        Resultado resultado = soma.executar(entrada);

        assertEquals(-15.0, resultado.getValor("soma"), 0.0001);
    }

    @Test
    void deveFalharQuandoFaltaUmParametro() {
        Soma soma = new Soma();
        Entrada entrada = new Entrada();
        entrada.adicionar("a", 5);

        assertThrows(ParametroInvalidoException.class, () -> {
            soma.executar(entrada);
        });
    }



}
