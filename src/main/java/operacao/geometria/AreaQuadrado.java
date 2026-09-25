package operacao.geometria;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class AreaQuadrado extends OperacaoBase {

    public AreaQuadrado() {
        super("Area o Quadrado", Categoria.GEOMETRIA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("lado", "lado do quadrado"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double lado = entrada.get("lado");
        if (lado <= 0) {
            throw new ParametroInvalidoException("Lado deve ser positivo");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double lado = entrada.get("lado");
        double area = lado * lado;

        Resultado resultado = new Resultado("Area do Quadrado");
        resultado.adicionarPasso("area = lado^2 = " + lado + "^2 = " + area);
        resultado.adicionarValor("area", area);
        return resultado;
    }
}
