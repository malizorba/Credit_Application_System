package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Exception.DuplicateINexception;
import com.example.creditapplicationsystem.Exception.NotFoundException;
import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.Customer;
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

    private final CreditScoreService creditScoreService;

    private final CustomerRepository customerRepository;



    @Override
    public Customer createCustomer (CustomerDTO customerDTO) throws DuplicateINexception {
        if (customerRepository.existsById(customerDTO.getNationalIdentityNumber())) {
            throw new DuplicateINexception();
        } else {
            Customer customer= CustomerMapper.toEntity(customerDTO);


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

}