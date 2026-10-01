package com.pragma.banca.domain.exception;

import java.math.BigDecimal;

public class InvalidAccountException extends RuntimeException {
    private final String field;
    private final Object invalidValue;
    private final ErrorType errorType;

    public enum ErrorType {
        NEGATIVE_BALANCE,
        DUPLICATE_ACCOUNT_NUMBER,
        INVALID_ACCOUNT_NUMBER,
        MISSING_REQUIRED_FIELD,
        INVALID_HOLDER,
        INVALID_OPENING_DATE
    }

    public InvalidAccountException(String field, Object invalidValue, ErrorType errorType) {
        super(buildMessage(field, invalidValue, errorType));
        this.field = field;
        this.invalidValue = invalidValue;
        this.errorType = errorType;
    }

    public InvalidAccountException(String field, Object invalidValue, ErrorType errorType, Throwable cause) {
        super(buildMessage(field, invalidValue, errorType), cause);
        this.field = field;
        this.invalidValue = invalidValue;
        this.errorType = errorType;
    }

    public InvalidAccountException(BigDecimal balance) {
        super(String.format("El saldo no puede ser negativo: %s", balance));
        this.field = "balance";
        this.invalidValue = balance;
        this.errorType = ErrorType.NEGATIVE_BALANCE;
    }

    public InvalidAccountException(String accountNumber, boolean duplicate) {
        super(duplicate 
            ? String.format("El número de cuenta ya existe: %s", accountNumber)
            : String.format("El número de cuenta es inválido: %s", accountNumber));
        this.field = "accountNumber";
        this.invalidValue = accountNumber;
        this.errorType = duplicate ? ErrorType.DUPLICATE_ACCOUNT_NUMBER : ErrorType.INVALID_ACCOUNT_NUMBER;
    }

    private static String buildMessage(String field, Object invalidValue, ErrorType errorType) {
        return switch (errorType) {
            case NEGATIVE_BALANCE -> String.format("El saldo no puede ser negativo: %s", invalidValue);
            case DUPLICATE_ACCOUNT_NUMBER -> String.format("El número de cuenta ya existe: %s", invalidValue);
            case INVALID_ACCOUNT_NUMBER -> String.format("El número de cuenta es inválido: %s", invalidValue);
            case MISSING_REQUIRED_FIELD -> String.format("El campo requerido está vacío o es nulo: %s", field);
            case INVALID_HOLDER -> String.format("Los datos del titular son inválidos: %s", invalidValue);
            case INVALID_OPENING_DATE -> String.format("La fecha de apertura es inválida: %s", invalidValue);
        };
    }

    public String getField() {
        return field;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getErrorCode() {
        return "INVALID_ACCOUNT";
    }

    public String getErrorMessage() {
        return getMessage();
    }

    public boolean isNegativeBalance() {
        return errorType == ErrorType.NEGATIVE_BALANCE;
    }

    public boolean isDuplicateAccountNumber() {
        return errorType == ErrorType.DUPLICATE_ACCOUNT_NUMBER;
    }

    public boolean isMissingRequiredField() {
        return errorType == ErrorType.MISSING_REQUIRED_FIELD;
    }
}