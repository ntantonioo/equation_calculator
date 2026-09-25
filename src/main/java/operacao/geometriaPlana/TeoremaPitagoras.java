package operacao.geometriaPlana;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class TeoremaPitagoras extends OperacaoBase {

    public TeoremaPitagoras() {
        super("Teorema de Pitagoras", Categoria.GEOMETRIA_PLANA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("catetoA","primeiro cateto"));
        lista.add(new Parametro("catetoB","segundo cateto"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double a = entrada.get("catetoA");
        double b = entrada.get("catetoB");

        if (a <= 0 || b <= 0) {
            throw new ParametroInvalidoException("Todos os lados devem ser maiores que zero");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("catetoA");
        double b = entrada.get("catetoB");

        //Teorema de Pitagoras
        double s = (a * a) + (b * b);
        double hipotenusa = Math.sqrt(s);

        Resultado resultado = new Resultado("Teorema de Pitagoras");
        resultado.adicionarPasso("Soma dos quadrados dos catetos: (a^2 + b^2) = " + s);
        resultado.adicionarPasso("Hipotenusa = raiz(" + s + ") = " + hipotenusa);
        resultado.adicionarValor("hipotenusa", hipotenusa);
        return resultado;
    }
}
