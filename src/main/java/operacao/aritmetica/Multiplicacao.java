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
        lista.add(new Parametro("a","multiplicando"));
        lista.add(new Parametro("b","multiplicador"));
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
        double multiplicacao = a * b;

        Resultado resultado = new Resultado("Multiplicação");
        resultado.adicionarPasso(a + " x " + b + " = " + multiplicacao);
        resultado.adicionarValor("multiplicacao", multiplicacao);
        return resultado;
    }
}
