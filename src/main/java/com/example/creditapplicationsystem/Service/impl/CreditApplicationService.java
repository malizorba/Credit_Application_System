package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Exception.NotFoundException;
import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Repository.CreditApplicationRepository;
import com.example.creditapplicationsystem.Service.constant.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j


public class CreditApplicationService implements com.example.creditapplicationsystem.Service.CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;





    @Override
    public List<CreditApplication> getAllApply() {
        List<CreditApplication> AllCreditApplication = creditApplicationRepository.findAll();
        return AllCreditApplication;
    }

    public CreditApplication getApplyById(Long id) {
        Optional<CreditApplication> CreditApplyById = creditApplicationRepository.findById(id);
        log.info("Credit Application has been got");
        return CreditApplyById.orElseThrow(()->new EntityNotFoundException("Credit Application"));

    }




    @Override
    public boolean deleteCreditApplication(Long id) {
        creditApplicationRepository.deleteById(id);
        log.info("Credit Application has been deleted");
        return true;
    }

    public CreditApplication createCreditAppToCustomer(String nationalIdentityNumber) {

        CreditApplication creditApplication =new CreditApplication();

        return creditApplicationRepository.save(creditApplication);

    }

    public CreditApplication getCreditApplicationByCustomerId(String nationalIdentityNumber) {
        List<CreditApplication> creditApplication = getAllApply();
        return creditApplication.stream()
                .filter((l) -> l.getCustomer().getIdentityNationalNumber().equals(nationalIdentityNumber)).findFirst()
                .orElseThrow(() -> new NotFoundException(Messages.creditApplicationDoesntFound));

    }













}
