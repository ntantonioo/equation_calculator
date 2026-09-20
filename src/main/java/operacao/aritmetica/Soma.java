package operacao.aritmetica;

import model.Categoria;
import model.Parametro;
import model.Entrada;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class Soma extends OperacaoBase {

    public Soma() {
        super("Soma", Categoria.ARITMETICA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("a", "primeira parcela"));
        lista.add(new Parametro("b", "segunda parcela"));
        return lista;
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("a");
        double b = entrada.get("b");
        double soma = a + b;

        Resultado resultado = new Resultado("Soma");
        resultado.adicionarPasso(a + " + " + b + " = " + soma);
        resultado.adicionarValor("soma", soma);
        return resultado;
    }
}
