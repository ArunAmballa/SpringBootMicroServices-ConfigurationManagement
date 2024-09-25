package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.CustomerAccountsResponseDto;
import com.eazybytes.accounts.record.AccountsInfoDto;
import com.eazybytes.accounts.services.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {

    private final IAccountsService accountsService;

    @Autowired
    private AccountsInfoDto accountsInfoDto;


    @PostMapping("/create")
    public ResponseEntity<CustomerAccountsResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto){
        return new ResponseEntity<>(accountsService.createAccount(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerAccountsResponseDto> getAccountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "mobileNumber must be 10 digits") String mobileNumber){
        CustomerAccountsResponseDto customerAccountsResponseDto = accountsService.fetchAccount(mobileNumber);
        return new ResponseEntity<>(customerAccountsResponseDto, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<CustomerAccountsResponseDto> updateAccount(@RequestParam String mobileNumber, @RequestBody @Valid AccountsDto accountsDto){

        CustomerAccountsResponseDto customerResponseDto = accountsService.updateAccount(accountsDto,mobileNumber);

        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Boolean deleteAccount(@RequestParam String mobileNumber){
        Boolean b = accountsService.deleteAccount(mobileNumber);
        return b;

    }

    @GetMapping("/build-info")
    public ResponseEntity<AccountsInfoDto> getAccountsInfo(){
        return new ResponseEntity<>(accountsInfoDto,HttpStatus.OK);
    }



}
