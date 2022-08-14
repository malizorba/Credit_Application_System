package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Exception.handler.GenericExceptionHandler;
import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.example.creditapplicationsystem.Service.impl.CreditResultService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class CreditResultControllerTest {

    private MockMvc mvc;

    @Mock
    private CreditResultService creditResultService;

    @InjectMocks
    CreditResultController creditResultController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(creditResultController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getAllACreditResult() throws Exception {
        // init test values / given
        List<CreditResult> expectedResult = getSampleList();

        // stub - when
        when(creditResultService.getAllResult()).thenReturn(expectedResult);

        MockHttpServletResponse response =mvc.perform(get("/api/CreditResult/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<CreditResult> actualApp = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<CreditResult>>() {
        });
        Assert.assertEquals(expectedResult.size(), actualApp.size());



    }

    @Test
    void getCreditResultbyId() throws Exception {
        // init test values
        List<CreditResult> expectedResult = getSampleList();

        // stub - given
        when(creditResultService.getResultyById(1L)).thenReturn(expectedResult.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/CreditResult/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        CreditResult actualResult = new ObjectMapper().readValue(response.getContentAsString(), CreditResult.class);
        Assert.assertEquals(expectedResult.get(0), actualResult);
    }

    @Test
    void getCreditResultByCustomerId() throws Exception {
        // init test values
        List<CreditResult> expectedResult = getSampleList();

        // stub - given
        Mockito.when(creditResultService.getCreditResultByCustomerId("11111111111")).thenReturn(expectedResult.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/CreditResult/customer/11111111111")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        CreditResult actualResult = new ObjectMapper().readValue(response.getContentAsString(), CreditResult.class);
        Assert.assertEquals(expectedResult.get(0), actualResult);


    }

    private List<CreditResult> getSampleList() {
        List<CreditResult> sampleList = new ArrayList<>();
        CreditResult creditResult = new CreditResult(null,555.0,10000.0, ApprovalStatus.OK,null,null);
        CreditResult creditResult1 = new CreditResult(2L,555.0,10000.0, ApprovalStatus.OK,null,null);
        CreditResult creditResult2 = new CreditResult(3L,555.0,10000.0, ApprovalStatus.OK,null,null);

        sampleList.add(creditResult);
        sampleList.add(creditResult1);
        sampleList.add(creditResult2);

        return sampleList;

    }
}