package com.example.creditapplicationsystem;

import com.example.creditapplicationsystem.Model.Entity.Account;
import com.example.creditapplicationsystem.Repository.AccountRepository;
import com.example.creditapplicationsystem.Service.impl.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleDataInitializer implements ApplicationRunner {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public void run(ApplicationArguments args) {


        // Creating a sample Admin USER
        Account adminAccount = new Account("admin-user", "adminuser@mail.com", "pass1234");
        if (!accountRepository.existsByUsername(adminAccount.getUsername())) {
            accountService.signup(adminAccount,true);
        }

        // Creating a sample USER
        Account sampleAccount = new Account("sample-user", "sampleuser@mail.com", "pass1234");
        if (!accountRepository.existsByUsername(sampleAccount.getUsername())) {
            accountService.signup(sampleAccount,false);
        }

    }

}