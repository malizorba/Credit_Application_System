package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Exception.NotFoundException;
import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
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
        CustomerDTO customerDTO=new CustomerDTO();
        creditResult.setCreditScore(creditScoreService.createCreditScore());

        if (creditResult.getCreditScore() >= 500.0 && creditResult.getCreditScore() < 1000.0 || customer.getMonthlySalary()<5000.0) {
            creditResult.setApprovalStatus(ApprovalStatus.OK);
            creditResult.setCreditLimit(10000.0);
            return creditResultRepository.save(creditResult);
        }
        else if (creditResult.getCreditScore() >= 500.0 && creditResult.getCreditScore() < 1000.0 ||customer.getMonthlySalary()>=5000.0){
                creditResult.setApprovalStatus(ApprovalStatus.OK);
                creditResult.setCreditLimit(20000.0);
            return creditResultRepository.save(creditResult);

        }
         else if (creditResult.getCreditScore()<500.0){
            creditResult.setApprovalStatus(ApprovalStatus.REJECTED);
            creditResult.setCreditLimit(0.0);
            return creditResultRepository.save(creditResult);
//            creditResultRepository.save(creditResult);
        }

        else if (creditResult.getCreditScore() > 1000) {
            creditResult.setApprovalStatus(ApprovalStatus.OK);
            creditResult.setCreditLimit(customerDTO.getMonthlySalary()*4);
            return creditResultRepository.save(creditResult);

        }

        return creditResultRepository.save(creditResult);


    }




    public void createCreditResultToCreditApp(CreditApplication creditApplication) {
        CreditResult creditResult = new CreditResult();
        creditResult.setCreditApplication(creditApplication);
        CalculateCreditLimit(creditResult);
    }


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
