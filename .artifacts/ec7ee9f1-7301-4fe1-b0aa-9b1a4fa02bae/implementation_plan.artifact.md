# Plan to Fix Errors and Warnings in `ActividadControladora.java`

The goal is to resolve the compilation errors related to `posicionAngular` and a warning about an unused increment in `ActividadControladora.java`.

## Proposed Changes

### [Component: Controlador]

#### [MODIFY] [ActividadControladora.java](file:///C:/Users/PRODESK/OneDrive/Documentos/UNAL/SIMULACIONES/MiVigesimaSegundaAPP/app/src/main/java/com/curso_simulaciones/mivigesimasegundaapp/controlador/ActividadControladora.java)

1.  **Fix Compilation Errors:**
    *   The code attempts to call `posicionAngular(float)` on `Polea` objects, but `posicionAngular` is a protected field in `ObjetoLaboratorio`, not a method.
    *   The `CuerpoRigido` class (parent of `Polea`) provides a public `mover(float posicionAngular)` method that sets the angular position.
    *   **Action:** Replace `posicionAngular(...)` with `mover(...)` for `polea_azul_1` and `polea_azul_2`.

2.  **Fix Warning:**
    *   On line 229, `objetos[indice++] = marca_m3;` increments `indice`, but the new value is never used.
    *   **Action:** Change `indice++` to `indice` or simply leave it if future expansion is expected. However, to satisfy the linter/analyzer, I will remove the unused increment.

## Verification Plan

### Automated Tests
- Run `gradle_build("app:assembleDebug")` to verify that the compilation errors are resolved and no new warnings are introduced.

### Manual Verification
- None required for these syntax/access fixes.
