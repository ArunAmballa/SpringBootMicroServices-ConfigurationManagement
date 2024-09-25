package com.eazybytes.accounts.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Accounts extends BaseEntity {

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
