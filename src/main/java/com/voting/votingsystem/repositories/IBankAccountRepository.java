package com.voting.votingsystem.repositories;

import com.voting.votingsystem.Entities.EBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankAccountRepository extends JpaRepository<EBankAccount, Long> {
    EBankAccount findById(long id);

    EBankAccount findByEmailAddressIgnoreCase(String emailAddress);

    EBankAccount findByFirstNameAndEmailAddress(String firstName, String lastName);
}
