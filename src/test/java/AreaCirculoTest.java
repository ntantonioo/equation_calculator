import operacao.geometriaPlana.AreaCirculo;

import exception.ParametroInvalidoException;
import model.Entrada;
import model.Resultado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AreaCirculoTest {

    @Test
    void deveCalcularAreaComRaioValido() {
        AreaCirculo areaCirculo = new AreaCirculo();
        Entrada entrada = new Entrada();
        entrada.adicionar("raio", 3);

        Resultado resultado = areaCirculo.executar(entrada);

        assertEquals(28.2743, resultado.getValor("area"), 0.0001);
    }

    @Test
    void deveFalharComRaioZero() {
        AreaCirculo areaCirculo = new AreaCirculo();
        Entrada entrada = new Entrada();
        entrada.adicionar("raio", 0);

        assertThrows(ParametroInvalidoException.class, () -> {
            areaCirculo.executar(entrada);
        });
    }

    @Test
    void deveFalharComRaioNegativo() {
        AreaCirculo areaCirculo = new AreaCirculo();
        Entrada entrada = new Entrada();
        entrada.adicionar("raio", -5);

        assertThrows(ParametroInvalidoException.class, () -> {
            areaCirculo.executar(entrada);
        });
    }
}
