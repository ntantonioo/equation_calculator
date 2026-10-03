package operacao.primeiroGrau;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class EquacaoPrimeiroGrau extends OperacaoBase {

    public EquacaoPrimeiroGrau() {
        super("Equacao de 1° grau", Categoria.PRIMEIRO_GRAU, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("a", "coeficiente de x"));
        lista.add(new Parametro("b","termo independente"));
        return lista;
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("a");
        double b = entrada.get("b");

        Resultado resultado = new Resultado("Equacao do 1o grau");
        resultado.adicionarPasso("Equacao: " + a + "x + " + b + " = 0");

        if (a == 0 && b == 0) {
            resultado.adicionarPasso("a = 0 e b = 0: a equacao vira 0 = 0, verdadeira sempre");
            resultado.setMensagem("Infinitas solucoes (qualquer valor de x satisfaz a equacao).");

        } else if (a == 0) {
            resultado.adicionarPasso("a = 0 e b diferente de zero: a equacao vira " + b + " = 0, que e falso");
            resultado.setMensagem("Nenhuma solucao (a equacao e impossivel).");

        } else {
            double x = -b / a;
            resultado.adicionarPasso("x = -b / a = -(" + b + ") / " + a + " = " + x);
            resultado.adicionarValor("x", x);
            resultado.setMensagem("Solucao unica.");
        }

        return resultado;
    }
}
