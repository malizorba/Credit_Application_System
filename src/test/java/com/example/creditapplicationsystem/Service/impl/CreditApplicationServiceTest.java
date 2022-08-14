package com.example.creditapplicationsystem.Service.impl;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Repository.CreditApplicationRepository;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

    @Mock
    CreditApplicationRepository creditApplicationRepository;



    @InjectMocks
    CreditApplicationService creditApplicationService;

    @Test
    void getAllApply() {
        //init
        List<CreditApplication> expectedApp = getSampleList();

        //stub
        Mockito.when(creditApplicationRepository.findAll()).thenReturn(expectedApp);

        //then
        List<CreditApplication> actualAllApp = creditApplicationService.getAllApply();

    }

    @Test
    void getApplyById() {

        //init
        CreditApplication expectedCreditApp = getSampleList().get(0);
        Optional<CreditApplication> optExpectedCreditApp = Optional.of(expectedCreditApp);
        //stub
        Mockito.when(creditApplicationRepository.findById(Mockito.any())).thenReturn(optExpectedCreditApp);

        //then
        CreditApplication actualCreditApp = creditApplicationService.getApplyById(1l);

        Assert.assertEquals(actualCreditApp.getDateofApply(), expectedCreditApp.getDateofApply());
        Assert.assertEquals(actualCreditApp.getId(), expectedCreditApp.getId());


    }

    @Test
    void createCreditApplication() {
        //init

        CreditApplication creditApplication=new CreditApplication(1L,null,null,null,null,null);

        //stub - when
        when(creditApplicationRepository.save(creditApplication)).thenReturn(creditApplication);

        //then step

        verify(creditApplicationRepository, times(1)).save(creditApplication);
    }


    @Test
    void deleteCreditApplication() {

        // init step
        CreditApplication creditApplication = getSampleList().get(0);
        Optional<CreditApplication> optionalCreditApplication = Optional.of(creditApplication);
        // stub - when step
        Mockito.when(creditApplicationRepository.findById(creditApplication.getId())).thenReturn(optionalCreditApplication);
        // doNothing().when(advertRepository).deleteById(advert.getId());
        // then - validate step
        creditApplicationService.deleteCreditApplication(creditApplication.getId());
        Mockito.verify(creditApplicationRepository, Mockito.times(1)).deleteById(creditApplication.getId());
    }

    private List<CreditApplication> getSampleList() {
        List<CreditApplication> sampleList = new ArrayList<>();
        CreditApplication creditApplication = new CreditApplication(1L, null,null,null,null,null);
        CreditApplication creditApplication1 = new CreditApplication(1L, null,null,null,null,null);
        CreditApplication creditApplication2 = new CreditApplication(2L, null,null,null,null,null);

        sampleList.add(creditApplication);
        sampleList.add(creditApplication1);
        sampleList.add(creditApplication2);

        return sampleList;

    }
}