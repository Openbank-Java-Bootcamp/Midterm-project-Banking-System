package com.bankingsystem.midtermproject.models.users.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String streetAddress;
    private String city;
    private String postalCode;
}

