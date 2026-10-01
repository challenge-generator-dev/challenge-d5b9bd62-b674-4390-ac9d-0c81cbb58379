package com.pragma.banca.domain.dto;

import com.pragma.banca.domain.Account;
import com.pragma.banca.domain.Holder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "DTO de respuesta para consultas de cuentas bancarias")
public record AccountResponse(
    @Schema(description = "Identificador único de la cuenta", example = "1")
    Long id,
    @Schema(description = "Número de cuenta bancaria único", example = "1234567890")
    String accountNumber,
    @Schema(description = "Información del titular de la cuenta")
    HolderResponse holder,
    @Schema(description = "Saldo actual de la cuenta", example = "1000.00")
    BigDecimal balance,
    @Schema(description = "Fecha de apertura de la cuenta", example = "2024-01-15")
    LocalDate openingDate
) {
    public static AccountResponse fromDomain(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountResponse(
            account.getId(),
            account.getAccountNumber(),
            HolderResponse.fromDomain(account.getHolder()),
            account.getBalance(),
            account.getOpeningDate()
        );
    }

    public Account toDomain() {
        return new Account(
            this.id,
            this.accountNumber,
            this.holder != null ? this.holder.toDomain() : null,
            this.balance,
            this.openingDate
        );
    }

    @Schema(description = "DTO de respuesta para información del titular")
    public record HolderResponse(
        @Schema(description = "Identificador único del titular", example = "1")
        Long id,
        @Schema(description = "Nombre completo del titular", example = "Juan Pérez")
        String name,
        @Schema(description = "Número de identificación del titular", example = "12345678")
        String identificationNumber
    ) {
        public static HolderResponse fromDomain(Holder holder) {
            if (holder == null) {
                return null;
            }
            return new HolderResponse(
                holder.getId(),
                holder.getName(),
                holder.getIdentificationNumber()
            );
        }

        public Holder toDomain() {
            return new Holder(
                this.id,
                this.name,
                this.identificationNumber
            );
        }
    }
}