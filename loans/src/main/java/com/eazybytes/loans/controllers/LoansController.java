package com.eazybytes.loans.controllers;


import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.record.LoansInfoDto;
import com.eazybytes.loans.services.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoansController {

    private final ILoansService loansService;

    @Autowired
    private LoansInfoDto loansInfoDto;


    @PostMapping("/create")
    public ResponseEntity<LoansDto> createLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits") String mobileNumber) {
        LoansDto loan = loansService.createLoan(mobileNumber);
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> getLoanByMobileNumber(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")  String mobileNumber) {
        LoansDto loansDto = loansService.fetchLoan(mobileNumber);
        return new ResponseEntity<>(loansDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<LoansDto> updateLoan(@RequestBody @Valid LoansDto loansDto, @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits") String mobileNumber) {
        LoansDto updatedLoansDto = loansService.updateLoan(loansDto, mobileNumber);
        return new ResponseEntity<>(updatedLoansDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Boolean deleteLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits") String mobileNumber) {

        boolean b = loansService.deleteLoan(mobileNumber);
        return b;
    }


    @GetMapping("/build-info")
    public ResponseEntity<LoansInfoDto> getLoansInfo() {
        return new ResponseEntity<>(loansInfoDto, HttpStatus.OK);
    }
}
