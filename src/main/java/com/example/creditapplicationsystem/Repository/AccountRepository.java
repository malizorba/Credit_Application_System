package com.example.creditapplicationsystem.Repository;

import com.example.creditapplicationsystem.Model.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // JPQL
    boolean existsByUsername(String username);

    Account findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);



}