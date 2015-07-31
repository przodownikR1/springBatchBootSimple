package pl.java.scalatech.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account {
    private String login;
    private BigDecimal saldo;
    private String creaditCardNumber;
    private boolean foreignCurrencyAccount;
}
