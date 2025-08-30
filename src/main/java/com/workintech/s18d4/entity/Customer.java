package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter @Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // primitive

    @NotBlank @Column(name="first_name")
    private String firstName;

    @NotBlank @Column(name="last_name")
    private String lastName;

    @Email @NotBlank @Column(unique = true)
    private String email;

    // testler salary'siz persist ediyor, bu yüzden nullable:
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    @JsonIgnoreProperties("customer")
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<Account> accounts = new ArrayList<>();
}
