package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Repository.CreditApplicationRepository;
import com.example.creditapplicationsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor


public class CreditApplicationService implements com.example.creditapplicationsystem.Service.CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;

    private final CustomerServiceImpl customerService;
    private final CustomerRepository customerRepository;


    @Override
    public List<CreditApplication> getAllApply() {
        List<CreditApplication> AllCreditApplication = creditApplicationRepository.findAll();
        return AllCreditApplication;
    }

    public Optional<CreditApplication> getApplyById(Long id) {
        Optional<CreditApplication> CreditApplyById = creditApplicationRepository.findById(id);
        return CreditApplyById;
    }

    @Override
    public CreditApplication createCreditApplication(CreditApplication creditApplication) {

        return creditApplicationRepository.save(creditApplication);



        }

    @Override
    public boolean deleteCreditApplication(Long id) {
        creditApplicationRepository.deleteById(id);
        return true;
    }

//    @Override
//    public CreditApplication getApprovedCreditApplicationByCustomer(CreditApplication creditApplication) {
//        return getAllApply().stream()
//                .filter(c -> c.getApprovalStatus().equals(ApprovalStatus.OK))
//                .findAny()
//                .get();
//    }


//    @Override
//    public CreditApplication getNotApprovedCreditApplicationByCustomer(CreditApplication creditApplication) {
//        return getAllApply().stream()
//                .filter(c -> c.getApprovalStatus().equals(ApprovalStatus.REJECTED))
//                .findAny()
//                .get();
//    }




/*
    public ApprovalStatus CreditStatus(String IdentityNationalId){
        if (creditScoreService.createCreditScoreToCustomer()<500){
           CreditApplication creditApplication=new CreditApplication();
           creditApplication.setApprovalStatus(ApprovalStatus.OK); // bak bunlara
            return ApprovalStatus.REJECTED;
        }
        else {
            return ApprovalStatus.OK;
        }
    }

*/



}
