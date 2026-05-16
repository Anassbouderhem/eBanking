package ma.enset.projet.services;

import ma.enset.projet.dtos.*;
import ma.enset.projet.entities.BankAccount;
import ma.enset.projet.entities.CurrentAccount;
import ma.enset.projet.entities.Customer;
import ma.enset.projet.entities.SavingAccount;
import ma.enset.projet.exeptions.BalanceNotSufficientException;
import ma.enset.projet.exeptions.BankAccountNotFoundException;
import ma.enset.projet.exeptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BankAccountNotFoundException, BalanceNotSufficientException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException ;

    List<CustomerDTO> searchCustomers(String keyword);

    List<BankAccountDTO> getCustomerAccounts(Long customerId);
}
