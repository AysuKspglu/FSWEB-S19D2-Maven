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

    // Testler bazı yerlerde int, bazı yerlerde Long veriyor → alanı Long tut, overload setter ekle
    @NotNull
    private Long no;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    // optional
    private String description;

    // Çift yönlü ilişki: sahibi Customer
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Customer customer;

    // --------- Overload setter'lar (int/Integer kabul etsin) ---------
    // Lombok @Setter Long için setNo(Long) oluşturur; aşağıdakiler ek kapılar:
    public void setNo(int no) { this.no = (long) no; }
    public void setNo(Integer no) { this.no = (no == null ? null : no.longValue()); }
    // Long için Lombok'un ürettiği setNo(Long) zaten mevcut
}
