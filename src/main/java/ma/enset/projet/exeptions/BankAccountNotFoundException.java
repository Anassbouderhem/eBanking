package ma.enset.projet.exeptions;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
