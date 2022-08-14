package com.example.creditapplicationsystem.Service.impl;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {


    public Double createCreditScore() {

        Double score = (Double) Math.floor(Math.random()*(1500-200+1)+200);
       return score;
    }
}
