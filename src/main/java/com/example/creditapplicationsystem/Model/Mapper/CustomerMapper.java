package com.example.creditapplicationsystem.Model.Mapper;

import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
public static CustomerDTO toDTO(Customer customer){
    CustomerDTO customerDTO=new CustomerDTO();

    customerDTO.setNationalIdentityNumber(customer.getIdentityNationalNumber());
    customerDTO.setName(customer.getName());
    customerDTO.setLastName(customer.getLastName());
    customerDTO.setPhoneNumber(customer.getPhoneNumber());
    customerDTO.setEmail(customer.getEmail());
    customerDTO.setMonthlySalary(customer.getMonthlySalary());
    customerDTO.setLoanList(customer.getLoanList());
    customerDTO.setCreditResultList(customer.getCreditResultList());

    return customerDTO;
    }
    public static Customer toEntity(CustomerDTO customerDTO){
    Customer customer=new Customer();
    customer.setIdentityNationalNumber(customerDTO.getNationalIdentityNumber());
    customer.setName(customerDTO.getName());
    customer.setLastName(customerDTO.getLastName());
    customer.setPhoneNumber(customerDTO.getPhoneNumber());
    customer.setEmail(customerDTO.getEmail());
    customer.setMonthlySalary(customerDTO.getMonthlySalary());
    customer.setLoanList(customerDTO.getLoanList());
    customer.setCreditResultList(customerDTO.getCreditResultList());
    return customer;
    }
}
