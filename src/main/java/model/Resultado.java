package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Resultado {

    private String nomeOperacao;
    private HashMap<String, Double> valores;
    private ArrayList<String> passos;
    private String mensagem;

    public Resultado(String nomeOperacao) {
        this.nomeOperacao = nomeOperacao;
        this.valores = new HashMap<>();
        this.passos = new ArrayList<>();
        this.mensagem = "";
    }

    public void adicionarValor(String nome, double valor) {
        valores.put(nome, valor);
    }

    public void adicionarPasso(String texto) {
        passos.add(texto);
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public double getValor(String nome) {
        return valores.get(nome);
    }

    public HashMap<String, Double> getValores() {
        return valores;
    }

    public ArrayList<String> getPassos() {
        return passos;
    }

    public String getMensagem() {
        return mensagem;
    }


    //Monta um texto final, pronto para a interface imprimir na tela.
    public String formatar() {
        StringBuilder texto = new StringBuilder();
        texto.append(nomeOperacao).append("\n\n");

        if (!passos.isEmpty()) {
            texto.append("Resolucao:\n");
            for (int i = 0; i < passos.size(); i++) {
                texto.append("  ").append(i + 1).append(". ").append(passos.get(i)).append("\n");
            }
            texto.append("\n");
        }

        if (!valores.isEmpty()) {
            texto.append("Resultado:\n");
            for (String nome : valores.keySet()) {
                texto.append("  ").append(nome).append(" = ").append(valores.get(nome)).append("\n");
            }
            texto.append("\n");
        }

        if (!mensagem.isEmpty()) {
            texto.append(mensagem).append("\n");
        }

        return texto.toString();
    }

    @Override
    public String toString() {
        return formatar();
    }
}
