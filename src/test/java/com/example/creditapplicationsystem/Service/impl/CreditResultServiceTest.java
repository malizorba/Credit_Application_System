package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.example.creditapplicationsystem.Repository.CreditResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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
    }

    @Test
    void getCreditResultByCustomerId() {
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