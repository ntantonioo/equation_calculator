package operacao.aritmetica;

import exception.ParametroInvalidoException;

import java.util.ArrayList;

public class AvaliadorExpressao {

    private ArrayList<String> passos;

    public AvaliadorExpressao() {
        this.passos = new ArrayList<>();
    }

    public ArrayList<String> getPassos() {
        return passos;
    }

    public double avaliar(String expressao) {
        ArrayList<String> tokens = tokenizar(expressao);
        ArrayList<String> apenasSomaESubtracao = resolverMultiplicacao(tokens);
        return resolverSomaESubtracao(apenasSomaESubtracao);
    }

    private ArrayList<String> tokenizar(String expressao) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder numeroAtual = new StringBuilder();

        for (char caractere : expressao.replace(" ", "").toCharArray()) {
            boolean ehOperador = caractere == '+' || caractere == '-'
                    || caractere =='*' || caractere == '/';

            if (Character.isDigit(caractere) || caractere == '.') {
                numeroAtual.append(caractere);
            } else if (ehOperador) {
                if (numeroAtual.length() == 0) {
                    throw new ParametroInvalidoException(
                            "Expressao invalida: operador sem numero antes dele");
                }
                tokens.add(numeroAtual.toString());
                numeroAtual.setLength(0);
                tokens.add(String.valueOf(caractere));
            } else {
                throw new ParametroInvalidoException(
                        "Caractere invalido na expressao: '" + caractere + "'");
            }
        }

        if (numeroAtual.length() == 0) {
            throw new ParametroInvalidoException("A expressao nao pode terminar com um operador");
        }
        tokens.add(numeroAtual.toString());

        return tokens;
    }

    private ArrayList<String> resolverMultiplicacao(ArrayList<String> tokens) {
        ArrayList<String> restante = new ArrayList<>(tokens);

        int i = 1;
        while (i < restante.size()) {
            String operador = restante.get(i);

            if (!operador.equals("*") && !operador.equals("/")) {
                i += 2;
                continue;
            }

            double esquerda = Double.parseDouble(restante.get(i - 1));
            double direita = Double.parseDouble(restante.get(i + 1));
            double resultadoParcial;

            if (operador.equals("*")) {
                resultadoParcial = esquerda * direita;
            } else {
                 if (direita == 0) {
                     throw new ParametroInvalidoException("Divisao por zero na expressao");
                 }
                 resultadoParcial = esquerda / direita;
            }

            passos.add(esquerda + " " + operador + " " + direita + " = " + resultadoParcial);

            restante.set(i - 1, String.valueOf(resultadoParcial));
            restante.remove(i);
            restante.remove(i);
        }

        return restante;
    }

    private double resolverSomaESubtracao(ArrayList<String> tokens) {
        double valor = Double.parseDouble(tokens.get(0));

        for (int i = 1; i < tokens.size(); i += 2) {
            String operador = tokens.get(i);
            double proximo = Double.parseDouble(tokens.get(i + 1));
            double anterior = valor;

            if (operador.equals("+")) {
                valor = valor + proximo;
            } else {
                valor = valor - proximo;
            }

            passos.add(anterior + " " + operador + " " + proximo + " = " + valor);
        }

        return valor;
    }
}
