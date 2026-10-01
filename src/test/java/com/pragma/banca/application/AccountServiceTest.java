package com.pragma.banca.application;

import com.pragma.banca.domain.Account;
import com.pragma.banca.domain.Holder;
import com.pragma.banca.domain.dto.AccountRequest;
import com.pragma.banca.domain.dto.AccountResponse;
import com.pragma.banca.domain.exception.AccountNotFoundException;
import com.pragma.banca.domain.exception.InvalidAccountException;
import com.pragma.banca.infrastructure.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias de AccountService")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private AccountRequest accountRequest;
    private Holder holder;

    @BeforeEach
    void setUp() {
        holder = new Holder("Juan Perez", "1234567890");
        account = new Account("ACC-001", holder, new BigDecimal("1000.00"), LocalDate.now());
        account.setId(1L);

        accountRequest = new AccountRequest("ACC-002", "Maria Lopez", "0987654321", new BigDecimal("500.00"));
    }

    @Test
    @DisplayName("Crear cuenta exitosamente")
    void testCreateAccount_Success() {
        when(accountRepository.existsByAccountNumber(accountRequest.accountNumber())).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse response = accountService.createAccount(accountRequest);

        assertNotNull(response);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("Crear cuenta con número duplicado debe lanzar excepción")
    void testCreateAccount_DuplicateAccountNumber_ThrowsException() {
        when(accountRepository.existsByAccountNumber(accountRequest.accountNumber())).thenReturn(true);

        assertThrows(InvalidAccountException.class, () -> accountService.createAccount(accountRequest));
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Obtener todas las cuentas")
    void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(account));

        List<AccountResponse> accounts = accountService.getAllAccounts();

        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Obtener cuenta por ID existente")
    void testGetAccountById_Found() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountResponse response = accountService.getAccountById(1L);

        assertNotNull(response);
        assertEquals("ACC-001", response.accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por ID no existente debe lanzar excepción")
    void testGetAccountById_NotFound_ThrowsException() {
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(99L));
    }

    @Test
    @DisplayName("Actualizar cuenta exitosamente")
    void testUpdateAccount_Success() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse response = accountService.updateAccount(1L, accountRequest);

        assertNotNull(response);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("Actualizar cuenta no existente debe lanzar excepción")
    void testUpdateAccount_NotFound_ThrowsException() {
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.updateAccount(99L, accountRequest));
    }

    @Test
    @DisplayName("Eliminar cuenta exitosamente")
    void testDeleteAccount_Success() {
        when(accountRepository.existsById(1L)).thenReturn(true);
        doNothing().when(accountRepository).deleteById(1L);

        assertDoesNotThrow(() -> accountService.deleteAccount(1L));
        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Eliminar cuenta no existente debe lanzar excepción")
    void testDeleteAccount_NotFound_ThrowsException() {
        when(accountRepository.existsById(99L)).thenReturn(false);

        assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccount(99L));
    }
}