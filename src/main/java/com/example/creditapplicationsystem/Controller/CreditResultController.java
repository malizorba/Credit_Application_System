package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import com.example.creditapplicationsystem.Service.impl.CreditResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/CreditResult")
public class CreditResultController {

    private final CreditResultService creditResultService;

    @GetMapping("/all")
    public ResponseEntity getAllACreditResult() {
        List<CreditResult> AllResult = creditResultService.getAllResult();
        return ResponseEntity.ok(AllResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCreditApplicationbyId(@PathVariable("id") Long id) {
        CreditResult byid = creditResultService.getResultyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byid);
    }
    @GetMapping("/customer/{nationalIdentityNumber}")
    public CreditResult getCreditApplicationByCustomerId(@PathVariable  String nationalIdentityNumber){
        return creditResultService.getCreditResultByCustomerId(nationalIdentityNumber);
    }
}
