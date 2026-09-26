package ui;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.Operacao;
import service.CalculadoraService;
import app.ConfiguracaoPadrao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculadoraFxApp extends Application {

    private CalculadoraService service;

    private HashMap<String, TextField> camposDoFormulario;

    private VBox areaCentral;
    private Label labelResultado;

    @Override
    public void start(Stage palco) {
        service = ConfiguracaoPadrao.montarService();
        camposDoFormulario = new HashMap<>();

        BorderPane raiz = new BorderPane();
        raiz.setLeft(construirBarraLateral());
        raiz.setCenter(construirAreaCentral());

        Scene cena = new Scene(raiz, 900, 600);
        cena.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        palco.setTitle("Calculadora de Equacoes");
        palco.setScene(cena);
        palco.show();
    }

    private VBox construirBarraLateral() {
        VBox barra = new VBox(12);
        barra.getStyleClass().add("barra-lateral");

        Label titulo = new Label("Calculadora");
        titulo.getStyleClass().add("titulo-app");
        barra.getChildren().add(titulo);
        barra.getChildren().add(new Label(" ")); // pequeno espaco

        for (Categoria categoria : Categoria.values()) {
            Button botao = new Button(icone(categoria) + "  " + categoria);
            botao.getStyleClass().add("botao-categoria");
            botao.setOnAction(evento -> mostrarOperacoes(categoria));
            barra.getChildren().add(botao);
        }

        return barra;
    }

    private String icone(Categoria categoria) {
        switch (categoria) {
            case ARITMETICA:
                return "\u2795"; // +
            case SEGUNDO_GRAU:
                return "\uD83D\uDCC8"; // grafico
            case GEOMETRIA_PLANA:
                return "\uD83D\uDCD0"; // esquadro
            case GEOMETRIA_ESPACIAL:
                return "\uD83E\uDDCA";
            default:
                return "\u2022";
        }
    }

    private VBox construirAreaCentral() {
        areaCentral = new VBox(16);
        areaCentral.getStyleClass().add("area-central");
        mostrarMensagemInicial();
        return areaCentral;
    }

    private void mostrarMensagemInicial() {
        areaCentral.getChildren().clear();
        Label mensagem = new Label("Escolha uma categoria ao lado para comecar.");
        mensagem.getStyleClass().add("titulo-secao");
        areaCentral.getChildren().add(mensagem);
    }

    private void mostrarOperacoes(Categoria categoria) {
        areaCentral.getChildren().clear();

        Label titulo = new Label(icone(categoria) + "  " + categoria);
        titulo.getStyleClass().add("titulo-secao");
        areaCentral.getChildren().add(titulo);

        ArrayList<Operacao> operacoes = service.getCatalogo().porCategoria(categoria);

        if (operacoes.isEmpty()) {
            areaCentral.getChildren().add(new Label("Nenhuma operacao cadastrada ainda."));
            return;
        }

        for (Operacao operacao : operacoes) {
            Button botao = new Button(operacao.getNome());
            botao.getStyleClass().add("botao-operacao");
            botao.setOnAction(evento -> mostrarFormulario(operacao));
            areaCentral.getChildren().add(botao);
        }
    }

    private void mostrarFormulario(Operacao operacao) {
        areaCentral.getChildren().clear();
        camposDoFormulario.clear();

        Label titulo = new Label(operacao.getNome());
        titulo.getStyleClass().add("titulo-secao");
        areaCentral.getChildren().add(titulo);

        for (Parametro parametro : operacao.getParametros()) {
            Label rotulo = new Label(parametro.paraExibicao());
            rotulo.getStyleClass().add("rotulo-campo");

            TextField campo = new TextField();
            campo.getStyleClass().add("campo-texto");

            camposDoFormulario.put(parametro.getNome(), campo);

            areaCentral.getChildren().add(rotulo);
            areaCentral.getChildren().add(campo);
        }

        Button botaoCalcular = new Button("Calcular");
        botaoCalcular.getStyleClass().add("botao-calcular");
        botaoCalcular.setOnAction(evento -> calcular(operacao));
        areaCentral.getChildren().add(botaoCalcular);

        labelResultado = new Label();
        labelResultado.getStyleClass().add("caixa-resultado");
        labelResultado.setWrapText(true);
        areaCentral.getChildren().add(labelResultado);
    }

    private void calcular(Operacao operacao) {
        try {
            Entrada entrada = new Entrada();

            for (String nomeParametro : camposDoFormulario.keySet()) {
                String texto = camposDoFormulario.get(nomeParametro).getText();

                double valor = Double.parseDouble(texto.trim().replace(",", "."));
                entrada.adicionar(nomeParametro, valor);
            }

            Resultado resultado = service.executar(operacao.getNome(), entrada);
            mostrarResultado(resultado.formatar(), true);

        } catch (NumberFormatException e) {
            mostrarResultado("Preencha todos os campos com numeros validos.", false);
        } catch (ParametroInvalidoException e) {
            mostrarResultado(e.getMessage(), false);
        }
    }

    private void mostrarResultado(String texto, boolean sucesso) {
        labelResultado.setText(texto);
        labelResultado.getStyleClass().removeAll("resultado-sucesso", "resultado-erro");
        labelResultado.getStyleClass().add(sucesso ? "resultado-sucesso" : "resultado-erro");
    }

    public static void main(String[] args) {
        launch(args);
    }
}