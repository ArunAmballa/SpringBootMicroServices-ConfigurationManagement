package com.eazybytes.accounts.services;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerAccountsResponseDto;
import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {


    CustomerAccountsResponseDto createAccount(CustomerDto customerDto);

    CustomerAccountsResponseDto updateAccount(AccountsDto accountsDto, String mobileNumber);

    CustomerAccountsResponseDto fetchAccount(String mobileNumber);

    Boolean deleteAccount(String mobileNumber);

}
