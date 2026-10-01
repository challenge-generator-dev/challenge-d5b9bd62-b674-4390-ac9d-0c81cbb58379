# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de una API REST para gestión de cuentas bancarias**.

| | |
|---|---|
| Tema | API REST en el dominio de banca |
| Nivel | junior-l1 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | capas estándar |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.4.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.4.0
- org.springframework.boot:spring-boot-starter-validation 3.4.0
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.6.0
- org.projectlombok:lombok 1.18.34
- com.h2database:h2 2.3.230
- org.springframework.boot:spring-boot-starter-test 3.4.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición y validación de entidades**: Entidades de cuenta bancaria y titular validadas y documentadas.
- **Fase 2 — Implementación de la API REST**: API REST implementada y documentada con OpenAPI.
- **Fase 3 — Pruebas y optimización**: API REST con pruebas unitarias y de integración y optimizada.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/banca/infrastructure/controller/AccountControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (14)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/banca/domain/dto/AccountResponse.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/banca/infrastructure/controller/AccountController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/banca/infrastructure/controller/AccountControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/banca/domain/dto/AccountResponse.java` — `Holder.toDomain`
      Se invoca `toDomain` sobre `Holder`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.findAll`
      Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.findById`
      Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.save`
      Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.existsById`
      Se invoca `existsById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/banca/application/AccountService.java` — `AccountRepository.deleteById`
      Se invoca `deleteById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.save`
      Se invoca `save` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.findAll`
      Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.findById`
      Se invoca `findById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountResponse.accountNumber`
      Se invoca `accountNumber` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/banca/application/AccountServiceTest.java` — `AccountRepository.existsById`
      Se invoca `existsById` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (14)

- `pom.xml`
- `src/main/java/com/pragma/banca/BancaApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/banca/domain/Account.java`
- `src/main/java/com/pragma/banca/domain/Holder.java`
- `src/main/java/com/pragma/banca/domain/dto/AccountRequest.java`
- `src/main/java/com/pragma/banca/domain/dto/AccountResponse.java`
- `src/main/java/com/pragma/banca/domain/exception/AccountNotFoundException.java`
- `src/main/java/com/pragma/banca/domain/exception/InvalidAccountException.java`
- `src/main/java/com/pragma/banca/infrastructure/repository/AccountRepository.java`
- `src/main/java/com/pragma/banca/infrastructure/controller/AccountController.java`
- `src/main/java/com/pragma/banca/application/AccountService.java`
- `src/test/java/com/pragma/banca/application/AccountServiceTest.java`
- `src/test/java/com/pragma/banca/infrastructure/controller/AccountControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/banca`
- `src/main/java/com/pragma/banca/domain`
- `src/main/java/com/pragma/banca/domain/dto`
- `src/main/java/com/pragma/banca/domain/exception`
- `src/main/java/com/pragma/banca/infrastructure`
- `src/main/java/com/pragma/banca/infrastructure/controller`
- `src/main/java/com/pragma/banca/infrastructure/repository`
- `src/main/java/com/pragma/banca/application`
- `src/main/resources`
- `src/test/java/com/pragma/banca`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Crear una API REST con Spring Boot, JPA, validación de entidades y documentación OpenAPI

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
