package app;

import operacao.aritmetica.Divisao;
import operacao.aritmetica.Soma;
import operacao.geometria.AreaCirculo;
import operacao.geometria.AreaTriangulo;
import operacao.segundoGrau.EquacaoSegundoGrau;
import service.CalculadoraService;
import service.CatalogoOperacoes;
import ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        CatalogoOperacoes catalogo = new CatalogoOperacoes();

        catalogo.registrar(new Soma());
        catalogo.registrar(new Divisao());
        catalogo.registrar(new EquacaoSegundoGrau());
        catalogo.registrar(new AreaCirculo());
        catalogo.registrar(new AreaTriangulo());

        CalculadoraService service = new CalculadoraService(catalogo);
        ConsoleUI ui = new ConsoleUI(service);
        ui.iniciar();
    }
}
