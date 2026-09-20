package operacao;

import model.Categoria;
import model.Entrada;
import model.Parametro;
import model.Resultado;

import java.util.ArrayList;

public interface Operacao {

    //Nome exibido na tela, ex: Soma
    String getNome();

    //A categoria a que a operacao pertence
    Categoria getCategoria();

    //Quais parametros essa operacao precisa receber, para a interface
    //montar as perguntas ao usuario
    ArrayList<Parametro> getParametros();

    //Executa o calculo e devolve o resultado
    Resultado executar(Entrada entrada);
}
