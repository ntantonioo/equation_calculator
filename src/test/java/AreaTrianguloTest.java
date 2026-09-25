import operacao.geometriaPlana.AreaTriangulo;

import exception.ParametroInvalidoException;
import model.Entrada;
import model.Resultado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AreaTrianguloTest {

    private Entrada montar(double a, double b, double c) {
        Entrada entrada = new Entrada();
        entrada.adicionar("ladoA", a);
        entrada.adicionar("ladoB", b);
        entrada.adicionar("ladoC", c);
        return entrada;
    }

    @Test
    void deveCalcularAreaDoTrianguloRetangulo3_4_5() {
        AreaTriangulo areaTriangulo = new AreaTriangulo();

        Resultado resultado = areaTriangulo.executar(montar(3,4,5));

        assertEquals(6.0, resultado.getValor("area"), 0.0001);
    }

    @Test
    void deveFalharQuandoLadosNaoFormamTriangulo() {
        AreaTriangulo areaTriangulo = new AreaTriangulo();

        assertThrows(ParametroInvalidoException.class, () -> {
            areaTriangulo.executar(montar(1,2,10));
        });
    }

    @Test
    void deveFalharComLadoNegativo() {
        AreaTriangulo areaTriangulo = new AreaTriangulo();

        assertThrows(ParametroInvalidoException.class, () -> {
            areaTriangulo.executar(montar(3, -4, 5));
        });
    }

    @Test
    void deveFalharQuandoSomaDoisLadosIgualaOTerceiro() {
        AreaTriangulo areaTriangulo = new AreaTriangulo();

        assertThrows(ParametroInvalidoException.class, () -> {
            areaTriangulo.executar(montar(1,2,10));
        });
    }
}
