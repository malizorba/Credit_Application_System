package com.example.creditapplicationsystem.Service.impl;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {


    public   Integer createCreditScoreToCustomer() {

        Integer score = (int)Math.floor(Math.random()*(1000));
       return score;
    }
}
