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
        //TODO
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("lado");

        double area = a * a;

        Resultado resultado = new Resultado("Area do Quadrado");
        resultado.adicionarPasso("Area do Quadrado = a(Lado)^2 " + " = " + area);
        resultado.adicionarValor("area", area);
        return resultado;
    }
}
