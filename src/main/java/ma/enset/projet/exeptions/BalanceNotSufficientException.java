package ma.enset.projet.exeptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
