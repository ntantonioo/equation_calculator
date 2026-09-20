package operacao.aritmetica;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

// REGRA DE NEGOCIO: O divisor nao pode ser zero

public class Divisao extends OperacaoBase {

    public Divisao() {
        super("Divisao", Categoria.ARITMETICA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("a", "dividendo"));
        lista.add(new Parametro("b", "divisor"));
        return lista;
    }

    //Sobrescreve o validar() da classe-mae para acrescentar uma regra que
    // so faz sentido para a divisao: o divisor nao pode ser zero
    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double divisor = entrada.get("b");
        if (divisor == 0 ) {
            throw new ParametroInvalidoException("O divisor (b) nao pode ser zero");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("a");
        double b = entrada.get("b");
        double divisao = a / b;

        Resultado resultado = new Resultado("Divisao");
        resultado.adicionarPasso(a + " / " + b + " = " + divisao);
        resultado.adicionarValor("divisao", divisao);
        return resultado;
    }
}
