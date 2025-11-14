# 📚 Documentación Técnica - Sistema de Facturació

## 📖 Documentos Disponibles

### 1. DICCIONARIO_CODIGO.md
**Referencia completa del código fuente**

Contiene:
- Descripción detallada de cada clase
- Todos los métodos y sus parámetros
- Flujos de datos completos
- Ejemplos de uso
- Guías de modificación

**Cuándo consultar:**
- Necesitas entender cómo funciona una clase
- Quieres modificar o añadir funcionalidad
- Buscas la ubicación de un método específico

### 2. UI_v1.4_COMPLETO.md
**Documentación exhaustiva de la interfaz gráfica**

Contiene:
- Descripción de todos los componentes UI
- Todos los paneles y sus métodos
- Estilos y colores utilizados
- Ejemplos de código para cada componente
- Guía para añadir nuevos paneles

**Cuándo consultar:**
- Necesitas modificar la interfaz gráfica
- Quieres añadir un nuevo panel
- Buscas cambiar colores o estilos
- Necesitas entender el flujo de navegación

## 🎯 Guía Rápida de Consulta

### ¿Quieres saber cómo funciona...?

**...la validación de datos?**
→ `DICCIONARIO_CODIGO.md` → Sección "Validaciones"

**...la gestión de clientes?**
→ `DICCIONARIO_CODIGO.md` → Sección "ClientService" y "ClientRepository"

**...la interfaz de clientes?**
→ `UI_v1.4_COMPLETO.md` → Sección "ClientsPanel"

**...el guardado de datos?**
→ `DICCIONARIO_CODIGO.md` → Sección "Repositorios"

**...los colores y estilos?**
→ `UI_v1.4_COMPLETO.md` → Sección "AppleStyler"

**...el flujo completo de una factura?**
→ `DICCIONARIO_CODIGO.md` → Sección "Flujo completo: Crear una factura"

## 🔍 Búsqueda Rápida

### Por Funcionalidad

| Funcionalidad | Documento | Sección |
|--------------|-----------|---------|
| Validar DNI | DICCIONARIO_CODIGO | Validation.java |
| Añadir cliente | DICCIONARIO_CODIGO | ClientService.addClient() |
| Eliminar artículo | DICCIONARIO_CODIGO | ArticleService.deleteArticle() |
| Calcular totales | DICCIONARIO_CODIGO | InvoiceService.calculateTotals() |
| Cambiar colores | UI_v1.4_COMPLETO | AppleStyler |
| Añadir panel | UI_v1.4_COMPLETO | Añadir un Nuevo Panel |

### Por Archivo

| Archivo | Documento | Descripción |
|---------|-----------|-------------|
| Main.java | DICCIONARIO_CODIGO | Punto de entrada |
| GuiUI.java | UI_v1.4_COMPLETO | Orquestador de interfaz |
| ClientsPanel.java | UI_v1.4_COMPLETO | Panel de clientes |
| AppleStyler.java | UI_v1.4_COMPLETO | Estilos visuales |
| ClientService.java | DICCIONARIO_CODIGO | Lógica de clientes |
| Validation.java | DICCIONARIO_CODIGO | Validaciones |

## 📝 Estructura de la Documentación

```
docs/
├── README.md                  ← Este archivo (guía de documentación)
├── DICCIONARIO_CODIGO.md     ← Referencia completa del código
└── UI_v1.4_COMPLETO.md       ← Documentación de la interfaz
```

## 💡 Consejos de Uso

### Para Desarrolladores Nuevos
1. Empieza leyendo el README.md principal del proyecto
2. Consulta DICCIONARIO_CODIGO.md para entender la arquitectura
3. Revisa UI_v1.4_COMPLETO.md si trabajarás con la interfaz

### Para Modificar el Código
1. Busca la funcionalidad en el diccionario
2. Lee la documentación del método/clase
3. Consulta los ejemplos de código
4. Haz los cambios siguiendo las guías

### Para Añadir Funcionalidad
1. Identifica dónde debe ir (model/service/ui)
2. Consulta ejemplos similares en la documentación
3. Sigue la arquitectura MVC existente
4. Actualiza la documentación si es necesario

## 🔄 Historial de Versiones

### Versión 1.4 (Actual)
- Interfaz refactorizada en múltiples archivos
- Diseño minimalista estilo Apple
- CRUD completo de clientes y artículos
- Sistema de validaciones robusto
- Documentación completa actualizada

### Versiones Anteriores
- v1.3: Mejoras visuales
- v1.2: Funcionalidad de eliminación
- v1.1: Mejoras de seguridad CSV
- v1.0: Versión inicial

---

**Última actualización:** Enero 2025  
**Versión de documentación:** 1.4

