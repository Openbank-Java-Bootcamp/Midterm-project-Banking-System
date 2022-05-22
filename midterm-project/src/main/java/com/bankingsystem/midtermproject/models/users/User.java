package com.bankingsystem.midtermproject.models.users;

import com.bankingsystem.midtermproject.models.Role.Role;
import com.bankingsystem.midtermproject.models.users.address.Address;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;

    private String password;


    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @Column(name= "date_of_birth")
    private Date dateOfBirth;

    @Embedded
    @Column(name = "primary_address")
    private Address primaryAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "mailing_street")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_postal"))
    })
    @Embedded
    @Column(name = "mailing_address")
    private Address mailingAddress;


    public User(String name, String username, String password, Collection<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String username, String password, Collection<Role> roles, Date dateOfBirth, Address primaryAddress, Address mailingAddress) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
}



