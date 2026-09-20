package service;

import service.*;

import exception.OperacaoNaoEncontradaException;
import model.Categoria;
import operacao.Operacao;

import java.util.ArrayList;

/**
 * Guarda todas as operacoes que a calculadora sabe executar;
 * Responde duas perguntas:"quais operacoes existem nesta categoria?" e me devolva a
 * operacao chamada..."
 */

public class CatalogoOperacoes {

    private ArrayList<Operacao> operacoes;

    public CatalogoOperacoes() {
        this.operacoes = new ArrayList<>();
    }

    //Adiciona uma nova operacao ao catalogo
    public void registrar(Operacao operacao) {
        operacoes.add(operacao);
    }

    //Devolve so as operacoes de uma categoria, na ordem em que foram resgistradas
    public ArrayList<Operacao> porCategoria(Categoria categoria) {
        ArrayList<Operacao> resultado = new ArrayList<>();
        for (Operacao operacao : operacoes) {
            if (operacao.getCategoria() == categoria) {
                resultado.add(operacao);
            }
        }
        return resultado;
    }

    //Procura uma operacao pelo nome exibido
    //Comparacao ignorando maiusculas/minusculas
    public Operacao buscar(String nome) {
        for (Operacao operacao : operacoes) {
            if (operacao.getNome().equalsIgnoreCase(nome)) {
                return operacao;
            }
        }
        throw  new OperacaoNaoEncontradaException("Operacao nao encontrada: " + nome);
    }

    public ArrayList<Operacao> todas() {
        return operacoes;
    }
}
