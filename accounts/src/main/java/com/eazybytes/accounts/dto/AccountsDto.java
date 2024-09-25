package com.eazybytes.accounts.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {


    @NotEmpty(message = "AccountType can not be a null or empty")
    private String accountType;

}
