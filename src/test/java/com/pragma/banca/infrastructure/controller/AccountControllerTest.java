package com.pragma.banca.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.banca.application.AccountService;
import com.pragma.banca.domain.dto.AccountRequest;
import com.pragma.banca.domain.dto.AccountResponse;
import com.pragma.banca.domain.exception.AccountNotFoundException;
import com.pragma.banca.domain.exception.InvalidAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@DisplayName("Pruebas de integración de AccountController")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private AccountRequest accountRequest;
    private AccountResponse accountResponse;
    private static final String BASE_URL = "/api/v1/accounts";

    @BeforeEach
    void setUp() {
        accountRequest = new AccountRequest("ACC-001", "Juan Perez", "1234567890", new BigDecimal("1000.00"));
        accountResponse = new AccountResponse(1L, "ACC-001", "Juan Perez", "1234567890", new BigDecimal("1000.00"), LocalDate.now());
    }

    @Test
    @DisplayName("GET /api/v1/accounts - Obtener todas las cuentas")
    void testGetAllAccounts_ReturnsOk() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(List.of(accountResponse));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].accountNumber").value("ACC-001"));
    }

    @Test
    @DisplayName("GET /api/v1/accounts/{id} - Obtener cuenta por ID existente")
    void testGetAccountById_Found_ReturnsOk() throws Exception {
        when(accountService.getAccountById(1L)).thenReturn(accountResponse);

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.accountNumber").value("ACC-001"));
    }

    @Test
    @DisplayName("GET /api/v1/accounts/{id} - Obtener cuenta por ID no existente")
    void testGetAccountById_NotFound_ReturnsNotFound() throws Exception {
        when(accountService.getAccountById(99L)).thenThrow(new AccountNotFoundException("Cuenta no encontrada"));

        mockMvc.perform(get(BASE_URL + "/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v1/accounts - Crear cuenta exitosamente")
    void testCreateAccount_Success_ReturnsCreated() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class))).thenReturn(accountResponse);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.accountNumber").value("ACC-001"));
    }

    @Test
    @DisplayName("POST /api/v1/accounts - Crear cuenta con datos inválidos")
    void testCreateAccount_InvalidData_ReturnsBadRequest() throws Exception {
        AccountRequest invalidRequest = new AccountRequest(null, "", "", new BigDecimal("-100"));

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/v1/accounts - Crear cuenta con número duplicado")
    void testCreateAccount_DuplicateAccountNumber_ReturnsConflict() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenThrow(new InvalidAccountException("El número de cuenta ya existe"));

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/v1/accounts/{id} - Actualizar cuenta exitosamente")
    void testUpdateAccount_Success_ReturnsOk() throws Exception {
        when(accountService.updateAccount(eq(1L), any(AccountRequest.class))).thenReturn(accountResponse);

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("PUT /api/v1/accounts/{id} - Actualizar cuenta no existente")
    void testUpdateAccount_NotFound_ReturnsNotFound() throws Exception {
        when(accountService.updateAccount(eq(99L), any(AccountRequest.class)))
                .thenThrow(new AccountNotFoundException("Cuenta no encontrada"));

        mockMvc.perform(put(BASE_URL + "/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v1/accounts/{id} - Eliminar cuenta exitosamente")
    void testDeleteAccount_Success_ReturnsNoContent() throws Exception {
        doNothing().when(accountService).deleteAccount(1L);

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/v1/accounts/{id} - Eliminar cuenta no existente")
    void testDeleteAccount_NotFound_ReturnsNotFound() throws Exception {
        doThrow(new AccountNotFoundException("Cuenta no encontrada")).when(accountService).deleteAccount(99L);

        mockMvc.perform(delete(BASE_URL + "/99"))
                .andExpect(status().isNotFound());
    }
}