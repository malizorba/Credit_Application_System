package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Exception.handler.GenericExceptionHandler;
import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import com.example.creditapplicationsystem.Service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private MockMvc mvc;

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getCustomer()throws Exception {
        // init test values / given
        List<Customer> expectedCustomer = getSampleList();

        // stub - when
        when(customerService.getCustomer("11111111111")).thenReturn(expectedCustomer.get(0));

        MockHttpServletResponse response =mvc.perform(get("/api/Customer/11111111111")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Customer actualCustomer = new ObjectMapper().readValue(response.getContentAsString(),Customer.class);

        Assert.assertEquals(expectedCustomer.get(0), actualCustomer);

    }


    @Test
    void getAllCustomer() throws Exception {
        // init test values / given
        List<Customer> expectedCustomer = getSampleList();

        // stub - when
        when(customerService.getAllCustomer()).thenReturn(expectedCustomer);

        MockHttpServletResponse response =mvc.perform(get("/api/Customer/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Customer> actualCustomer = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Customer>>() {
        });
        Assert.assertEquals(expectedCustomer.size(), actualCustomer.size());
    }

    @Test
    void createCustomer() throws Exception{
        // init test values
        List<Customer> expectedCustomers = getSampleList();
        Customer expectedCustomer = new Customer();
        expectedCustomer.setName("Henry");
        expectedCustomer.setLastName("Cavill");
        expectedCustomer.setIdentityNationalNumber("28795641582");
        expectedCustomer.setPhoneNumber("5064789852");
        expectedCustomer.setMonthlySalary(7500.0);
        expectedCustomers.add(expectedCustomer);


        // stub - given
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedcustomerJsonStr = ow.writeValueAsString(expectedCustomers);
        Mockito.doNothing().when(customerService).createCustomer((CustomerDTO) expectedCustomers);

        MockHttpServletResponse response = mvc.perform(post("/api/customer/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedcustomerJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(customerService, Mockito.times(1)).createCustomer(any());
    }







    @Test
    void deleteCustomer () throws Exception {
        // stub - given
        Mockito.when(customerService.deleteCustomer(any())).thenReturn(true);

        MockHttpServletResponse response = mvc.perform(delete("/api/customer/?id=11111111111")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    private List<Customer> getSampleList() {
        List<Customer> sampleList = new ArrayList<>();
        Customer customer=new Customer("11111111111","mali","zorba","5055366199",null,null,null);
        Customer customer1=new Customer("11111111112","mali","zorba","5055366199",null,null,null);
        Customer customer2=new Customer("11111111113","mali","zorba","5055366199",null,null,null);

        sampleList.add(customer);
        sampleList.add(customer1);
        sampleList.add(customer2);

        return sampleList;

    }
}