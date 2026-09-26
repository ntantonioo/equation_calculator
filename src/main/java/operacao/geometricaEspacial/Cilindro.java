package operacao.geometricaEspacial;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class Cilindro extends OperacaoBase {

    public Cilindro() {
        super("Cilindro", Categoria.GEOMETRIA_ESPACIAL, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("raio", "raio da base"));
        lista.add(new Parametro("altura", "altura do cilindro"));
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

    @Override
    protected Resultado calcular(Entrada entrada) {
        double raio = entrada.get("raio");
        double altura = entrada.get("altura");

        double volume = Math.PI * raio * raio * altura;
        double areaTotal = 2 * Math.PI * raio * (raio + altura);

        Resultado resultado = new Resultado("Cilindro");
        resultado.adicionarPasso("volume = PI * raio^2 * altura = " + volume);
        resultado.adicionarPasso("area total = 2 * PI * raio * (raio + altura) = " + areaTotal);
        resultado.adicionarValor("volume", volume);
        resultado.adicionarValor("areaTotal", areaTotal);
        return resultado;
    }
}
