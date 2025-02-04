package com.eazybytes.accounts.repositories;

import com.eazybytes.accounts.entities.Accounts;
import com.eazybytes.accounts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomer(Customer customer);
}
