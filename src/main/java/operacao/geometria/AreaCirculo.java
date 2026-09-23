package operacao.geometria;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

/**
 * Calcula a area de um circulo: area = PI * raio^2
 */

public class AreaCirculo extends OperacaoBase {

    public AreaCirculo() {
        super("Area do Circulo", Categoria.GEOMETRIA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("raio", "raio do circulo"));
        return lista;
    }

    // Regra propria: dimensoes de geometria tem que ser maiores que zero
    // Raio zero ou negativo nao existe
    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double raio = entrada.get("raio");
        if (raio <= 0) {
            throw new ParametroInvalidoException("O raio precisa ser maior que zero");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double raio = entrada.get("raio");
        double area = Math.PI * raio * raio;

        Resultado resultado = new Resultado("Area do circulo");
        resultado.adicionarPasso("area = PI * raio^2 = " + Math.PI + " + " + raio + "^2 = " + area);
        resultado.adicionarValor("area", area);
        return resultado;
    }
}
