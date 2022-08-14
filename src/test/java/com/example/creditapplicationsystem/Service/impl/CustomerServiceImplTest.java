package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import com.example.creditapplicationsystem.Model.Mapper.CustomerMapper;
import com.example.creditapplicationsystem.Repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private  CustomerServiceImpl customerService;

    @Test
    void createCustomer() {

        CustomerDTO customerDTO= new CustomerDTO();
        Customer expectedCustomer = CustomerMapper.toEntity(customerDTO);
        Mockito.when(customerRepository.save(expectedCustomer)).thenReturn(expectedCustomer);

        // then step
        Customer actualCustomer = customerService.createCustomer(customerDTO);


        // validate step
        Assert.assertEquals(actualCustomer, expectedCustomer);


    }

    @Test
    void getAllCustomer() {
        //init
        List<Customer> expectedCustomer=getSampleList();

        //stub
        Mockito.when(customerRepository.findAll()).thenReturn(expectedCustomer);

        //then
        List<Customer>actualCustomers=customerService.getAllCustomer();

    }

    @Test
    void getCustomer_succesfull() {
        //init
        Customer expectedCustomer=getSampleList().get(0);
        Optional<Customer> optExpectedCustomer=Optional.of(expectedCustomer);
        //stub
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(optExpectedCustomer);

        //then
        Customer actualCustomer=customerService.getCustomer("18691067974");

        Assert.assertEquals(actualCustomer.getIdentityNationalNumber(),expectedCustomer.getIdentityNationalNumber());



    }
    @Test
    void getCustomer_Notsuccesfull() {
      //stub-when step
        Mockito.when(customerRepository.findById("1869521465")).thenReturn(Optional.empty());
    // then validate step
        Assert.assertThrows(EntityNotFoundException.class,
                ()->{
            Customer actualCustomer=customerService.getCustomer("1869521465");
                });

    }



    @Test
    void deleteCustomer() {
        // init step
        Customer customer = getSampleList().get(0);
        Optional<Customer> optionalCustomer = Optional.of(customer);
        // stub - when step
        Mockito.when(customerRepository.findById(customer.getIdentityNationalNumber())).thenReturn(optionalCustomer);
        // doNothing().when(advertRepository).deleteById(advert.getId());
        // then - validate step
        customerService.deleteCustomer(customer.getIdentityNationalNumber());
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customer.getIdentityNationalNumber());
    }
    private List<Customer> getSampleList() {
        List<Customer> sampleList = new ArrayList<>();
        Customer customer = new Customer("18694067810", "mali", "zorba", "5055366199", 5000.0,"asd@hotmail.com", null);
        Customer customer1 = new Customer("18691067974", "mehmet", "kolayba", "565324123", 4000.0, "bsd@hotmail.com", null);
        Customer customer2 = new Customer("18781067810", "example", "4example", "5689856325", 6000.0, "csd@hotmail.com", null);

        sampleList.add(customer);
        sampleList.add(customer1);
        sampleList.add(customer2);

        return sampleList;

    }

}