package model;

import exception.ParametroInvalidoException;

import java.util.HashMap;

public class Entrada {

    private HashMap<String, Double> valores;

    public Entrada() {
        this.valores = new HashMap<>();
    }

    //vai sendo preechida aos poucos, conforme a interface le o teclado
    public void adicionar(String nome, double valor) {
        valores.put(nome, valor);
    }

    //devolve o valor de um parametro. Se ele nao foi informado, avisa com uma excecao
    public double get(String nome) {
        if (!valores.containsKey(nome)) {
            throw new ParametroInvalidoException("Parametro obrigatorio ausente: " + nome);
        }
        return valores.get(nome);
    }

    public boolean contem(String nome) {
        return valores.containsKey(nome);
    }

    @Override
    public String toString() {
        return valores.toString();
    }
}
