package com.pragma.banca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record AccountRequest(
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    String accountNumber,

    @NotNull(message = "El ID del titular no puede ser nulo")
    Long holderId,

    @PositiveOrZero(message = "El saldo inicial no puede ser negativo")
    BigDecimal initialBalance
) {
    public AccountRequest {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (holderId == null) {
            throw new IllegalArgumentException("El ID del titular no puede ser nulo");
        }
        if (initialBalance != null && initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
    }
}