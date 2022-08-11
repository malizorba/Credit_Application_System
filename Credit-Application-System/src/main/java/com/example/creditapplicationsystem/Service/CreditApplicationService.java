package com.example.creditapplicationsystem.Service;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;

import java.util.List;
import java.util.Optional;

public interface CreditApplicationService {

    public List<CreditApplication> getAllApply ();

    public Optional<CreditApplication> getApplyById (Long id);


//    CreditApplication createCreditApplication(CreditApplication creditApplication);
//
//    CreditApplication getApprovedCreditApplicationByCustomer(CreditApplication creditApplication);
//
//    CreditApplication getNotApprovedCreditApplicationByCustomer(CreditApplication creditApplication);



    CreditApplication createCreditApplication(CreditApplication creditApplication);

    boolean deleteCreditApplication(Long id);


}
