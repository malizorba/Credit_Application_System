package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import com.example.creditapplicationsystem.Service.impl.CreditResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/CreditResult")
public class CreditResultController {

    private final CreditResultService creditResultService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity getAllACreditResult() {
        List<CreditResult> AllResult = creditResultService.getAllResult();
        return ResponseEntity.ok(AllResult);
    }
    @PreAuthorize(("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD_CLIENT')"))
    @GetMapping("/{id}")
    public ResponseEntity getCreditResultbyId(@PathVariable("id") Long id) {
        CreditResult byid = creditResultService.getResultyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byid);
    }
    @PreAuthorize(("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD_CLIENT')"))
    @GetMapping("/customer/{nationalIdentityNumber}")
    public CreditResult getCreditResultByCustomerId(@PathVariable  String nationalIdentityNumber){
        return creditResultService.getCreditResultByCustomerId(nationalIdentityNumber);
    }
}
