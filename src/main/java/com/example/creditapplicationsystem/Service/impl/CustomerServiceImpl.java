package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Exception.DuplicateINexception;
import com.example.creditapplicationsystem.Exception.NotFoundException;
import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.example.creditapplicationsystem.Model.Mapper.CustomerMapper;
import com.example.creditapplicationsystem.Repository.CustomerRepository;
import com.example.creditapplicationsystem.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {



    private final CustomerRepository customerRepository;

    private final CreditApplicationService creditApplicationService;

    private final CreditScoreService creditScoreService;







    @Override
    public Customer createCustomer (CustomerDTO customerDTO) throws DuplicateINexception {
        if (customerRepository.existsById(customerDTO.getNationalIdentityNumber())) {
            throw new DuplicateINexception();
        } else {
            Customer customer = CustomerMapper.toEntity(customerDTO);
            CreditApplication creditApplication = creditApplicationService.createCreditAppToCustomer(customer.getIdentityNationalNumber());


            Double score = creditScoreService.createCreditScore();
            creditApplication.setCreditScore(score.intValue());

            if (score >= 500.0 && score < 1000.0 && customer.getMonthlySalary() < 5000.0) {
                creditApplication.setApprovalStatus(ApprovalStatus.OK);
                creditApplication.setCreditLimit(10000);


            } else if (score >= 500.0 && score < 1000.0 && customer.getMonthlySalary() >= 5000.0) {
                creditApplication.setApprovalStatus(ApprovalStatus.OK);
                creditApplication.setCreditLimit(20000);


            } else if (score < 500.0) {
                creditApplication.setApprovalStatus(ApprovalStatus.REJECTED);

            } else if (score > 1000) {
                creditApplication.setApprovalStatus(ApprovalStatus.OK);
                creditApplication.setCreditLimit((int) (customer.getMonthlySalary() * 4));

            }
            customer.setLoanList(creditApplication);


            return customerRepository.save(customer);

        }
    }




    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(String nationalIdentityNumber) {
        Optional<Customer> customer = customerRepository.findById(nationalIdentityNumber);
        return customer.orElseThrow(() -> new NotFoundException("Customer"));
    }

//    @Override
//    public Customer updateCustomer(CustomerDTO customerDTO,String email) { // değişkenlere bak.
//        if (!customerRepository.existsById(customerDTO.getNationalIdentityNumber())) {
//            throw new NotFoundException("Customer IN: " + customerDTO.getNationalIdentityNumber() + " not found!");
//        }
//        return customerRepository.save(toEntity(customerDTO));
//    }

    @Override
    public Customer updateCustomerBySalary(String nationalIdentityNumber,CustomerDTO customerDTO) {


        Optional <Customer> CustomerByid = customerRepository.findById(nationalIdentityNumber);
        if (!CustomerByid.isPresent()) {
            throw new NotFoundException("Customer IN: " + customerDTO.getNationalIdentityNumber() + " not found!");
        }
        Customer updated = CustomerByid.get();
        updated.setMonthlySalary(customerDTO.getMonthlySalary());
        return customerRepository.save(updated);
        }



    @Override
    public boolean deleteCustomer(String nationalIdentityNumber) {
        if (!customerRepository.existsById(nationalIdentityNumber)){
            throw new NotFoundException("Customer has not been found to delete");
        }else {
            customerRepository.delete(getCustomer(nationalIdentityNumber));
            return true;
        }
    }

//    @Override
//    public Customer AddApply(String nationalIdentityNumber, CreditApplication creditApplication) {
//        Customer customer=getCustomer(nationalIdentityNumber);
//       CreditApplication creditApplication1=creditApplicationService.getApplyById(creditApplication.getId());
//        List<CreditApplication> creditApplicationList=customer.getLoanList();
//        creditApplicationList.add(creditApplication1);
//        return customerRepository.save(customer);
//    }

//    public void createCreditAppToCustomer(CreditResult creditResult) {
//        Customer customer
//        creditApplication.setCustomer(customer);
//        createCreditApplication(creditApplication);
//
//    }
    }
//    public void createCreditResultToCustomer(CreditResult creditResult) {
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setCreditResultList(creditResult);
//        createCustomer(customerDTO);
//    }



