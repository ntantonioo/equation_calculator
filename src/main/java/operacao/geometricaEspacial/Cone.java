package operacao.geometricaEspacial;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class Cone extends OperacaoBase {

    public Cone() {
        super("Cone", Categoria.GEOMETRIA_ESPACIAL, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("raio", "raio da base"));
        lista.add(new Parametro("altura", "altura do cone"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double raio = entrada.get("raio");
        double altura = entrada.get("altura");
        if (raio <= 0 || altura <= 0) {
            throw new ParametroInvalidoException("O raio e a altura precisam ser maiores que zero");
        }
    }
    private double calcularGeratriz(double raio, double altura) {
        return Math.sqrt(raio * raio + altura * altura);
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double raio = entrada.get("raio");
        double altura = entrada.get("altura");
        double geratriz = calcularGeratriz(raio, altura);

        double volume = (1.0 / 3.0) * Math.PI * raio * raio * altura;
        double areaTotal = Math.PI * raio * (raio + geratriz);

        Resultado resultado = new Resultado("Cone");
        resultado.adicionarPasso("geratriz = raiz(raio^2 + altura^2) = " + geratriz);
        resultado.adicionarPasso("volume = (1/3) * PI * raio^2 * altura = " + volume);
        resultado.adicionarPasso("area total = PI * raio * (raio + geratriz) = " + areaTotal);
        resultado.adicionarValor("volume", volume);
        resultado.adicionarValor("areaTotal", areaTotal);
        return resultado;
    }
}
