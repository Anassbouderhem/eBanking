package ma.enset.projet.repositories;

import ma.enset.projet.entities.BankAccount;
import ma.enset.projet.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
