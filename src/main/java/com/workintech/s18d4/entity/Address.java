package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "address")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;          // primitive

    @NotBlank
    private String street;

    @Column(name = "no")
    private int no;           // <-- primitive int

    // testlerde Long/Integer gelebilir:
    public void setNo(Long n)    { this.no = (n == null) ? 0 : Math.toIntExact(n); }
    public void setNo(Integer n) { this.no = (n == null) ? 0 : n; }

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    private String description;

    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties("address")
    private Customer customer;
}
