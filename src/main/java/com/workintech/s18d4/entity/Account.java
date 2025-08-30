package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "account")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;                  // <-- primitive long

    @NotBlank
    @Column(name = "account_name")
    private String accountName;

    @Column(name = "money_amount")
    private double moneyAmount;       // <-- primitive double

    // Controller'da Double geldiğinde hata olmasın:
    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount == null ? 0.0 : moneyAmount;
    }
    public void setMoneyAmount(long moneyAmount) { this.moneyAmount = (double) moneyAmount; }
    public void setMoneyAmount(int moneyAmount)  { this.moneyAmount = (double) moneyAmount; }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("accounts")
    private Customer customer;
}
