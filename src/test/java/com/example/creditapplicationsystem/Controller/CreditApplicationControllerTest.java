package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Exception.handler.GenericExceptionHandler;
import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Service.impl.CreditApplicationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class CreditApplicationControllerTest {

    private MockMvc mvc;

    @Mock
    CreditApplicationService creditApplicationService;

    @InjectMocks
    CreditApplicationController creditApplicationController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(creditApplicationController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void getAllACreditApplication() throws Exception {
// init test values / given
        List<CreditApplication> expectedApp = getSampleList();

        // stub - when
        when(creditApplicationService.getAllApply()).thenReturn(expectedApp);

        MockHttpServletResponse response =mvc.perform(get("/api/CreditApplication/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<CreditApplication> actualApp = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<CreditApplication>>() {
        });
        Assert.assertEquals(expectedApp.size(), actualApp.size());

    }



    @Test
    void getCreditApplicationbyId() throws Exception {

        // init test values
        List<CreditApplication> expectedApp = getSampleList();

        // stub - given
        when(creditApplicationService.getApplyById(1L)).thenReturn(expectedApp.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/CreditApplication/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        CreditApplication actualApp = new ObjectMapper().readValue(response.getContentAsString(), CreditApplication.class);
        Assert.assertEquals(expectedApp.get(0), actualApp);
    }


    @Test
    void getCreditApplicationByCustomerId()throws Exception {
        // init test values
        List<CreditApplication> expectedApp = getSampleList();

        // stub - given
        when(creditApplicationService.getCreditApplicationByCustomerId("11111111111")).thenReturn(expectedApp.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/CreditApplication//customer/11111111111")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        CreditApplication actualApp = new ObjectMapper().readValue(response.getContentAsString(), CreditApplication.class);
        Assert.assertEquals(expectedApp.get(0), actualApp);
    }
    private List<CreditApplication> getSampleList() {
        List<CreditApplication> sampleList = new ArrayList<>();
        CreditApplication creditApplication = new CreditApplication(1L, null, null, null);
        CreditApplication creditApplication1 = new CreditApplication(1L, null, null, null);
        CreditApplication creditApplication2 = new CreditApplication(2L, null, null, null);

        sampleList.add(creditApplication);
        sampleList.add(creditApplication1);
        sampleList.add(creditApplication2);

        return sampleList;

    }
}