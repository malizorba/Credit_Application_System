package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.example.creditapplicationsystem.Repository.CreditResultRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CreditResultServiceTest {

    @Mock
    CreditResultRepository creditResultRepository;

    @InjectMocks
    CreditResultService creditResultService;

    @Test
    void getAllResult() {
        //init
        List<CreditResult> expectedResult = getSampleList();

        //stub
        Mockito.when(creditResultRepository.findAll()).thenReturn(expectedResult);

        //then
        List<CreditResult> actualAllResult = creditResultService.getAllResult();

    }


    @Test
    void getResultyById() {
        //init
        CreditResult expectedCreditResult = getSampleList().get(0);
        Optional<CreditResult> optExpectedCreditResult = Optional.of(expectedCreditResult);
        //stub
        Mockito.when(creditResultRepository.findById(Mockito.any())).thenReturn(optExpectedCreditResult);

        //then
        CreditResult actualCreditResult = creditResultService.getResultyById(1L);

        Assert.assertEquals(actualCreditResult.getCreditScore(), expectedCreditResult.getCreditScore());
        Assert.assertEquals(actualCreditResult.getId(), expectedCreditResult.getId());
    }

    @Test
    void getCreditResultByCustomerId() {

        CreditResult expectedCreditResult = getSampleList().get(0);
        Optional<CreditResult> optionalExpResult=Optional.of(expectedCreditResult);

        //stub
        Mockito.when(creditResultRepository.findCreditResultByCustomer_IdentityNationalNumber("18691067974")).thenReturn(optionalExpResult);

        //then
        CreditResult actualCreditResult=creditResultService.getCreditResultByCustomerId("18694067810");
        Assert.assertEquals(actualCreditResult.getId(),expectedCreditResult.getId());
    }

    private List<CreditResult> getSampleList() {
        List<CreditResult> sampleList = new ArrayList<>();
        CreditResult creditResult = new CreditResult(1L,550.0,10000.0, ApprovalStatus.OK,null,null);
        CreditResult creditResult1 = new CreditResult(2L,550.0,10000.0, ApprovalStatus.OK,null,null);
        CreditResult creditResult2 = new CreditResult(3L,550.0,10000.0, ApprovalStatus.OK,null,null);

        sampleList.add(creditResult);
        sampleList.add(creditResult1);
        sampleList.add(creditResult2);

        return sampleList;

    }
}