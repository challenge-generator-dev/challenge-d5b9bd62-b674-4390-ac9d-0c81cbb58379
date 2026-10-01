package com.pragma.banca.domain.exception;

public class AccountNotFoundException extends RuntimeException {
    private final Long accountId;
    private final String accountNumber;

    public AccountNotFoundException(Long accountId) {
        super(String.format("No se encontró la cuenta con ID: %d", accountId));
        this.accountId = accountId;
        this.accountNumber = null;
    }

    public AccountNotFoundException(String accountNumber) {
        super(String.format("No se encontró la cuenta con número: %s", accountNumber));
        this.accountId = null;
        this.accountNumber = accountNumber;
    }

    public AccountNotFoundException(Long accountId, String accountNumber) {
        super(String.format("No se encontró la cuenta con ID: %d o número: %s", accountId, accountNumber));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.accountId = null;
        this.accountNumber = null;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getErrorCode() {
        return "ACCOUNT_NOT_FOUND";
    }

    public String getErrorMessage() {
        return getMessage();
    }
}