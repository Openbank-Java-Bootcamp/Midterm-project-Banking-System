package com.bankingsystem.midtermproject.DTO;

import com.bankingsystem.midtermproject.models.money.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class newAccountDTO {

    private Money balance;
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String secretKey;
}
