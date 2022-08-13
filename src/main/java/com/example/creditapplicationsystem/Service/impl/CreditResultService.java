package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Exception.NotFoundException;
import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.example.creditapplicationsystem.Repository.CreditResultRepository;
import com.example.creditapplicationsystem.Service.constant.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditResultService {
    private final CreditResultRepository creditResultRepository;

    private final CreditScoreService creditScoreService;


    public CreditResult CalculateCreditLimit(CreditResult creditResult) {

        Customer customer = new Customer();
        creditResult.setCreditScore(creditScoreService.createCreditScore());

        if (creditResult.getCreditScore() >= 500 && creditResult.getCreditScore() < 1000) {
            creditResult.setApprovalStatus(ApprovalStatus.OK);
            creditResultRepository.save(creditResult);
            if (customer.getMonthlySalary() < 5000) {
                creditResult.setCreditLimit(10000);
                return creditResultRepository.save(creditResult);
            } else {
                creditResult.setCreditLimit(20000);
                return creditResultRepository.save(creditResult);
            }
        } else {
            creditResult.setApprovalStatus(ApprovalStatus.REJECTED);
            creditResult.setCreditLimit(0);
            creditResultRepository.save(creditResult);
        }
        if (creditResult.getCreditScore() >= 1000) {
            creditResult.setApprovalStatus(ApprovalStatus.OK);
            creditResult.setCreditLimit((int) (customer.getMonthlySalary() * 4));

            creditResultRepository.save(creditResult);
        }

        return creditResultRepository.save(creditResult);


    }



//    public CreditResult createCreateCreditResult(CreditResult creditResult) {
//        return creditResultRepository.save(creditResult);
//    }

    public void createCreditResultToCreditApp(CreditApplication creditApplication) {
        CreditResult creditResult = new CreditResult();
        creditResult.setCreditApplication(creditApplication);
        CalculateCreditLimit(creditResult);
    }
//    public void createCreditResultToCustomer(Customer customer) {
//        CreditResult creditResult = new CreditResult();
//        creditResult.setCustomer(customer);
//        createCreateCreditResult(creditResult);
//    }

    public List<CreditResult> getAllResult() {
        List<CreditResult> AllCreditResult = creditResultRepository.findAll();
        return AllCreditResult;
    }

    public CreditResult getResultyById(Long id) {
        Optional<CreditResult> CreditApplyById = creditResultRepository.findById(id);
        return CreditApplyById.orElseThrow(() -> new EntityNotFoundException("Credit Result"));
    }
    public CreditResult getCreditResultByCustomerId(String nationalIdentityNumber) {
        List<CreditResult> creditResultList = getAllResult();
        return creditResultList.stream()
                .filter((l) -> l.getCustomer().getIdentityNationalNumber().equals(nationalIdentityNumber)).findFirst()
                .orElseThrow(() -> new NotFoundException(Messages.creditApplicationDoesntFound));
    }
}
