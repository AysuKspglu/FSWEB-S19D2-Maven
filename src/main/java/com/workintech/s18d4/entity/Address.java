package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String street;

    // Testlerde bazen int, bazen Long geliyor → Long tut
    @NotNull
    private Long no;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    // optional
    private String description;

    // çift yönlü ilişki: sahibi Customer
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Customer customer;

    // ---------- EK CTOR'lar: int/Integer kabul etsin ----------
    public Address(Long id, String street, int no, String city, String country, String description, Customer customer) {
        this.id = id;
        this.street = street;
        this.no = (long) no;
        this.city = city;
        this.country = country;
        this.description = description;
        this.customer = customer;
    }

    public Address(Long id, String street, Integer no, String city, String country, String description, Customer customer) {
        this.id = id;
        this.street = street;
        this.no = (no == null ? null : no.longValue());
        this.city = city;
        this.country = country;
        this.description = description;
        this.customer = customer;
    }

    // ---------- EK SETTER'lar: int/Integer kabul etsin ----------
    public void setNo(int no) { this.no = (long) no; }
    public void setNo(Integer no) { this.no = (no == null ? null : no.longValue()); }

    // ---------- Builder override: no(int)/no(Integer) kabul etsin ----------
    public static class AddressBuilder {
        public AddressBuilder no(int no) { this.no = (long) no; return this; }
        public AddressBuilder no(Integer no) { this.no = (no == null ? null : no.longValue()); return this; }
    }
}
