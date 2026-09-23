package operacao.geometria;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

/**
 * Calcula a area do triangulo a partir dos 3 lados
 * Regra de negócio: desigualdade triangular
 */
public class AreaTriangulo extends OperacaoBase {

    public AreaTriangulo() {
        super("Area do Triangulo", Categoria.GEOMETRIA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("lado a", "primeiro lado"));
        lista.add(new Parametro("lado b", "segundo lado"));
        lista.add(new Parametro("lado c", "terceiro lado"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double a = entrada.get("ladoA");
        double b = entrada.get("ladoB");
        double c = entrada.get("ladoC");

        if (a <= 0 || b <=0 || c <= 0) {
            throw new ParametroInvalidoException("Todos os lados precisam ser maiores que zero");
        }

        //Desigualdade triangular:
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new ParametroInvalidoException("Esses 3 lados nao formam um triangulo valido " +
                    "(a smoa de dois lados precisam ser maior que o terceiro)");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("ladoA");
        double b = entrada.get("ladoB");
        double c = entrada.get("ladoC");

        //Formula de Heron
        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        Resultado resultado = new Resultado("Area do triangulo");
        resultado.adicionarPasso("Semiperimetro s = (a + b + c) / 2 = " + s);
        resultado.adicionarPasso("area = raiz(s * (s-a) * (s-b) * (s-c) = " + area);
        resultado.adicionarValor("area", area);
        return resultado;
    }
}
