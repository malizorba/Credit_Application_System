package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Exception.NotFoundException;
import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import com.example.creditapplicationsystem.Repository.CreditApplicationRepository;
import com.example.creditapplicationsystem.Service.constant.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor


public class CreditApplicationService implements com.example.creditapplicationsystem.Service.CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;

    private final CreditResultService creditResultService;





    @Override
    public List<CreditApplication> getAllApply() {
        List<CreditApplication> AllCreditApplication = creditApplicationRepository.findAll();
        return AllCreditApplication;
    }

    public CreditApplication getApplyById(Long id) {
        Optional<CreditApplication> CreditApplyById = creditApplicationRepository.findById(id);
        return CreditApplyById.orElseThrow(()->new EntityNotFoundException("Credit Application"));

    }

    @Override
    public CreditApplication createCreditApplication(CreditApplication creditApplication) {
        creditResultService.createCreditResultToCreditApp(creditApplication);
        return creditApplicationRepository.save(creditApplication);



        }

    @Override
    public boolean deleteCreditApplication(Long id) {
        creditApplicationRepository.deleteById(id);
        return true;
    }

    public void createCreditAppToCustomer(Customer customer) {
        CreditApplication creditApplication =new CreditApplication();
        creditApplication.setCustomer(customer);
        createCreditApplication(creditApplication);
    }

    public CreditApplication getCreditApplicationByCustomerId(String nationalIdentityNumber) {
        List<CreditApplication> creditApplication = getAllApply();
        return creditApplication.stream()
                .filter((l) -> l.getCustomer().getIdentityNationalNumber().equals(nationalIdentityNumber)).findFirst()
                .orElseThrow(() -> new NotFoundException(Messages.creditApplicationDoesntFound));
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











}
