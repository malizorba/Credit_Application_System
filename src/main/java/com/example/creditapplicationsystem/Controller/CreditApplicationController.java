package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Service.impl.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/CreditApplication")
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity getAllACreditApplication() {
        List<CreditApplication> AllApp = creditApplicationService.getAllApply();
        return ResponseEntity.ok(AllApp);
    }

    @PreAuthorize(("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD_CLIENT')"))
    @GetMapping("/{id}")
    public ResponseEntity getCreditApplicationbyId(@PathVariable("id") Long id) {
        CreditApplication byid = creditApplicationService.getApplyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byid);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity deleteCreditApplication (@RequestParam(name = "id")Long id){
        creditApplicationService.deleteCreditApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body("CreditApplication was deleted");
    }
    @PreAuthorize(("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD_CLIENT')"))
    @GetMapping("/customer/{nationalIdentityNumber}")
    public CreditApplication getCreditApplicationByCustomerId(@PathVariable  String nationalIdentityNumber){
        return creditApplicationService.getCreditApplicationByCustomerId(nationalIdentityNumber);
    }


}
