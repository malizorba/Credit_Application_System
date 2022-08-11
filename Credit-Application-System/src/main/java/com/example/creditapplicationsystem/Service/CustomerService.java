package com.example.creditapplicationsystem.Service;

import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerDTO customerDTO);

    List<Customer> getAllCustomer();

    Customer getCustomer(String nationalIdentityNumber);

//    Customer updateCustomer(CustomerDTO customerDTO);

    Customer updateCustomerBySalary(String nationalIdentityNumber,CustomerDTO customerDTO);


    boolean deleteCustomer(String nationalIdentityNumber);

}
