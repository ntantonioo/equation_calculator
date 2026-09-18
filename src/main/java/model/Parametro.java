package model;

public class Parametro {

    private String nome;
    private String descricao;
    private String obrigatorio;

    public Parametro(String nome, String descricao, String obrigatorio) {
        this.nome = nome;
        this.descricao = descricao;
        this.obrigatorio = obrigatorio;
    }

    // Construtor para caso comum: parametro obrigatorio
    public Parametro(String nome, String descricao) {
        this(nome, descricao, true);
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    //Texto pronto para exibir na tela
    public String paraExibicao() {
        return nome + " (" + descricao + ")";
    }
}
