package ui;

import app.ConfiguracaoPadrao;
import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;
import operacao.Operacao;
import service.CalculadoraService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculadoraFxApp extends Application {

    private CalculadoraService service;

    private HashMap<String, TextField> camposDoFormulario;

    private VBox areaCentral;
    private Label labelResultado;

    private TextField campoAtivo;

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
                return "\uD83E\uDDCA"; // cubo
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

    // Chamado quando o usuario clica numa categoria: lista as operacoes
    // dela como botoes.
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
        campoAtivo = null;

        Label titulo = new Label(operacao.getNome());
        titulo.getStyleClass().add("titulo-secao");
        areaCentral.getChildren().add(titulo);

        // Coluna da esquerda: rotulos, campos, botao calcular e resultado.
        VBox colunaCampos = new VBox(10);

        TextField primeiroCampo = null;

        for (Parametro parametro : operacao.getParametros()) {
            Label rotulo = new Label(parametro.paraExibicao());
            rotulo.getStyleClass().add("rotulo-campo");

            TextField campo = new TextField();
            campo.getStyleClass().add("campo-texto");

            campo.focusedProperty().addListener((observavel, perdeuFoco, ganhouFoco) -> {
                if (ganhouFoco) {
                    campoAtivo = campo;
                }
            });

            if (primeiroCampo == null) {
                primeiroCampo = campo;
            }

            // Guarda a referencia do campo, associada ao nome do parametro,
            // para poder ler o valor de volta quando o botao Calcular for
            // clicado.
            camposDoFormulario.put(parametro.getNome(), campo);

            colunaCampos.getChildren().add(rotulo);
            colunaCampos.getChildren().add(campo);
        }

        Button botaoCalcular = new Button("Calcular");
        botaoCalcular.getStyleClass().add("botao-calcular");
        botaoCalcular.setOnAction(evento -> calcular(operacao));
        colunaCampos.getChildren().add(botaoCalcular);

        labelResultado = new Label();
        labelResultado.getStyleClass().add("caixa-resultado");
        labelResultado.setWrapText(true);
        colunaCampos.getChildren().add(labelResultado);

        // Linha horizontal: campos a esquerda, teclado numerico a direita.
        HBox linha = new HBox(24, colunaCampos, construirTeclado());
        areaCentral.getChildren().add(linha);

        if (primeiroCampo != null) {
            campoAtivo = primeiroCampo;
            primeiroCampo.requestFocus();
        }
    }

    private GridPane construirTeclado() {
        GridPane teclado = new GridPane();
        teclado.getStyleClass().add("teclado");
        teclado.setHgap(8);
        teclado.setVgap(8);

        String[][] disposicao = {
                {"7", "8", "9", "<-"},
                {"4", "5", "6", "C"},
                {"1", "2", "3", "+/-"},
                {"0", "0", ",", ","}  // "0" ocupa 2 colunas; "," soh aparece 1 vez
        };

        for (int linha = 0; linha < disposicao.length; linha++) {
            if (linha == 3) {
                // Ultima linha tratada a parte, por causa do botao "0" largo.
                Button botaoZero = criarBotaoTeclado("0", false);
                teclado.add(botaoZero, 0, linha, 2, 1); // ocupa 2 colunas
                Button botaoVirgula = criarBotaoTeclado(",", false);
                teclado.add(botaoVirgula, 2, linha, 2, 1);
                break;
            }
            for (int coluna = 0; coluna < disposicao[linha].length; coluna++) {
                String texto = disposicao[linha][coluna];
                boolean funcao = texto.equals("<-") || texto.equals("C") || texto.equals("+/-");
                Button botao = criarBotaoTeclado(texto, funcao);
                teclado.add(botao, coluna, linha);
            }
        }

        return teclado;
    }

    private Button criarBotaoTeclado(String texto, boolean funcao) {
        Button botao = new Button(texto);
        botao.getStyleClass().add(funcao ? "botao-teclado-funcao" : "botao-teclado");

        switch (texto) {
            case "<-":
                botao.setOnAction(evento -> apagarUltimoCaractere());
                break;
            case "C":
                botao.setOnAction(evento -> limparCampoAtivo());
                break;
            case "+/-":
                botao.setOnAction(evento -> alternarSinal());
                break;
            case ",":
                botao.setOnAction(evento -> inserirNoCampo(","));
                break;
            default:
                botao.setOnAction(evento -> inserirNoCampo(texto));
        }

        return botao;
    }

    private void inserirNoCampo(String texto) {
        if (campoAtivo == null) {
            return;
        }
        // Evita um segundo separador decimal no mesmo numero.
        if (texto.equals(",") && campoAtivo.getText().contains(",")) {
            return;
        }
        campoAtivo.appendText(texto);
    }

    private void apagarUltimoCaractere() {
        if (campoAtivo == null) {
            return;
        }
        String textoAtual = campoAtivo.getText();
        if (!textoAtual.isEmpty()) {
            campoAtivo.setText(textoAtual.substring(0, textoAtual.length() - 1));
            campoAtivo.positionCaret(campoAtivo.getText().length());
        }
    }

    private void limparCampoAtivo() {
        if (campoAtivo == null) {
            return;
        }
        campoAtivo.clear();
    }

    private void alternarSinal() {
        if (campoAtivo == null) {
            return;
        }
        String textoAtual = campoAtivo.getText();
        if (textoAtual.startsWith("-")) {
            campoAtivo.setText(textoAtual.substring(1));
        } else if (!textoAtual.isEmpty()) {
            campoAtivo.setText("-" + textoAtual);
        }
        campoAtivo.positionCaret(campoAtivo.getText().length());
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