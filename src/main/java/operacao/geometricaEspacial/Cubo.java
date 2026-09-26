package operacao.geometricaEspacial;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class Cubo extends OperacaoBase {

    public Cubo() {
        super("Cubo", Categoria.GEOMETRIA_ESPACIAL, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("aresta","aresta do cubo"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double aresta = entrada.get("aresta");
        if (aresta <=0) {
            throw new ParametroInvalidoException("A aresta precisa ser maior que zero");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double aresta = entrada.get("aresta");

        double volume = aresta *  aresta * aresta;
        double areaTotal = 6 * aresta * aresta;

        Resultado resultado = new Resultado("Cubo");
        resultado.adicionarPasso("volume = aresta^3 = " + aresta + "^3 = " + volume);
        resultado.adicionarPasso("area total = 6 * aresta^2 = 6 * " + aresta + "^2 = " + areaTotal);
        resultado.adicionarValor("volume", volume);
        resultado.adicionarValor("areaTotal", areaTotal);
        return resultado;
    }
}
