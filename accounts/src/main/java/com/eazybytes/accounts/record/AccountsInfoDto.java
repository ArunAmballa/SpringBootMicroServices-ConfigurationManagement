package com.eazybytes.accounts.record;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.List;
import java.util.Map;


@Getter
@Setter
@ConfigurationProperties(prefix = "accounts")
public class AccountsInfoDto {

    private String message;

    private Map<String,String> contactDetails;

    private List<String> onCallSupport;
}
