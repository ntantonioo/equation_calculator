import operacao.aritmetica.Divisao;
import operacao.aritmetica.Soma;
import service.CalculadoraService;
import service.CatalogoOperacoes;
import ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        CatalogoOperacoes catalogo = new CatalogoOperacoes();

        catalogo.registrar(new Soma());
        catalogo.registrar(new Divisao());

        CalculadoraService service = new CalculadoraService(catalogo);
        ConsoleUI ui = new ConsoleUI(service);
        ui.iniciar();
    }
}
