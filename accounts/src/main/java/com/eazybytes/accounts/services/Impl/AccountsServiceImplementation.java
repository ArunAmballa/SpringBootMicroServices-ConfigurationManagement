package com.eazybytes.accounts.services.Impl;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerAccountsResponseDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entities.Accounts;
import com.eazybytes.accounts.entities.Customer;
import com.eazybytes.accounts.exceptions.CustomerAlreadyExistException;
import com.eazybytes.accounts.exceptions.ResourceNotFoundException;
import com.eazybytes.accounts.repositories.AccountsRepository;
import com.eazybytes.accounts.repositories.CustomerRepository;
import com.eazybytes.accounts.services.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImplementation implements IAccountsService {

    private final AccountsRepository accountsRepository;

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CustomerAccountsResponseDto createAccount(CustomerDto customerDto) {

        Optional<Customer> customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(customer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exist with given mobile number"+customerDto.getMobileNumber());
        }

        Customer customerToBeSaved = modelMapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customerToBeSaved);

        Accounts newAccount = new Accounts();
        newAccount.setCustomer(savedCustomer);
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        Accounts savedAccount = accountsRepository.save(newAccount);

        CustomerAccountsResponseDto customerAccountsResponseDto = CustomerAccountsResponseDto
                .builder()
                .customerId(savedCustomer.getCustomerId())
                .name(savedCustomer.getName())
                .mobileNumber(savedCustomer.getMobileNumber())
                .email(savedCustomer.getEmail())
                .accountNumber(savedAccount.getAccountNumber())
                .accountType(savedAccount.getAccountType())
                .branchAddress(savedAccount.getBranchAddress())
                .build();

        return customerAccountsResponseDto;
    }

    @Override
    public CustomerAccountsResponseDto updateAccount(AccountsDto accountsDto, String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer with mobile number " + mobileNumber + " not found"));
        Accounts accounts = accountsRepository.findByCustomer(customer).orElseThrow(() -> new ResourceNotFoundException("Account for Specific Customer  not found"));
        accounts.setAccountType(accountsDto.getAccountType());
        Accounts savedAccount = accountsRepository.save(accounts);
        CustomerAccountsResponseDto customerAccountsResponseDto = CustomerAccountsResponseDto
                .builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .mobileNumber(customer.getMobileNumber())
                .email(customer.getEmail())
                .accountNumber(savedAccount.getAccountNumber())
                .accountType(savedAccount.getAccountType())
                .branchAddress(savedAccount.getBranchAddress())
                .build();

        return customerAccountsResponseDto;

    }

    @Override
    public CustomerAccountsResponseDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer with mobile number " + mobileNumber + " not found"));
        Accounts accounts = accountsRepository.findByCustomer(customer).orElseThrow(() -> new ResourceNotFoundException("Account for Specific Customer not found"));
        CustomerAccountsResponseDto customerAccountsResponseDto = CustomerAccountsResponseDto
                .builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .mobileNumber(customer.getMobileNumber())
                .email(customer.getEmail())
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .build();
        return customerAccountsResponseDto;
    }

    @Override
    public Boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer with mobile number " + mobileNumber + " not found"));
        Accounts accounts = accountsRepository.findByCustomer(customer).orElseThrow(() -> new ResourceNotFoundException("Account for Specific Customer not found"));
        accountsRepository.delete(accounts);
        customerRepository.delete(customer);
        return true;
    }
}
