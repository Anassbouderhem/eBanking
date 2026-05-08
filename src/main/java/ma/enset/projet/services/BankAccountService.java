package ma.enset.projet.services;

import ma.enset.projet.entities.BankAccount;
import ma.enset.projet.entities.CurrentAccount;
import ma.enset.projet.entities.Customer;
import ma.enset.projet.entities.SavingAccount;
import ma.enset.projet.exeptions.BalanceNotSufficientException;
import ma.enset.projet.exeptions.BankAccountNotFoundException;
import ma.enset.projet.exeptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BankAccountNotFoundException, BalanceNotSufficientException;
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

    List<BankAccount> bankAccountList();
}
