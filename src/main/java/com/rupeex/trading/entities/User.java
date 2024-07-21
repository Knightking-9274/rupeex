package com.rupeex.trading.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rupeex.trading.domain.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;

    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Embedded
    private TwoFactorAuth twoFactorAuth= new TwoFactorAuth();

    private UserRole role = UserRole.Role_Customer;




}
