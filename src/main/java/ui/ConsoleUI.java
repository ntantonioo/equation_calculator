package ui;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.Operacao;
import service.CalculadoraService;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private CalculadoraService service;
    private Scanner scanner;

    public ConsoleUI(CalculadoraService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Calculadora de Equacoes ===");

        boolean continuar = true;
        while (continuar) {
            Categoria categoria = escolherCategoria();
            if (categoria == null) {
                continuar = false;
                continue;
            }
            executarFluxoDaCategoria(categoria);
        }

        System.out.println("Até a próxima!");
    }

    private Categoria escolherCategoria() {
        Categoria[] categorias = Categoria.values();

        System.out.println();
        System.out.println("Categorias");
        for (int i = 0; i < categorias.length; i ++) {
            System.out.println(" " + (i + 1) + " - " + categorias[i]);
        }
        System.out.println(" 0 - Sair");

        int opcao = lerInteiro("Escolha uma categoria: ");
        if (opcao == 0) {
            return null;
        }
        if (opcao < 1 || opcao > categorias.length) {
            System.out.println("Opcao invalida!");
            return escolherCategoria();
        }

        return categorias[opcao - 1];
    }

    private void executarFluxoDaCategoria(Categoria categoria) {
        ArrayList<Operacao> operacoes = service.getCatalogo().porCategoria(categoria);

        if (operacoes.isEmpty()) {
            System.out.println("Ainda nao ha operacoes cadastradas em " + categoria + ".");
            return;
        }

        System.out.println();
        System.out.println("Operacoes de " + categoria + ":");
        for (int i = 0; i < operacoes.size(); i ++) {
            System.out.println(" " + (i + 1) + " - " + operacoes.get(i).getNome());
        }

        int opcao = lerInteiro("Escolha uma operacao: ");
        if (opcao < 1 || opcao > operacoes.size()) {
            System.out.println("Opcao invalida!");
            return;
        }

        Operacao operacaoEscolhida = operacoes.get(opcao - 1);
        Entrada entrada = lerParametros(operacaoEscolhida);

        try {
            Resultado resultado = service.executar(operacaoEscolhida.getNome(), entrada);

            System.out.println();
            System.out.println(resultado.formatar());
        } catch (ParametroInvalidoException e) {
            System.out.println();
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private Entrada lerParametros(Operacao operacao) {
        Entrada entrada = new Entrada();
        System.out.println();
        for (Parametro parametro : operacao.getParametros()) {
            double valor = lerDouble(parametro.paraExibicao() + ": ");
            entrada.adicionar(parametro.getNome(), valor);
        }
        return entrada;
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um numero valido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        return valor;
    }

    private double lerDouble(String mensagem) {
        System.out.println(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Digite um numero valido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        return valor;
    }

}
