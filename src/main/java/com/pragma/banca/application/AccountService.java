package com.pragma.banca.application;

import com.pragma.banca.domain.Account;
import com.pragma.banca.domain.Holder;
import com.pragma.banca.domain.dto.AccountRequest;
import com.pragma.banca.domain.dto.AccountResponse;
import com.pragma.banca.domain.exception.AccountNotFoundException;
import com.pragma.banca.domain.exception.InvalidAccountException;
import com.pragma.banca.infrastructure.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada con ID: " + id));
        return toResponse(account);
    }

    public AccountResponse createAccount(AccountRequest request) {
        validateAccountRequest(request);
        validateUniqueAccountNumber(request.accountNumber());

        Holder holder = new Holder(request.holderName(), request.holderIdentificationNumber());
        Account account = new Account(
                request.accountNumber(),
                holder,
                request.balance(),
                LocalDate.now()
        );

        Account savedAccount = accountRepository.save(account);
        return toResponse(savedAccount);
    }

    public AccountResponse updateAccount(Long id, AccountRequest request) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada con ID: " + id));

        validateAccountRequest(request);

        if (!existingAccount.getAccountNumber().equals(request.accountNumber())) {
            validateUniqueAccountNumber(request.accountNumber());
        }

        existingAccount.setAccountNumber(request.accountNumber());
        existingAccount.setBalance(request.balance());
        existingAccount.getHolder().setName(request.holderName());
        existingAccount.getHolder().setIdentificationNumber(request.holderIdentificationNumber());

        Account updatedAccount = accountRepository.save(existingAccount);
        return toResponse(updatedAccount);
    }

    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Cuenta no encontrada con ID: " + id);
        }
        accountRepository.deleteById(id);
    }

    private void validateAccountRequest(AccountRequest request) {
        if (request.accountNumber() == null || request.accountNumber().isBlank()) {
            throw new InvalidAccountException("El número de cuenta es obligatorio");
        }

        if (request.accountNumber().length() < 8 || request.accountNumber().length() > 20) {
            throw new InvalidAccountException("El número de cuenta debe tener entre 8 y 20 caracteres");
        }

        if (request.balance() == null) {
            throw new InvalidAccountException("El saldo es obligatorio");
        }

        if (request.balance().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAccountException("El saldo no puede ser negativo");
        }

        if (request.holderName() == null || request.holderName().isBlank()) {
            throw new InvalidAccountException("El nombre del titular es obligatorio");
        }

        if (request.holderIdentificationNumber() == null || request.holderIdentificationNumber().isBlank()) {
            throw new InvalidAccountException("El número de identificación del titular es obligatorio");
        }
    }

    private void validateUniqueAccountNumber(String accountNumber) {
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            throw new InvalidAccountException("El número de cuenta ya existe: " + accountNumber);
        }
    }

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getHolder().getName(),
                account.getHolder().getIdentificationNumber(),
                account.getBalance(),
                account.getOpeningDate()
        );
    }
}