package com.eazybytes.loans.services;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoansService {

     /**
      *
      * @param mobileNumber - Mobile Number of the Customer
      */
     LoansDto createLoan(String mobileNumber);

     /**
      *
      * @param mobileNumber - Input mobile Number
      *  @return Loan Details based on a given mobileNumber
      */
     LoansDto fetchLoan(String mobileNumber);

     /**
      *
      * @param loansDto - LoansDto Object
      * @return boolean indicating if the update of card details is successful or not
      */
     LoansDto updateLoan(LoansDto loansDto, String mobileNumber);

     /**
      *
      * @param mobileNumber - Input Mobile Number
      * @return boolean indicating if the delete of loan details is successful or not
      */
     boolean deleteLoan(String mobileNumber);

}
