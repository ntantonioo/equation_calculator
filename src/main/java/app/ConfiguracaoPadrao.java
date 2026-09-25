package app;

import operacao.aritmetica.Divisao;
import operacao.aritmetica.Soma;
import operacao.aritmetica.Multiplicacao;
import operacao.aritmetica.Subtracao;
import operacao.geometria.AreaCirculo;
import operacao.geometria.AreaQuadrado;
import operacao.geometria.AreaTrapezio;
import operacao.geometria.AreaTriangulo;
import operacao.segundoGrau.EquacaoSegundoGrau;
import service.CalculadoraService;
import service.CatalogoOperacoes;

public class ConfiguracaoPadrao {

    public static CalculadoraService montarService() {
        CatalogoOperacoes catalogoOperacoes = new CatalogoOperacoes();

        catalogoOperacoes.registrar(new Soma());
        catalogoOperacoes.registrar(new Divisao());
        catalogoOperacoes.registrar(new Multiplicacao());
        catalogoOperacoes.registrar(new Subtracao());
        catalogoOperacoes.registrar(new EquacaoSegundoGrau());
        catalogoOperacoes.registrar(new AreaCirculo());
        catalogoOperacoes.registrar(new AreaTriangulo());
        catalogoOperacoes.registrar(new AreaQuadrado());
        catalogoOperacoes.registrar(new AreaTrapezio());

        return new CalculadoraService(catalogoOperacoes);
    }
}
