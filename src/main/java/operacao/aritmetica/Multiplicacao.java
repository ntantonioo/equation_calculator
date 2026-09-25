package operacao.aritmetica;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class Multiplicacao extends OperacaoBase {

    public Multiplicacao() {
        super("Multiplicação",Categoria.ARITMETICA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("a","primeiro fator"));
        lista.add(new Parametro("b","segundo fator"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        //TODO
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("a");
        double b = entrada.get("b");
        double produto = a * b;

        Resultado resultado = new Resultado("Multiplicação");
        resultado.adicionarPasso(a + " x " + b + " = " + produto);
        resultado.adicionarValor("produto", produto);
        return resultado;
    }
}
