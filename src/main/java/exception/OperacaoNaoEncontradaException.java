package exception;

public class OperacaoNaoEncontradaException extends RuntimeException {
    public OperacaoNaoEncontradaException(String message) {
        super(message);
    }
}
