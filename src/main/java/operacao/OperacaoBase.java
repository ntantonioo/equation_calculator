package operacao;

import exception.ParametroInvalidoException;
import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;

import java.util.ArrayList;

public abstract class OperacaoBase implements Operacao {

    protected String nome;
    protected Categoria categoria;
    protected ArrayList<Parametro> parametros;

    public OperacaoBase(String nome, Categoria categoria, ArrayList<Parametro> parametros) {
        this.nome = nome;
        this.categoria = categoria;
        this.parametros = parametros;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public ArrayList<Parametro> getParametros() {
        return parametros;
    }

    @Override
    public Resultado executar(Entrada entrada) {
        validar(entrada);
        return calcular(entrada);
    }


    //Confere se t0do parametro obrigatorio foi informado
    protected void validar(Entrada entrada) {
        for (Parametro parametro: parametros) {
            if (parametro.isObrigatorio() && !entrada.contem(parametro.getNome())) {
                throw new ParametroInvalidoException(
                        "Parametro Obrigatorio nao informado: " + parametro.getNome());
            }
        }
    }

    protected abstract Resultado calcular(Entrada entrada);
}
