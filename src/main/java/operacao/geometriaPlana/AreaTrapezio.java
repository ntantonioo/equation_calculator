package operacao.geometriaPlana;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.OperacaoBase;

import java.util.ArrayList;

public class AreaTrapezio extends OperacaoBase {

    public AreaTrapezio() {
        super("Area do Trapezio", Categoria.GEOMETRIA_PLANA, montarParametros());
    }

    private static ArrayList<Parametro> montarParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();
        lista.add(new Parametro("baseMaior", "base maior"));
        lista.add(new Parametro("baseMenor", "base menor"));
        lista.add(new Parametro("altura", "altura"));
        return lista;
    }

    @Override
    protected void validar(Entrada entrada) {
        super.validar(entrada);

        double baseMaior = entrada.get("baseMaior");
        double baseMenor = entrada.get("baseMenor");
        double altura = entrada.get("altura");

        if (baseMaior <= 0 || baseMenor <= 0 || altura <= 0) {
            throw new ParametroInvalidoException("Todas as dimensoes precisam ser maiores que zero");
        }

        if (baseMenor > baseMaior) {
            throw new ParametroInvalidoException("A base menor nao pode ser maior que a base maior");
        }
    }

    @Override
    protected Resultado calcular(Entrada entrada) {
        double baseMaior = entrada.get("baseMaior");
        double baseMenor = entrada.get("baseMenor");
        double altura = entrada.get("altura");

        double area = (baseMaior + baseMenor + altura) / 2;

        Resultado resultado = new Resultado("Area do trapezio");
        resultado.adicionarPasso("area = (base maior + base menor) * altura / 2");
        resultado.adicionarPasso("area = (" + baseMaior + " + " + baseMenor + ") * " + altura + " / 2 = " + area);
        resultado.adicionarValor("area", area);
        return resultado;

    }
}
