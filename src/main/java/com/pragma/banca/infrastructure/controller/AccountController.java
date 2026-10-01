package com.pragma.banca.infrastructure.controller;

import com.pragma.banca.application.AccountService;
import com.pragma.banca.domain.dto.AccountRequest;
import com.pragma.banca.domain.dto.AccountResponse;
import com.pragma.banca.domain.exception.AccountNotFoundException;
import com.pragma.banca.domain.exception.InvalidAccountException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Gestión de Cuentas Bancarias", description = "API para la gestión de cuentas bancarias")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las cuentas", description = "Retorna una lista de todas las cuentas bancarias registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas obtenidas exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cuenta por ID", description = "Retorna una cuenta bancaria específica por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta encontrada",
                     content = @Content(schema = @Schema(implementation = AccountResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada",
                     content = @Content(schema = @Schema(implementation = AccountNotFoundException.class)))
    })
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        AccountResponse account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    @Operation(summary = "Crear nueva cuenta", description = "Crea una nueva cuenta bancaria con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de cuenta inválidos",
                     content = @Content(schema = @Schema(implementation = InvalidAccountException.class))),
        @ApiResponse(responseCode = "409", description = "El número de cuenta ya existe",
                     content = @Content(schema = @Schema(implementation = InvalidAccountException.class)))
    })
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponse createdAccount = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cuenta", description = "Actualiza los datos de una cuenta bancaria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente",
                     content = @Content(schema = @Schema(implementation = AccountResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada",
                     content = @Content(schema = @Schema(implementation = AccountNotFoundException.class))),
        @ApiResponse(responseCode = "400", description = "Datos de cuenta inválidos",
                     content = @Content(schema = @Schema(implementation = InvalidAccountException.class)))
    })
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, 
                                                          @Valid @RequestBody AccountRequest request) {
        AccountResponse updatedAccount = accountService.updateAccount(id, request);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuenta", description = "Elimina una cuenta bancaria por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cuenta eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada",
                     content = @Content(schema = @Schema(implementation = AccountNotFoundException.class)))
    })
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}