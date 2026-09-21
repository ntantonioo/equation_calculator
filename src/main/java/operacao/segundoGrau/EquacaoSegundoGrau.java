package operacao.segundoGrau;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class EquacaoSegundoGrau extends OperacaoBase {

    public EquacaoSegundoGrau() {
        super("Equacao do 2° grau", Categoria.SEGUNDO_GRAU, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("a", "coeficiente de x ao quadrado"));
        lista.add(new Parametro("b", "coeficiente de x"));
        lista.add(new Parametro("c","termo independente"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double a = entrada.get("a");
        if (a == 0) {
            throw new ParametroInvalidoException(
                    "O coeficiente a nao pode ser zero"
            );
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double a = entrada.get("a");
        double b = entrada.get("b");
        double c = entrada.get("c");

        double delta = (b * b) - (4 * a * c);

        Resultado resultado = new Resultado("Equação do 2° grau");
        resultado.adicionarPasso("Delta = b^2 - 4ac = " + delta);

        if (delta > 0) {
            double raizDelta = Math.sqrt(delta);
            double x1 = (-b + raizDelta) / (2 * a);
            double x2 = (-b - raizDelta) / (2 * a);

            resultado.adicionarPasso("Delta positivo: duas raizes reais distintas");
            resultado.adicionarValor("x1", x1);
            resultado.adicionarValor("x2", x2);
            resultado.setMensagem("Duas raizes reais distintas");

        } else if (delta == 0) {
            double x = -b / (2 * a);

            resultado.adicionarPasso("Delta igual a zero: uma raiz real (raiz dupla)");
            resultado.adicionarValor("x", x);
            resultado.setMensagem("Uma raiz real (raiz dupla).");

        } else {
            resultado.adicionarPasso("Delta negativo: a equacao nao tem raizes reais");
            resultado.setMensagem("Nao ha raizes reais (Delta negativo)");
        }

        return resultado;
    }
}
