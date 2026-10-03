package model;

//Categorias de operações que a calculadora oferece
//Enum evita erros de digitação

public enum Categoria {

    ARITMETICA("Aritmetica"),
    PRIMEIRO_GRAU("Equações de 1° grau"),
    SEGUNDO_GRAU("Equações do 2° grau"),
    GEOMETRIA_PLANA("Geometria Plana"),
    GEOMETRIA_ESPACIAL("Geometria Espacial");

    private final String nomeExibicao;

    Categoria(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    @Override
    public String toString() {
        return nomeExibicao;
    }
}
