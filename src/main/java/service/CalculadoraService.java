package service;

import model.Entrada;
import model.Resultado;
import operacao.Operacao;

import java.util.ArrayList;

/**
 * Fachada do sistema (unica que a interface (ConsoleUI) preicsa
 * conhecer;
 * Nao calcula nada sozinha;
 * Pede a operacao certa ao catalogo, manda executar e guarda
 * o resultado no historico de sessao.
 */

public class CalculadoraService {

    private CatalogoOperacoes catalogo;
    private ArrayList<Resultado> historico;

    public CalculadoraService(CatalogoOperacoes catalogo) {
        this.catalogo = catalogo;
        this.historico = new ArrayList<>();
    }

    public Resultado executar(String nomeOperacao, Entrada entrada) {
        Operacao operacao = catalogo.buscar(nomeOperacao);
        Resultado resultado = operacao.executar(entrada);
        historico.add(resultado);
        return resultado;
    }

    public ArrayList<Resultado> getHistorico() {
        return historico;
    }

    public CatalogoOperacoes getCatalogo() {
        return catalogo;
    }
}