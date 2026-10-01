# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/banca/infrastructure/controller/AccountControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/banca/domain/dto/AccountResponse.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/banca/infrastructure/controller/AccountController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/banca/infrastructure/controller/AccountControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/banca/domain/dto/AccountResponse.java` — `Holder.toDomain`: Se invoca `toDomain` sobre `Holder`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.findAll`: Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.findById`: Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.save`: Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.existsById`: Se invoca `existsById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.deleteById`: Se invoca `deleteById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.save`: Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.findAll`: Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.findById`: Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountResponse.accountNumber`: Se invoca `accountNumber` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.existsById`: Se invoca `existsById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Crear una API REST con Spring Boot, JPA, validación de entidades y documentación OpenAPI

### Reto
- Tema: API REST en el dominio de banca
- Seniority: junior-l1
- Tipo: practical
- Título: Desarrollo de una API REST para gestión de cuentas bancarias
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición y validación de entidades — objetivo: Definir y validar las entidades de cuenta bancaria y titular. — entregable (NO resolver): Entidades de cuenta bancaria y titular validadas y documentadas.
- Fase 2: Implementación de la API REST — objetivo: Implementar los endpoints de la API REST para gestión de cuentas bancarias. — entregable (NO resolver): API REST implementada y documentada con OpenAPI.
- Fase 3: Pruebas y optimización — objetivo: Realizar pruebas unitarias y de integración y optimizar la API. — entregable (NO resolver): API REST con pruebas unitarias y de integración y optimizada.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>banca</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>banca</name>
    <description>API REST para gestión de cuentas bancarias</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi-ui.version>2.6.0</springdoc-openapi-ui.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi-ui.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.3.230</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/banca/BancaApplication.java ===
package com.pragma.banca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancaApplication.class, args);
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: banca
  datasource:
    url: jdbc:h2:mem:bancadb
    driverClassName: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true

server:
  port: 8080

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method

// === ARCHIVO: src/main/java/com/pragma/banca/domain/Account.java ===
package com.pragma.banca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    @NotNull(message = "El número de cuenta no puede ser nulo")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id", nullable = false)
    @NotNull(message = "El titular no puede ser nulo")
    private Holder holder;

    @Column(nullable = false)
    @PositiveOrZero(message = "El saldo no puede ser negativo")
    private BigDecimal balance;

    @Column(name = "opening_date", nullable = false)
    @NotNull(message = "La fecha de apertura no puede ser nula")
    private LocalDate openingDate;

    public Account() {
    }

    public Account(String accountNumber, Holder holder, BigDecimal balance, LocalDate openingDate) {
        this.accountNumber = accountNumber;
        this.holder = holder;
        this.balance = balance;
        this.openingDate = openingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.balance = balance;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", holder=" + holder +
                ", balance=" + balance +
                ", openingDate=" + openingDate +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/banca/domain/Holder.java ===
package com.pragma.banca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "holders")
public class Holder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Column(name = "identification_number", unique = true, nullable = false)
    @NotBlank(message = "El número de identificación no puede estar vacío")
    private String identificationNumber;

    public Holder() {
    }

    public Holder(String name, String identificationNumber) {
        this.name = name;
        this.identificationNumber = identificationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de identificación no puede estar vacío");
        }
        this.identificationNumber = identificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holder holder = (Holder) o;
        return Objects.equals(id, holder.id) && Objects.equals(identificationNumber, holder.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationNumber);
    }

    @Override
    public String toString() {
        return "Holder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/banca/domain/dto/AccountRequest.java ===
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

// === ARCHIVO: src/main/java/com/pragma/banca/domain/dto/AccountResponse.java ===
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

// === ARCHIVO: src/main/java/com/pragma/banca/domain/exception/AccountNotFoundException.java ===
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

// === ARCHIVO: src/main/java/com/pragma/banca/domain/exception/InvalidAccountException.java ===
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

// === ARCHIVO: src/main/java/com/pragma/banca/infrastructure/repository/AccountRepository.java ===
package com.pragma.banca.infrastructure.repository;

import com.pragma.banca.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByHolderId(Long holderId);
    List<Account> findByBalanceGreaterThanEqual(BigDecimal minimumBalance);
    void deleteByAccountNumber(String accountNumber);
}

// === ARCHIVO: src/main/java/com/pragma/banca/infrastructure/controller/AccountController.java ===
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

// === ARCHIVO: src/main/java/com/pragma/banca/application/AccountService.java ===
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

// === ARCHIVO: src/test/java/com/pragma/banca/application/AccountServiceTest.java ===
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

// === ARCHIVO: src/test/java/com/pragma/banca/infrastructure/controller/AccountControllerTest.java ===
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
```
