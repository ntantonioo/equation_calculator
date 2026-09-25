package app;

import operacao.aritmetica.Divisao;
import operacao.aritmetica.Soma;
import operacao.aritmetica.Multiplicacao;
import operacao.aritmetica.Subtracao;
import operacao.geometriaPlana.*;
import operacao.segundoGrau.EquacaoSegundoGrau;
import service.CalculadoraService;
import service.CatalogoOperacoes;

public class ConfiguracaoPadrao {

    public static CalculadoraService montarService() {
        CatalogoOperacoes catalogo = new CatalogoOperacoes();

        catalogo.registrar(new Divisao());
        catalogo.registrar(new Soma());
        catalogo.registrar(new Multiplicacao());
        catalogo.registrar(new Subtracao());
        catalogo.registrar(new EquacaoSegundoGrau());
        catalogo.registrar(new AreaCirculo());
        catalogo.registrar(new AreaTriangulo());
        catalogo.registrar(new AreaQuadrado());
        catalogo.registrar(new AreaTrapezio());
        catalogo.registrar(new TeoremaPitagoras());

        return new CalculadoraService(catalogo);
    }
}
