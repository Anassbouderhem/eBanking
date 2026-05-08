package ma.enset.projet;

import ma.enset.projet.entities.*;
import ma.enset.projet.enums.AccountStatus;
import ma.enset.projet.enums.OperationType;
import ma.enset.projet.exeptions.BalanceNotSufficientException;
import ma.enset.projet.exeptions.BankAccountNotFoundException;
import ma.enset.projet.exeptions.CustomerNotFoundException;
import ma.enset.projet.repositories.AccountOperationRepository;
import ma.enset.projet.repositories.BankAccountRepository;
import ma.enset.projet.repositories.CustomerRepository;
import ma.enset.projet.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Ali", "Hamza", "Yassmine").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 120000, 10000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 170000, 4.3, customer.getId());
                    List<BankAccount> bankAccounts = bankAccountService.bankAccountList();
                    for (BankAccount bankAccount : bankAccounts) {
                        for (int i = 0; i < 10; i++) {
                            bankAccountService.credit(bankAccount.getId(), 10000 + Math.random() * 150000, "Credit");
                            bankAccountService.debit(bankAccount.getId(), 1000 + Math.random() * 5000, "Debit");
                        }
                    }

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException e) {
                    e.printStackTrace();
                } catch (BalanceNotSufficientException e) {
                    e.printStackTrace();
                }
            });


        };

    }
    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {

        return args -> {
            Stream.of("Ali", "Hamza", "Yassmine").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 10000);
                currentAccount.setOverDraft(23000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setCustomer(customer);
                currentAccount.setStatus(AccountStatus.CREATED);
                bankAccountRepository.save(currentAccount);
            });
            customerRepository.findAll().forEach(customer -> {
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 10000);
                savingAccount.setInterestRate(4.3);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setCustomer(customer);
                savingAccount.setStatus(AccountStatus.CREATED);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 15000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
                    accountOperation.setBankAccount(account);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}
