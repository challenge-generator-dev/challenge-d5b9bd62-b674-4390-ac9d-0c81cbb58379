# Desarrollo de una API REST para gestión de cuentas bancarias

El sistema debe permitir la gestión de cuentas bancarias, incluyendo creación, consulta, actualización y eliminación de cuentas. Los datos de las cuentas incluyen número de cuenta, titular, saldo y fecha de apertura. El sistema debe validar que el número de cuenta sea único y que el saldo no sea negativo. La API debe estar documentada con OpenAPI.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | API REST en el dominio de banca |
| **Nivel** | junior-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición y validación de entidades

**Objetivo:** Definir y validar las entidades de cuenta bancaria y titular.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Crear la entidad de cuenta bancaria con los atributos requeridos.
- Crear la entidad de titular con los atributos requeridos.
- Validar que el número de cuenta sea único y que el saldo no sea negativo.

**Entregable:** Entidades de cuenta bancaria y titular validadas y documentadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo validar la unicidad del número de cuenta y el saldo positivo.
- Piensa en cómo relacionar la entidad de cuenta bancaria con la entidad de titular.

</details>

### Fase 2: Implementación de la API REST

**Objetivo:** Implementar los endpoints de la API REST para gestión de cuentas bancarias.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Crear los endpoints para creación, consulta, actualización y eliminación de cuentas bancarias.
- Asegurar que los endpoints validen los datos de entrada según las reglas definidas en la fase anterior.
- Documentar la API con OpenAPI.

**Entregable:** API REST implementada y documentada con OpenAPI.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo manejar los errores de validación en los endpoints.
- Piensa en cómo documentar la API de manera clara y concisa.

</details>

### Fase 3: Pruebas y optimización

**Objetivo:** Realizar pruebas unitarias y de integración y optimizar la API.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Escribir pruebas unitarias para las entidades y los endpoints.
- Realizar pruebas de integración para validar el flujo completo de la API.
- Optimizar la API para mejorar el rendimiento y la escalabilidad.

**Entregable:** API REST con pruebas unitarias y de integración y optimizada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo escribir pruebas efectivas que cubran los casos de uso más comunes.
- Piensa en cómo optimizar la API para manejar un mayor volumen de solicitudes.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una entidad en el contexto de una API REST?
- **paraQueSirve**: ¿Para qué sirve validar los datos de entrada en los endpoints de una API REST?
- **comoSeUsa**: ¿Cómo se usa OpenAPI para documentar una API REST?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar una API REST?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización de una API REST?

## Criterios de Evaluacion

- Definición y validación de entidades de cuenta bancaria y titular.
- Implementación de endpoints de la API REST para gestión de cuentas bancarias.
- Documentación de la API con OpenAPI.
- Realización de pruebas unitarias y de integración.
- Optimización de la API para mejorar el rendimiento y la escalabilidad.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
