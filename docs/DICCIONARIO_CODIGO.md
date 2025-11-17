# 📚 DICCIONARIO COMPLETO DEL CÓDIGO - PROGRAMA DE FACTURACIÓ

## ✨ NUEVA FUNCIONALIDAD (v1.6 - 2025-11-17)

### 📄 VISUALIZACIÓN MEJORADA DE FACTURAS

Se ha implementado una funcionalidad completa para visualizar facturas de forma elegante y profesional **en el mismo panel**, sin ventanas emergentes.

#### **3 Formas de Visualizar una Factura:**

1. **🔍 Búsqueda por ID**
   - Campo de búsqueda en la parte superior
   - Introduce el ID y pulsa "Buscar"
   - Los detalles se muestran debajo de la tabla

2. **👆 Botón "Veure Factura"**
   - Nuevo botón en el header
   - Selecciona una factura y haz clic
   - Los detalles se muestran debajo de la tabla

3. **🖱️ Doble clic en la tabla**
   - Haz doble clic sobre cualquier factura
   - Los detalles se muestran automáticamente debajo de la tabla

#### **Características del Panel de Visualización:**

✅ **Diseño profesional** estilo Apple  
✅ **Información completa del cliente** (DNI y nombre)  
✅ **Tabla de líneas** con cantidad, artículo, precio y total  
✅ **Panel de totales** destacado:
  - Base imponible
  - IVA calculado
  - Total en grande y azul
✅ **Se muestra en el mismo panel** - sin ventanas emergentes  
✅ **Botón "Tancar"** para ocultar  

#### **Archivos Modificados:**
- `InvoicesPanel.java` - 6 nuevos métodos:
  - `viewSelectedInvoice()` - Visualiza factura seleccionada
  - `showInvoiceDetails(String id)` - Muestra detalles en panel
  - `createViewPanel()` - Crea el panel de visualización
  - `hideViewPanel()` - Oculta el panel de visualización
  - `createBoldLabel()` - Helper para etiquetas en negrita
  - `createValueLabel()` - Helper para valores

#### **Documentación Completa:**
📖 Ver `docs/VISUALIZACION_FACTURAS_v1.6.md` para más detalles

---

## 🔥 ÚLTIMAS CORRECCIONES (v1.5 - 2025-01-17)

### ✅ FALLOS CORREGIDOS

#### 1. **Espacio vacío en el sidebar - SOLUCIONADO ✓**
- **Archivo modificado:** `GuiUI.java`
- **Cambio:** Añadidos gaps de `0` en los `BorderLayout`
  ```java
  frame.setLayout(new BorderLayout(0, 0)); // Antes: new BorderLayout()
  JPanel mainContainer = new JPanel(new BorderLayout(0, 0)); // Antes: new BorderLayout()
  ```
- **Resultado:** El sidebar ahora queda pegado al borde izquierdo sin espacios

#### 2. **Validación DNI mejorada - SOLUCIONADO ✓**
- **Archivo modificado:** `Validation.java`
- **Antes:** Solo validaba longitud de 9 caracteres
- **Ahora:** Valida formato español (8 dígitos + letra)
  ```java
  // Verifica que los primeros 8 caracteres sean dígitos
  // Verifica que el último carácter sea una letra
  ```

#### 3. **Validación teléfono mejorada - SOLUCIONADO ✓**
- **Archivo modificado:** `Validation.java`
- **Antes:** Solo validaba 9 dígitos
- **Ahora:** Valida que empiece por 6, 7, 8 o 9 (teléfonos españoles válidos)
  ```java
  char first = phone.charAt(0);
  return first == '6' || first == '7' || first == '8' || first == '9';
  ```

#### 4. **Validación precio mejorada - SOLUCIONADO ✓**
- **Archivo modificado:** `Validation.java`
- **Antes:** Permitía cualquier número de decimales
- **Ahora:** Máximo 2 decimales
  ```java
  // Verificar máximo 2 decimales
  if (dotIndex >= 0) {
      String decimals = price.substring(dotIndex + 1);
      if (decimals.length() > 2) return false;
  }
  ```

#### 5. **Validación campos vacíos - SOLUCIONADO ✓**
- **Archivos modificados:** `Validation.java`, `ArticleService.java`, `ClientService.java`
- **Nueva función:** `Validation.notEmpty(String s)` 
- **Implementado en:**
  - `ArticleService.addArticle()`: Valida nombre y precio no vacíos
  - `ClientService.addClient()`: Valida todos los campos no vacíos

#### 6. **Método UPDATE implementado - SOLUCIONADO ✓**
- **Archivos modificados:**
  - `ArticleRepository.java` → Nuevo método `update(String oldName, Article newArticle)`
  - `ClientRepository.java` → Nuevo método `update(String dni, Client newClient)`
  - `ArticleService.java` → Nuevo método `updateArticle(String oldName, Article newArticle)`
  - `ClientService.java` → Nuevo método `updateClient(String dni, Client newClient)`
- **Funcionamiento:**
  1. Busca el registro existente por clave (nombre o DNI)
  2. Lo reemplaza con los nuevos datos
  3. Reescribe el archivo completo
  4. Aplica todas las validaciones antes de actualizar

#### 7. **Mejor manejo de errores - MEJORADO ✓**
- **Archivos modificados:** `ArticleRepository.java`, `ClientRepository.java`
- **Mejoras:**
  - Logging detallado con número de línea cuando hay datos corruptos
  - Stack trace completo en caso de excepciones
  - Mensajes de error en catalán
  ```java
  System.err.println("Avís: Línia " + lineNumber + " d'articles.txt corrupta o invàlida: " + line);
  e.printStackTrace();
  ```

### 📊 RESUMEN DE CAMBIOS v1.5

| Categoría | Cambio | Estado |
|-----------|--------|--------|
| **UX/UI** | Espacio vacío sidebar eliminado | ✅ |
| **Validaciones** | DNI formato español (8 dígitos + letra) | ✅ |
| **Validaciones** | Teléfono español (comienza 6/7/8/9) | ✅ |
| **Validaciones** | Precio máximo 2 decimales | ✅ |
| **Validaciones** | Campos vacíos no permitidos | ✅ |
| **Funcionalidad** | Método UPDATE artículos | ✅ |
| **Funcionalidad** | Método UPDATE clientes | ✅ |
| **Errores** | Logging mejorado con línea y stack trace | ✅ |

### ⚠️ FALLOS PENDIENTES (No críticos)

#### Moderados:
- **Archivo theme.txt sin usar:** Existe pero no implementa cambio de tema
- **Sin validación facturas vacías:** Se pueden crear facturas sin líneas

#### Menores:
- **Sin sistema de backup:** No hay respaldo automático de datos
- **Warnings de compilación:** StandardCharsets.UTF_8, empty catch blocks (no afectan funcionalidad)

---

## 📁 ÍNDICE RÁPIDO
1. [Estructura del Proyecto](#estructura-del-proyecto)
2. [Archivos de Datos](#archivos-de-datos)
3. [Modelos (Model)](#modelos-model)
4. [Repositorios (Repository)](#repositorios-repository)
5. [Servicios (Service)](#servicios-service)
6. [Interfaz de Usuario (UI)](#interfaz-de-usuario-ui)
7. [Validaciones (Util)](#validaciones-util)
8. [Archivo Principal (Main)](#archivo-principal-main)
9. [Guía de Modificaciones](#guía-de-modificaciones)
10. [Mejoras Implementadas (v1.1)](#mejoras-implementadas-v11)
11. [🆕 Novedades Versión 1.2](#-novedades-versión-12-2025-01-03)
12. [🎨 Mejoras Visuales (v1.3)](#-mejoras-visuales-v13-2025-01-03)
13. [🍎 Rediseño Apple (v1.4)](#-rediseño-apple-v14-2025-01-03)

---

## ESTRUCTURA DEL PROYECTO

```
ProgramaFacturacio/
├── data/                          # Carpeta con archivos de datos
│   ├── articles.txt              # Base de datos de artículos
│   ├── clients.txt               # Base de datos de clientes
│   ├── config.txt                # Configuración (IVA)
│   ├── factures.txt              # Base de datos de facturas
│   └── linies_factura.txt        # Base de datos de líneas de factura
├── docs/                          # Documentación completa del proyecto
│   ├── DICCIONARIO_CODIGO.md     # Este documento
│   ├── REDISEÑO_APPLE_v1.4.md    # 🍎 Nuevo en v1.4
│   ├── MEJORAS_VISUALES_v1.3.md  # Mejoras v1.3
│   ├── NUEVAS_FUNCIONALIDADES.md # Novedades v1.2
│   └── ... (otros documentos)
├── src/                          # Código fuente
│   ├── Main.java                 # Punto de entrada de la aplicación
│   ├── TestRunner.java           # Tests automatizados
│   ├── model/                    # Clases que representan datos
│   │   ├── Article.java
│   │   ├── Client.java
│   │   ├── Invoice.java
│   │   └── InvoiceLine.java
│   ├── repository/               # Clases que leen/escriben archivos
│   │   ├── ArticleRepository.java
│   │   ├── ClientRepository.java
│   │   ├── InvoiceRepository.java
│   │   └── InvoiceLineRepository.java
│   ├── service/                  # Lógica de negocio y validaciones
│   │   ├── ArticleService.java
│   │   ├── ClientService.java
│   │   ├── ConfigService.java
│   │   └── InvoiceService.java
│   ├── ui/                       # 🍎 Interfaz de usuario (REFACTORIZADA v1.4)
│   │   ├── ConsoleUI.java       # Interfaz de texto
│   │   ├── GuiUI.java           # Interfaz gráfica principal (refactorizada)
│   │   ├── components/          # 🍎 Componentes reutilizables (NUEVO v1.4)
│   │   │   ├── AppleStyler.java  # Utilidades de estilo Apple
│   │   │   └── AppleSidebar.java # Barra lateral de navegación
│   │   └── panels/              # 🍎 Paneles individuales (NUEVO v1.4)
│   │       ├── WelcomePanel.java   # Pantalla de bienvenida
│   │       ├── ClientsPanel.java   # Gestión de clientes
│   │       ├── ArticlesPanel.java  # Gestión de artículos
│   │       ├── InvoicesPanel.java  # Gestión de facturas
│   │       └── ConfigPanel.java    # Configuración
│   └── util/                     # Utilidades
│       └── Validation.java      # Funciones de validación
└── README.md                     # Documentación del proyecto
```

**🍎 CAMBIOS EN v1.4:**
- ✅ **Refactorización UI:** Código modular en múltiples archivos
- ✅ **Componentes:** Carpeta `ui/components/` con utilidades reutilizables
- ✅ **Paneles:** Carpeta `ui/panels/` con cada panel en su archivo
- ✅ **Arquitectura:** Separación de responsabilidades y código más limpio

---

## ARCHIVOS DE DATOS

### 📄 **articles.txt** - Base de datos de artículos
**Ubicación:** `data/articles.txt`

**Formato actual:** `nombre;precio`

**Ejemplo:**
```
Bolígraf blau;0.45
Llibreta A5;2.50
```

**Campos:**
- `nombre` (40 caracteres max): Nombre del artículo
- `precio` (6 caracteres max): Precio del artículo (formato decimal con punto)

**¿Dónde se modifica?**
- **Lectura:** `ArticleRepository.findAll()` y `ArticleRepository.findByName()`
- **Escritura:** `ArticleRepository.save()`
- **Conversión:** `Article.toCSV()` y `Article.fromCSV()`

---

### 📄 **clients.txt** - Base de datos de clientes
**Ubicación:** `data/clients.txt`

**Formato actual:** `dni;nombre;dirección;ciudad;cp;provincia;teléfono`

**Ejemplo:**
```
12345678Z;Maria Serra;C/ Major 12;Palma;07001;Illes Balears;612345678
```

**Campos:**
- `dni` (9 caracteres): DNI del cliente (único)
- `nombre` (40 caracteres max): Nombre del cliente
- `dirección` (40 caracteres max): Dirección completa
- `ciudad` (20 caracteres max): Población
- `cp` (5 dígitos): Código postal
- `provincia` (20 caracteres max): Provincia
- `teléfono` (9 dígitos): Número de teléfono

**¿Dónde se modifica?**
- **Lectura:** `ClientRepository.findAll()` y `ClientRepository.findByDni()`
- **Escritura:** `ClientRepository.save()`
- **Conversión:** `Client.toCSV()` y `Client.fromCSV()`

---

### 📄 **factures.txt** - Base de datos de facturas
**Ubicación:** `data/factures.txt`

**Formato actual:** `id;fecha;dni_cliente;iva`

**Ejemplo:**
```
F00001;2025-10-13;12345678Z;21
F00002;2025-10-13;45188608W;21
```

**Campos:**
- `id` (formato F00001): ID único de la factura (generado automáticamente)
- `fecha` (YYYY-MM-DD): Fecha de creación
- `dni_cliente`: DNI del cliente asociado
- `iva`: Porcentaje de IVA aplicado (ej: 21)

**¿Dónde se modifica?**
- **Lectura:** `InvoiceRepository.findAll()` y `InvoiceRepository.findById()`
- **Escritura:** `InvoiceRepository.save()`
- **Conversión:** `Invoice.toCSV()` y `Invoice.fromCSV()`
- **Generación de ID:** `InvoiceService.nextId()`

---

### 📄 **linies_factura.txt** - Base de datos de líneas de factura
**Ubicación:** `data/linies_factura.txt`

**Formato actual:** `id_factura;cantidad;nombre_articulo;precio`

**Ejemplo:**
```
F00001;3;Bolígraf blau;0.45
F00001;2;Llibreta A5;2.50
```

**Campos:**
- `id_factura`: ID de la factura a la que pertenece
- `cantidad` (1-9999): Cantidad de artículos
- `nombre_articulo` (40 caracteres max): Nombre del artículo
- `precio` (6 caracteres max): Precio unitario

**¿Dónde se modifica?**
- **Lectura:** `InvoiceLineRepository.findByInvoiceId()`
- **Escritura:** `InvoiceLineRepository.saveLine()` y `saveLines()`
- **Conversión:** `InvoiceLine.toCSV()` y `InvoiceLine.fromCSV()`

---

### 📄 **config.txt** - Configuración del sistema
**Ubicación:** `data/config.txt`

**Formato actual:** `IVA=21`

**Contenido:**
```
IVA=21
```

**¿Dónde se modifica?**
- **Lectura:** `ConfigService.load()`
- **Escritura:** `ConfigService.setIva()`

---

## MODELOS (MODEL)

### 🏷️ **Article.java** - Representa un artículo
**Ubicación:** `src/model/Article.java`

**Propósito:** Almacena información de un artículo (producto) que se puede vender.

**Constantes:**
- `MAX_NAME_LENGTH = 40`: Longitud máxima del nombre
- `MAX_PRICE_LENGTH = 6`: Longitud máxima del precio (sin punto decimal)

**Atributos:**
- `String name`: Nombre del artículo (max 40 caracteres)
- `String price`: Precio como String (max 6 caracteres sin punto)

**Métodos principales:**
- `getName()`, `setName()`: Obtener/establecer nombre
  - ⚠️ **PROTECCIÓN:** Trunca automáticamente si supera 40 caracteres, convierte null en ""
- `getPrice()`, `setPrice()`: Obtener/establecer precio
  - ⚠️ **PROTECCIÓN:** Trunca si supera 6 caracteres, convierte null en ""
- `toCSV()`: Convierte el objeto a formato CSV para guardar
- `fromCSV(String line)`: Crea un objeto Article desde una línea CSV
  - ⚠️ **VALIDACIÓN:** Retorna null si la línea es null o vacía
- `escape()`, `unescape()`: Escapan caracteres especiales (`;`, `\`, `\n`, `\r`)
- `toString()`: Representación legible (ej: "Bolígraf blau -> 0.45")

**Mejoras de seguridad (v1.1):**
- ✅ Truncado automático de campos largos
- ✅ Conversión de null a cadena vacía
- ✅ Escape mejorado de múltiples caracteres especiales
- ✅ Constantes centralizadas para límites

**¿Cuándo modificar?**
- Si quieres cambiar las longitudes máximas → modifica las constantes
- Si quieres añadir más campos (ej: categoría, stock)
- Si quieres cambiar el formato del precio
- Si quieres cambiar la validación

---

### 👤 **Client.java** - Representa un cliente
**Ubicación:** `src/model/Client.java`

**Propósito:** Almacena toda la información de un cliente.

**Constantes:**
- `MAX_DNI_LENGTH = 9`
- `MAX_NAME_LENGTH = 40`
- `MAX_ADDRESS_LENGTH = 40`
- `MAX_CITY_LENGTH = 20`
- `MAX_POSTAL_CODE_LENGTH = 5`
- `MAX_PROVINCE_LENGTH = 20`
- `MAX_PHONE_LENGTH = 9`

**Atributos:**
- `String dni`: DNI del cliente (9 caracteres, único)
- `String name`: Nombre (max 40 caracteres)
- `String address`: Dirección (max 40 caracteres)
- `String city`: Ciudad (max 20 caracteres)
- `String postalCode`: Código postal (5 dígitos)
- `String province`: Provincia (max 20 caracteres)
- `String phone`: Teléfono (9 dígitos)

**Métodos principales:**
- Getters y setters para cada atributo
  - ⚠️ **PROTECCIÓN:** Todos los setters truncan automáticamente y convierten null en ""
- `truncate(String s, int maxLength)`: Método helper para truncar strings
- `toCSV()`: Convierte el objeto a formato CSV
- `fromCSV(String line)`: Crea un objeto Client desde CSV
  - ⚠️ **VALIDACIÓN:** Retorna null si la línea es null o vacía
- `escape()`, `unescape()`: Protegen caracteres especiales (`;`, `\`, `\n`, `\r`)
- `toString()`: Formato legible

**Mejoras de seguridad (v1.1):**
- ✅ Truncado automático en todos los setters
- ✅ Conversión de null a cadena vacía
- ✅ Escape mejorado de múltiples caracteres especiales
- ✅ Constantes centralizadas
- ✅ Método helper reutilizable `truncate()`

**¿Cuándo modificar?**
- Si quieres cambiar las longitudes máximas → modifica las constantes
- Si quieres añadir más campos (ej: email, NIF empresa)
- Si quieres cambiar las restricciones de longitud
- Si cambias el formato de archivo

---

### 🧾 **Invoice.java** - Representa una factura
**Ubicación:** `src/model/Invoice.java`

**Propósito:** Almacena la cabecera de una factura (sin las líneas de artículos).

**Constantes:**
- `MAX_ID_LENGTH = 10`
- `MAX_DATE_LENGTH = 10`
- `MAX_DNI_LENGTH = 9`
- `MAX_IVA_LENGTH = 3`

**Atributos:**
- `String id`: ID único de la factura (ej: F00001)
- `String date`: Fecha en formato YYYY-MM-DD
- `String clientDni`: DNI del cliente
- `String iva`: Porcentaje de IVA (ej: "21")

**Métodos principales:**
- Getters y setters para cada atributo
  - ⚠️ **PROTECCIÓN:** Todos los setters truncan automáticamente y convierten null en ""
- `truncate(String s, int maxLength)`: Método helper para truncar strings
- `toCSV()`: Convierte a formato CSV
- `fromCSV(String line)`: Crea objeto desde CSV
  - ⚠️ **VALIDACIÓN:** Retorna null si la línea es null o vacía
- `escape()`, `unescape()`: Protegen caracteres especiales (`;`, `\`, `\n`, `\r`)
- `toString()`: Representación legible

**Mejoras de seguridad (v1.1):**
- ✅ Truncado automático en todos los setters
- ✅ Conversión de null a cadena vacía
- ✅ Escape mejorado de múltiples caracteres especiales
- ✅ Constantes centralizadas

**¿Cuándo modificar?**
- Si quieres cambiar las longitudes máximas → modifica las constantes
- Si quieres añadir más campos (ej: método de pago, notas)
- Si cambias el formato de ID
- Si cambias el formato de fecha

---

### 📋 **InvoiceLine.java** - Representa una línea de factura
**Ubicación:** `src/model/InvoiceLine.java`

**Propósito:** Representa una línea individual dentro de una factura (un artículo con su cantidad).

**Constantes:**
- `MAX_INVOICE_ID_LENGTH = 10`
- `MAX_NAME_LENGTH = 40`
- `MAX_PRICE_LENGTH = 6`

**Atributos:**
- `String invoiceId`: ID de la factura a la que pertenece
- `int quantity`: Cantidad de artículos (1-9999)
- `String name`: Nombre del artículo (max 40 caracteres)
- `String price`: Precio unitario como String (max 6 caracteres)

**Métodos principales:**
- Getters y setters para cada atributo
  - ⚠️ **PROTECCIÓN:** Todos los setters truncan automáticamente y convierten null en ""
  - ⚠️ **VALIDACIÓN CANTIDAD:** setQuantity limita automáticamente entre 1-9999
- `getArticleName()`, `setArticleName()`: Alias para name (compatibilidad)
- `truncate(String s, int maxLength)`: Método helper para truncar strings
- `toCSV()`: Convierte a formato CSV
- `fromCSV(String line)`: Crea objeto desde CSV
  - ⚠️ **VALIDACIÓN:** Retorna null si la línea es null, vacía o tiene formato inválido
- `escape()`, `unescape()`: Protegen caracteres especiales (`;`, `\`, `\n`, `\r`)
- `toString()`: Formato legible (ej: "3 x Bolígraf blau @ 0.45")

**Mejoras de seguridad (v1.1):**
- ✅ Truncado automático en todos los setters de String
- ✅ Validación automática de cantidad (1-9999)
- ✅ Conversión de null a cadena vacía
- ✅ Escape mejorado de múltiples caracteres especiales
- ✅ Constantes centralizadas

**¿Cuándo modificar?**
- Si quieres cambiar las longitudes máximas → modifica las constantes
- Si quieres cambiar el rango de cantidad → modifica setQuantity()
- Si quieres añadir descuentos por línea
- Si quieres añadir impuestos especiales
- Si cambias el formato de precio

---

## REPOSITORIOS (REPOSITORY)

Los repositorios son responsables de **leer y escribir archivos**. Son la capa de acceso a datos.

### 💾 **ArticleRepository.java**
**Ubicación:** `src/repository/ArticleRepository.java`

**Propósito:** Gestiona la persistencia de artículos en `articles.txt`.

**Atributos:**
- `File file`: Referencia al archivo articles.txt

**Métodos:**
- **`ArticleRepository(String dataDir)`**: Constructor que inicializa el archivo
- **`findAll()`**: Lee todos los artículos del archivo y los devuelve en una lista
- **`findByName(String name)`**: Busca un artículo por su nombre exacto
- **`save(Article article)`**: Añade un artículo nuevo al archivo (devuelve false si ya existe)
- **✨ NUEVO v1.2: `delete(String name)`**: Elimina un artículo por nombre

**Flujo de lectura:**
1. Abre el archivo con BufferedReader
2. Lee línea por línea
3. Usa `Article.fromCSV()` para convertir cada línea en objeto
4. Añade a la lista y devuelve

**Flujo de escritura:**
1. Verifica que el artículo no exista
2. Abre el archivo en modo append (añadir al final)
3. Usa `article.toCSV()` para convertir a texto
4. Escribe la línea

**✨ NUEVO v1.2 - Flujo de eliminación:**
1. Lee todos los artículos con `findAll()`
2. Filtra el artículo que se quiere eliminar (comparando por nombre)
3. Guarda los demás artículos en una lista temporal
4. Reescribe el archivo completo SIN el artículo eliminado
5. Retorna true si se eliminó, false si no existía

**IMPORTANTE:** La eliminación reescribe todo el archivo, puede ser lento con muchos artículos (pero es seguro)

**¿Cuándo modificar?**
- Si cambias el nombre del archivo
- Si quieres añadir funcionalidad de actualización (UPDATE)
- ~~Si quieres añadir funcionalidad de eliminación (DELETE)~~ ✅ Ya implementado
- Si cambias el formato del archivo

---

### 💾 **ClientRepository.java**
**Ubicación:** `src/repository/ClientRepository.java`

**Propósito:** Gestiona la persistencia de clientes en `clients.txt`.

**Estructura similar a ArticleRepository:**
- **`findAll()`**: Lee todos los clientes
- **`findByDni(String dni)`**: Busca cliente por DNI (clave única)
- **`save(Client client)`**: Guarda nuevo cliente (previene DNI duplicado)
- **✨ NUEVO v1.2: `delete(String dni)`**: Elimina un cliente por DNI

**Funcionamiento de delete():**
1. Lee todos los clientes
2. Filtra el que tiene el DNI especificado
3. Reescribe el archivo sin ese cliente
4. Retorna true si existía y se eliminó, false si no existía

**⚠️ IMPORTANTE:** Si eliminas un cliente que tiene facturas asociadas, esas facturas quedarán con un DNI que no existe (facturas huérfanas). Considera añadir una validación para evitarlo.

**¿Cuándo modificar?**
- Si quieres permitir actualizar datos de clientes
- ~~Si quieres eliminar clientes~~ ✅ Ya implementado
- Si cambias el formato del archivo
- Si quieres añadir validación antes de eliminar (verificar facturas)

---

### 💾 **InvoiceRepository.java**
**Ubicación:** `src/repository/InvoiceRepository.java`

**Propósito:** Gestiona la persistencia de facturas en `factures.txt`.

**Métodos:**
- **`findAll()`**: Lee todas las facturas
- **`findById(String id)`**: Busca factura por ID
- **`save(Invoice invoice)`**: Guarda nueva factura

**¿Cuándo modificar?**
- Si quieres permitir modificar facturas
- Si quieres añadir búsqueda por fecha o cliente
- Si cambias el formato

---

### 💾 **InvoiceLineRepository.java**
**Ubicación:** `src/repository/InvoiceLineRepository.java`

**Propósito:** Gestiona las líneas de factura en `linies_factura.txt`.

**Métodos:**
- **`findByInvoiceId(String invoiceId)`**: Busca todas las líneas de una factura específica
- **`saveLine(InvoiceLine line)`**: Guarda una línea individual
- **`saveLines(List<InvoiceLine> lines)`**: Guarda múltiples líneas

**¿Cuándo modificar?**
- Si quieres actualizar o eliminar líneas
- Si cambias el formato

---

## SERVICIOS (SERVICE)

Los servicios contienen la **lógica de negocio** y **validaciones**. Son la capa intermedia entre UI y Repository.

### 🔧 **ArticleService.java**
**Ubicación:** `src/repository/ArticleService.java`

**Propósito:** Gestiona la lógica de negocio de artículos con validaciones.

**Atributos:**
- `ArticleRepository repo`: Repositorio para acceso a datos

**Métodos:**
- **`listAll()`**: Devuelve todos los artículos
- **`find(String name)`**: Busca un artículo por nombre
- **`addArticle(Article a)`**: Añade un artículo después de validarlo
- **`deleteArticle(String name)`**: ✨ **NUEVO v1.2** - Elimina un artículo por nombre

**Validaciones en addArticle():**
1. Nombre máximo 40 caracteres
2. **✨ NUEVO v1.2:** No permite el carácter `;` (punto y coma) en el nombre
3. Precio válido (formato decimal, max 6 caracteres)
4. **✨ NUEVO v1.2:** No permite el carácter `;` en el precio

**¿Por qué se prohíbe el `;`?**
- El archivo usa `;` como separador CSV
- Permitirlo causaría problemas al leer/escribir
- Mensaje claro al usuario: "El nom no pot contenir el caràcter ';'"

**¿Cuándo modificar?**
- Si quieres añadir más validaciones
- Si quieres añadir funcionalidad de actualización
- Si quieres añadir búsqueda avanzada
- Si quieres permitir más caracteres especiales (no recomendado)

---

### 🔧 **ClientService.java**
**Ubicación:** `src/service/ClientService.java`

**Propósito:** Gestiona la lógica de negocio de clientes con validaciones exhaustivas.

**Métodos:**
- **`listAll()`**: Devuelve todos los clientes
- **`find(String dni)`**: Busca cliente por DNI
- **`addClient(Client c)`**: Añade cliente tras validación
- **`deleteClient(String dni)`**: ✨ **NUEVO v1.2** - Elimina un cliente por DNI

**Validaciones en addClient():**
1. DNI debe tener exactamente 9 caracteres
2. **✨ NUEVO v1.2:** DNI no puede contener `;`
3. Nombre máximo 40 caracteres
4. **✨ NUEVO v1.2:** Nombre no puede contener `;`
5. Dirección máximo 40 caracteres
6. **✨ NUEVO v1.2:** Dirección no puede contener `;`
7. Ciudad máximo 20 caracteres
8. **✨ NUEVO v1.2:** Ciudad no puede contener `;`
9. CP exactamente 5 dígitos
10. Provincia máximo 20 caracteres
11. **✨ NUEVO v1.2:** Provincia no puede contener `;`
12. Teléfono exactamente 9 dígitos

**¿Por qué se prohíbe el `;` en todos los campos?**
- Protege la integridad del archivo CSV
- Evita confusión al usuario
- Mensajes claros por cada campo

**¿Cuándo modificar?**
- Si cambias las reglas de validación del DNI
- Si quieres permitir actualizar clientes
- Si quieres añadir validaciones adicionales (ej: formato email)
- **CUIDADO:** Si eliminas un cliente con facturas asociadas, quedarán huérfanas

---

### 🔧 **ConfigService.java**
**Ubicación:** `src/service/ConfigService.java`

**Propósito:** Gestiona la configuración del sistema (actualmente solo el IVA).

**Atributos:**
- `File file`: Referencia a config.txt
- `String iva`: Valor actual del IVA (default: "21")

**Métodos:**
- **`ConfigService(String dataDir)`**: Constructor, carga configuración
- **`load()`**: Lee el archivo config.txt
- **`getIva()`**: Devuelve el porcentaje de IVA actual
- **`setIva(String newIva)`**: Cambia el IVA (valida que sea número) y guarda

**¿Cuándo modificar?**
- Si quieres añadir más opciones de configuración
- Si quieres múltiples tipos de IVA
- Si quieres guardar otras preferencias

---

### 🔧 **InvoiceService.java**
**Ubicación:** `src/service/InvoiceService.java`

**Propósito:** Gestiona la lógica compleja de creación de facturas y cálculos.

**Atributos:**
- `InvoiceRepository invoiceRepo`
- `InvoiceLineRepository lineRepo`
- `ClientRepository clientRepo`

**Métodos principales:**

**`listAll()`**: Devuelve todas las facturas

**`find(String id)`**: Busca factura por ID

**`findLines(String invoiceId)`**: Obtiene las líneas de una factura

**`nextId()`**: Genera el siguiente ID de factura
- Lee todas las facturas existentes
- Encuentra el número más alto (ej: F00005)
- Suma 1 y formatea (ej: F00006)

**`createInvoice(...)`**: Crea una factura completa
- **Parámetros:**
  - `date`: Fecha de la factura
  - `clientDni`: DNI del cliente
  - `lines`: Lista de líneas de factura
  - `ivaPercent`: Porcentaje de IVA
- **Validaciones:**
  1. El cliente debe existir
  2. Debe tener al menos 1 línea
  3. Máximo 10 líneas
  4. Cada línea: cantidad 1-9999
  5. Cada línea: nombre max 40 caracteres
  6. Cada línea: precio válido
- **Proceso:**
  1. Genera nuevo ID con `nextId()`
  2. Guarda la factura en factures.txt
  3. Guarda cada línea en linies_factura.txt
  4. Devuelve la factura creada

**`calculateTotals(lines, ivaPercent)`**: Calcula totales
- Suma base imponible (precio × cantidad de cada línea)
- Calcula IVA (base × porcentaje / 100)
- Calcula total (base + IVA)
- Redondea a 2 decimales
- Devuelve objeto `Totals` con los tres valores

**Clase interna Totals:**
- `BigDecimal base`: Base imponible
- `BigDecimal iva`: Importe del IVA
- `BigDecimal total`: Total a pagar

**¿Cuándo modificar?**
- Si quieres cambiar el límite de líneas por factura
- Si quieres añadir descuentos globales
- Si quieres cambiar el formato del ID
- Si quieres validaciones adicionales

---

## INTERFAZ DE USUARIO (UI)

**🍎 VERSIÓN 1.4 FINAL - REFACTORIZACIÓN COMPLETA**

En la versión 1.4 se ha **refactorizado completamente** la interfaz gráfica:
- **ANTES:** 1 archivo monolítico (`GuiUI.java` ~900 líneas)
- **AHORA:** Arquitectura modular con 7 archivos organizados

**Nueva estructura:**
```
ui/
├── GuiUI.java          (Orquestador ~150 líneas)
├── ConsoleUI.java      (Sin cambios)
├── components/         (Componentes reutilizables)
│   ├── AppleStyler.java
│   └── AppleSidebar.java
└── panels/             (Paneles individuales)
    ├── WelcomePanel.java
    ├── ClientsPanel.java
    ├── ArticlesPanel.java
    ├── InvoicesPanel.java
    └── ConfigPanel.java
```

**📚 DOCUMENTACIÓN COMPLETA:** Ver **`docs/UI_v1.4_COMPLETO.md`** para documentación exhaustiva de todos los componentes, paneles y métodos.

---

### 📋 RESUMEN EJECUTIVO DE COMPONENTES UI

#### 💻 **ConsoleUI.java** - Interfaz de consola
**Ubicación:** `src/ui/ConsoleUI.java`
**Estado:** Sin cambios en v1.4
**Propósito:** Interfaz de texto para terminal con menús interactivos

**Métodos principales:**
- `run()` - Bucle principal del menú
- `manageClients(Scanner)` - Gestión de clientes
- `manageArticles(Scanner)` - Gestión de artículos  
- `manageInvoices(Scanner)` - Gestión de facturas
- `manageConfig(Scanner)` - Configuración IVA

---

#### 🖼️ **GuiUI.java** - Orquestador principal (✨ REFACTORIZADO v1.4)
**Ubicación:** `src/ui/GuiUI.java`
**Líneas:** ~150 (antes ~900)

**Responsabilidad:** Coordinar componentes, gestionar navegación, actualizar estado

**Métodos clave:**
- `show()` - Inicializa ventana (1300x800px)
- `createSidebar()` - Construye barra lateral
- `createStatusBar()` - Barra de estado
- `showCard(String)` - Navegación entre paneles
- `updateStatus(String)` - Actualiza mensajes

---

### 🧩 COMPONENTES REUTILIZABLES (ui/components/)

#### 🎨 **AppleStyler.java** - Utilidades de estilo (✨ NUEVO v1.4)
**Ubicación:** `src/ui/components/AppleStyler.java`

**Propósito:** Centralizar todos los estilos visuales de la aplicación

**Colores principales:**
- `BLUE` (#007AFF) - Botones primarios
- `BLUE_HOVER` (#0064E6) - Hover azul
- `RED` (#FF3B30) - Botones eliminar
- `RED_HOVER` (#E62D23) - Hover rojo
- `BG_WHITE` (#FFFFFF) - Paneles/cards
- `BG_LIGHT` (#F8F8FA) - Fondo general
- `TEXT_BLACK` (#000000) - Texto principal
- `BORDER` (#E6E6EB) - Bordes sutiles

**Fuentes:**
- `FONT_TITLE` (Segoe UI Bold 24px) - Títulos
- `FONT_BODY` (Segoe UI Regular 14px) - Texto normal
- `FONT_BUTTON` (Segoe UI Bold 14px) - Botones

**Métodos de estilización:**
- `styleButtonPrimary(JButton)` - Botones azules principales
- `styleButtonSecondary(JButton)` - Botones grises secundarios
- `styleButtonDanger(JButton)` - Botones rojos de eliminar
- `styleTextField(JTextField)` - Campos de texto minimalistas
- `styleTable(JTable)` - Tablas sin cuadrícula, selección azul
- `createCard()` - Crea panel tipo tarjeta blanca con borde
- `applyTheme()` - Aplica tema global (se llama una vez al inicio)

**¿Cuándo modificar AppleStyler?**
- Cambiar colores globales → Edita las constantes de colores
- Añadir nuevo tipo de botón → Crea método `styleButtonXxx()`
- Cambiar fuentes → Modifica las constantes FONT_xxx
- Ajustar espaciados → Cambia los valores de padding

**📚 Documentación completa:** Ver `docs/UI_v1.4_COMPLETO.md` sección AppleStyler

---

#### 📊 **AppleSidebar.java** - Barra lateral de navegación (✨ NUEVO v1.4)
**Ubicación:** `src/ui/components/AppleSidebar.java`

**Propósito:** Componente reutilizable para navegación lateral

**Características:**
- Ancho fijo: 200px
- Fondo gris claro (#FAFAFC)
- Gestión automática de selección de botones

**Métodos:**
- `addLogo(String text)` - Añade logo/título en la parte superior
- `addButton(String text, ActionListener action)` - Añade botón de navegación
  - Gestión automática de selección visual
  - Efecto hover
  - Retorna el JButton creado
- `addSpace(int height)` - Añade espacio fijo
- `addFlexibleSpace()` - Añade espacio flexible (para empujar elementos al final)
- `selectButton(JButton)` - Selecciona botón programáticamente

**Ejemplo de uso:**
```java
AppleSidebar sidebar = new AppleSidebar();
sidebar.addLogo("Facturacio");
JButton btnHome = sidebar.addButton("Inici", e -> showCard("WELCOME"));
sidebar.addButton("Clients", e -> showCard("CLIENTS"));
sidebar.addFlexibleSpace();
sidebar.addButton("Sortir", e -> System.exit(0));
sidebar.selectButton(btnHome);
```

**📚 Documentación completa:** Ver `docs/UI_v1.4_COMPLETO.md` sección AppleSidebar

---

### 📄 PANELES INDIVIDUALES (ui/panels/)

#### 🏠 **WelcomePanel.java** - Pantalla de bienvenida (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/WelcomePanel.java`

**Propósito:** Pantalla inicial del sistema

**Componentes:**
- Icono "$" grande (80px azul)
- Título "Sistema de Facturació" (24px Bold)
- Subtítulo explicativo (16px)
- Versión "Versió 1.4" (12px)
- Botones de acceso rápido:
  - "Nou Client" (azul primario)
  - "Nova Factura" (gris secundario)

**Métodos:**
- `setOnNewClient(Runnable)` - Configura acción botón cliente
- `setOnNewInvoice(Runnable)` - Configura acción botón factura

---

#### 👥 **ClientsPanel.java** - Gestión de clientes (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ClientsPanel.java`

**Propósito:** Panel completo para gestionar clientes (CRUD completo)

**Constructor:**
```java
ClientsPanel(ClientService service, Consumer<String> statusUpdater)
```

**Componentes:**
- **Header:** Título + Botones (Nou, Eliminar, Actualitzar)
- **Tabla:** DNI, Nom, Adreça, CP, Població, Província, Telèfon
- **Formulario:** 7 campos (oculto por defecto, aparece al clic "Nou")

**Métodos principales:**
- `showForm()` - Muestra formulario
- `hideForm()` - Oculta formulario
- `saveClient()` - Valida y guarda cliente
- `deleteSelected()` - Elimina cliente seleccionado (con confirmación)
- `refreshTable()` - Recarga datos de la tabla

**Flujo de uso:**
1. Ver lista en tabla
2. Clic "Nou" → Formulario aparece abajo
3. Rellenar campos → "Desar"
4. Validaciones + guardar + actualizar tabla
5. Mensaje en barra de estado

**📚 Documentación completa:** Ver `docs/UI_v1.4_COMPLETO.md` sección ClientsPanel

---

#### 📦 **ArticlesPanel.java** - Gestión de artículos (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ArticlesPanel.java`

**Propósito:** Panel completo para gestionar artículos (CRUD completo)

**Estructura similar a ClientsPanel:**
- Tabla: Nom, Preu
- Formulario: 2 campos (Nom, Preu)
- Botones: Nou, Eliminar, Actualitzar

**Métodos:**
- `saveArticle()` - Valida y guarda
- `deleteSelected()` - Elimina con confirmación
- `refreshTable()` - Actualiza lista

---

#### 🧾 **InvoicesPanel.java** - Gestión de facturas (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/InvoicesPanel.java`

**Propósito:** Panel para crear y consultar facturas

**Constructor:**
```java
InvoicesPanel(InvoiceService invoiceService, ClientService clientService,
              ArticleService articleService, ConfigService configService,
              Consumer<String> statusUpdater)
```

**Componentes:**

**Búsqueda:**
- Campo ID + Botón "Buscar"
- Muestra detalles en diálogo

**Tabla de facturas:**
- Columnas: ID, Data, DNI Client, IVA (%), Total
- Calcula totales automáticamente

**Formulario de nueva factura:**
- Campo DNI cliente
- **ComboBox de artículos** (se carga automáticamente)
- Spinner cantidad (1-9999)
- Botón "Afegir Linia"
- JList de líneas añadidas
- Botones: Eliminar Linia, Netejar Tot, Cancel·lar, Desar Factura

**Métodos principales:**
- `refreshArticlesCombo()` - Carga artículos en combo
- `addLine()` - Añade línea a la lista temporal
  - Valida artículos disponibles
  - Busca artículo seleccionado
  - Crea InvoiceLine
  - Añade a lista visual
- `removeLine()` - Elimina línea seleccionada
- `saveInvoice()` - Crea la factura
  - Valida DNI no vacío
  - Valida mínimo 1 línea, máximo 10
  - Verifica cliente existe
  - Crea fecha actual
  - Llama a `invoiceService.createInvoice()`
  - Muestra totales en diálogo
- `searchInvoice()` - Busca y muestra factura por ID
- `refreshInvoicesTable()` - Actualiza tabla con cálculo de totales

**Flujo crear factura:**
1. Clic "Nova Factura"
2. Introduce DNI del cliente
3. Selecciona artículo del combo
4. Indica cantidad
5. Clic "Afegir Linia" (repite para más líneas)
6. Clic "Desar Factura"
7. Sistema valida, crea, muestra totales

**📚 Documentación completa:** Ver `docs/UI_v1.4_COMPLETO.md` sección InvoicesPanel

---

#### ⚙️ **ConfigPanel.java** - Configuración (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ConfigPanel.java`

**Propósito:** Panel para configurar el IVA del sistema

**Componentes:**
- Card centrado (400x200px)
- Título "IVA (%)"
- Campo de texto con valor actual
- Botón "Desar" (azul)

**Métodos:**
- `saveConfig()` - Guarda configuración
  - Valida número válido
  - Llama a `service.setIva(iva)`
  - Muestra mensaje éxito/error

---

## 🎯 ARQUITECTURA UI v1.4

**Flujo de creación:**
```
GuiUI (Main)
├── AppleStyler.applyTheme()  (Configuración global)
├── AppleSidebar              (Navegación)
│   ├── Logo
│   ├── Botones navegación
│   └── Botón salir
└── CardLayout (Contenido)
    ├── WelcomePanel
    ├── ClientsPanel
    ├── ArticlesPanel
    ├── InvoicesPanel
    └── ConfigPanel
```

**Ventajas de la refactorización:**
- ✅ Código más limpio y mantenible
- ✅ Cada archivo con responsabilidad única
- ✅ Fácil añadir nuevos paneles
- ✅ Estilos centralizados y consistentes
- ✅ Componentes reutilizables

---

## VALIDACIONES (UTIL)

### ✅ **Validation.java** - ⚡ ACTUALIZADO v1.5
**Ubicación:** `src/util/Validation.java`

**Propósito:** Funciones estáticas de validación reutilizables en todo el sistema

**🔥 MÉTODOS MEJORADOS EN v1.5:**

**`validDni(String dni)`**: ✨ **MEJORADO** - Valida formato DNI español
- ✅ Comprueba que tenga exactamente 9 caracteres
- ✅ **NUEVO:** Verifica que los primeros 8 sean dígitos
- ✅ **NUEVO:** Verifica que el último carácter sea una letra
- ❌ Rechaza: null, longitud ≠ 9, formatos inválidos (ej: "123456789", "AAAAAAAAA")
- ✅ Acepta: "12345678Z", "87654321A"
- Retorna boolean

**`validPhone(String phone)`**: ✨ **MEJORADO** - Valida teléfono español
- ✅ Comprueba que tenga exactamente 9 dígitos
- ✅ Verifica que sea numérico
- ✅ **NUEVO:** Verifica que empiece por 6, 7, 8 o 9 (móviles y fijos españoles)
- ❌ Rechaza: null, longitud ≠ 9, empezando por 0-5 (ej: "000000000", "111111111")
- ✅ Acepta: "612345678", "971234567", "834567890"
- Retorna boolean

**`validCP(String cp)`**: Valida código postal español
- ✅ Comprueba que tenga exactamente 5 dígitos
- ✅ Verifica que sea numérico
- Retorna boolean

**`validLength(String s, int maxLength)`**: Valida longitud máxima
- ✅ Comprueba que el string no supere el límite
- ✅ Si es null, retorna true (se permite)
- Útil para validar campos de texto
- Retorna boolean

**`notEmpty(String s)`**: ✨ **NUEVO v1.5** - Valida que no esté vacío
- ✅ Verifica que el string no sea null
- ✅ Verifica que no esté vacío después de trim()
- ❌ Rechaza: null, "", "   "
- ✅ Acepta: "texto", "  texto  " (quita espacios)
- Retorna boolean
- **USO:** Validación obligatoria de campos en formularios

**`validPrice(String price)`**: ✨ **MEJORADO** - Valida formato de precio
- ✅ Debe ser número decimal válido
- ✅ No puede ser negativo
- ✅ Máximo 6 caracteres (sin contar el punto decimal)
- ✅ **NUEVO:** Máximo 2 decimales permitidos
- ❌ Rechaza: null, "", "abc", "-5", "1234.567" (3 decimales), "1234567" (7 dígitos)
- ✅ Acepta: "0.45", "123.99", "1000", "1.5"
- Retorna boolean

**`validQuantity(String qty)`**: Valida cantidad
- ✅ Debe ser número entero entre 1 y 9999
- ❌ Rechaza: null, "0", "-1", "10000", "abc"
- ✅ Acepta: "1", "50", "9999"
- Retorna boolean

**`noForbiddenChars(String s)`**: Valida caracteres prohibidos
- ✅ Verifica que no contenga el carácter `;` (punto y coma)
- ⚠️ **RAZÓN:** El separador CSV es `;`, permitirlo rompe el formato
- Si es null, retorna true
- ❌ Rechaza: "Hola;Adiós", "Texto; con punto y coma"
- ✅ Acepta: "Hola", "Texto normal", null
- Retorna boolean

### 📋 RESUMEN DE VALIDACIONES v1.5

| Función | Antes | Ahora (v1.5) | Mejora |
|---------|-------|--------------|--------|
| `validDni()` | Solo longitud 9 | 8 dígitos + letra | ✅ Formato español |
| `validPhone()` | Solo 9 dígitos | 9 dígitos empezando 6/7/8/9 | ✅ Teléfonos españoles |
| `validPrice()` | Cualquier decimal | Máximo 2 decimales | ✅ Formato moneda |
| `notEmpty()` | ❌ No existía | Valida campos obligatorios | ✨ NUEVO |

**¿Cuándo modificar?**
- Para añadir nuevas validaciones (ej: email, NIF)
- Para cambiar reglas de validación existentes
- Para añadir validaciones de formato específicas
- Para validar caracteres especiales adicionales

**¿Dónde se usan?**
- **`ArticleService.addArticle()`** → validDni, validPhone, validCP, notEmpty, validPrice, noForbiddenChars
- **`ClientService.addClient()`** → validLength, notEmpty, noForbiddenChars
- **`ArticleService.updateArticle()`** → ✨ NUEVO v1.5
- **`ClientService.updateClient()`** → ✨ NUEVO v1.5

---

## ARCHIVO PRINCIPAL (MAIN)

### 🚀 **Main.java**
**Ubicación:** `src/Main.java`

**Propósito:** Punto de entrada de la aplicación. Inicializa todos los servicios y lanza la interfaz.

**Flujo de ejecución:**
1. Determina directorio de datos (`user.dir + /data`)
2. Inicializa ConfigService y carga configuración del IVA
3. Inicializa repositorios (Client, Article, Invoice, InvoiceLine)
4. Inicializa servicios con sus dependencias
5. Detecta modo de ejecución:
   - Con argumento `--console` → Lanza ConsoleUI
   - Sin argumentos → Lanza GuiUI (interfaz gráfica v1.4)
6. Lanza interfaz correspondiente

**Modos de ejecución:**
- **Interfaz gráfica (predeterminado):** `java Main`
- **Interfaz de consola:** `java Main --console`

**Diagrama de dependencias:**
```
Main
├── ConfigService
├── Repositories
│   ├── ClientRepository
│   ├── ArticleRepository
│   ├── InvoiceRepository
│   └── InvoiceLineRepository
├── Services
│   ├── ClientService (usa ClientRepository)
│   ├── ArticleService (usa ArticleRepository)
│   └── InvoiceService (usa Invoice/Line Repository + ClientRepository)
└── UI
    ├── ConsoleUI (modo --console)
    └── GuiUI (modo predeterminado, v1.4)
```

**¿Cuándo modificar?**
- Para cambiar la ruta de datos por defecto
- Para añadir nuevos servicios
- Para cambiar el modo de interfaz por defecto
- Para añadir opciones de línea de comandos

---

## 🧪 TESTS AUTOMATIZADOS

### **TestRunner.java**
**Ubicación:** `src/TestRunner.java`

**Propósito:** Sistema de tests automatizados para verificar funcionalidad

**Tests implementados:**
- Validación de DNI, teléfono, CP
- Creación de clientes
- Creación de artículos
- Generación de IDs de factura
- Creación de facturas con líneas
- Cálculo de totales

**Ejecución:** `java TestRunner`

---

## GUÍA DE MODIFICACIONES

Esta sección te ayuda a saber dónde modificar el código según lo que quieras cambiar.

### 📝 Añadir un campo nuevo a un modelo (ej: email al Cliente):

**1. Modelo (`Client.java`):**
```java
// Añadir constante
public static final int MAX_EMAIL_LENGTH = 50;

// Añadir atributo
private String email;

// Añadir getter/setter con truncado
public void setEmail(String email) {
    this.email = truncate(email == null ? "" : email, MAX_EMAIL_LENGTH);
}

// Actualizar toCSV()
return dni + ";" + name + ";" + ... + ";" + email;

// Actualizar fromCSV()
client.setEmail(parts.length > 7 ? parts[7] : "");

// Actualizar escape/unescape
```

**2. Service (`ClientService.java`):**
```java
// Añadir validación en addClient()
if (c.getEmail().contains(";")) {
    return "L'email no pot contenir el caràcter ';'";
}
if (!Validation.validEmail(c.getEmail())) {
    return "Email invàlid";
}
```

**3. Validation (`Validation.java`):**
```java
public static boolean validEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
}
```

**4. UI Panel (`ClientsPanel.java`):**
```java
// Añadir campo al formulario
JTextField txtEmail = new JTextField(20);

// Añadir columna a la tabla
tableModel = new DefaultTableModel(
    new String[]{"DNI", "Nom", "Adreça", "CP", "Població", "Província", "Telèfon", "Email"}, 0
);

// Actualizar saveClient() para leer el campo
client.setEmail(txtEmail.getText().trim());

// Actualizar refreshTable() para mostrar el campo
model.addRow(new Object[]{..., client.getEmail()});
```

---

### 🎨 Cambiar colores de la interfaz:

**Archivo:** `src/ui/components/AppleStyler.java`

**Modificar las constantes de color:**
```java
public static final Color BLUE = new Color(0, 122, 255); // Cambiar aquí
public static final Color RED = new Color(255, 59, 48);  // Cambiar aquí
// ... etc
```

Todos los componentes que usen AppleStyler se actualizarán automáticamente.

---

### 🆕 Añadir un nuevo panel a la interfaz:

**1. Crear el panel (`ui/panels/MiNuevoPanel.java`):**
```java
package ui.panels;
import javax.swing.*;
import ui.components.AppleStyler;
import java.util.function.Consumer;

public class MiNuevoPanel extends JPanel {
    public MiNuevoPanel(Consumer<String> statusUpdater) {
        setLayout(new BorderLayout());
        setBackground(AppleStyler.BG_LIGHT);
        // ... construir UI
    }
}
```

**2. Registrar en GuiUI (`GuiUI.java`):**
```java
// En el método show(), añadir:
MiNuevoPanel miPanel = new MiNuevoPanel(this::updateStatus);
contentPanel.add(miPanel, "MI_PANEL");

// En createSidebar(), añadir:
sidebar.addButton("Mi Panel", e -> showCard("MI_PANEL"));
```

---

### 🗑️ Añadir funcionalidad de eliminar:

Ya está implementado en v1.2 para Clientes y Artículos.

**Para otro modelo:**
1. **Repository:** Crear método `delete(String key)` que filtre y reescriba el archivo
2. **Service:** Crear método `deleteXxx(String key)` que llame al repository
3. **UI Panel:** Añadir botón "Eliminar" que llame al service y actualice la tabla

---

### 📊 Cambiar límites de validación:

**Artículos:**
- **Archivo:** `model/Article.java`
- Modificar `MAX_NAME_LENGTH` o `MAX_PRICE_LENGTH`

**Clientes:**
- **Archivo:** `model/Client.java`
- Modificar las constantes `MAX_XXX_LENGTH`

**Facturas:**
- **Archivo:** `service/InvoiceService.java`
- Modificar límite de líneas en `createInvoice()` (actualmente máximo 10)

---

### 🔧 Añadir validación personalizada:

**1. Crear función en `Validation.java`:**
```java
public static boolean validMiCampo(String valor) {
    // Tu lógica de validación
    return /* true o false */;
}
```

**2. Usar en el Service correspondiente:**
```java
if (!Validation.validMiCampo(objeto.getCampo())) {
    return "Error: formato invàlid";
}
```

---

### 📁 Cambiar ruta de archivos de datos:

**Archivo:** `Main.java`

Modificar:
```java
String dataDir = System.getProperty("user.dir") + "/data";
```

Por la ruta que desees.

---

### 🎯 Cambiar tamaño de ventana:

**Archivo:** `GuiUI.java`

Modificar en el método `show()`:
```java
frame.setSize(1300, 800); // Cambiar estas dimensiones
```

---

## 📚 HISTORIAL DE VERSIONES

### 🍎 **Versión 1.4** (2025-01-07) - REDISEÑO APPLE

**🎨 Refactorización completa de la UI:**
- ✅ Código modular: 1 archivo (900 líneas) → 7 archivos (~150 líneas cada uno)
- ✅ Nueva carpeta `ui/components/` con utilidades reutilizables
- ✅ Nueva carpeta `ui/panels/` con cada panel en su archivo
- ✅ `AppleStyler.java`: Centraliza todos los estilos visuales
- ✅ `AppleSidebar.java`: Componente de barra lateral reutilizable
- ✅ `WelcomePanel.java`: Pantalla de bienvenida minimalista
- ✅ `ClientsPanel.java`: Gestión de clientes completa
- ✅ `ArticlesPanel.java`: Gestión de artículos completa
- ✅ `InvoicesPanel.java`: Creación y consulta de facturas
- ✅ `ConfigPanel.java`: Configuración del IVA

**🎨 Diseño minimalista estilo Apple:**
- ✅ Paleta de colores refinada (#007AFF, #FF3B30, #F8F8FA)
- ✅ Tipografía Segoe UI en múltiples tamaños
- ✅ Botones sin bordes con hover suave
- ✅ Tablas sin cuadrícula, filas de 40px
- ✅ Cards blancas con bordes sutiles
- ✅ Espaciado generoso y diseño limpio
- ✅ Ventana ampliada a 1300x800px

**🐛 Correcciones:**
- ✅ Arreglado `getIvaPercent()` en ConfigService
- ✅ Eliminados iconos problemáticos de la sidebar
- ✅ Panel de facturas completamente funcional
- ✅ ComboBox de artículos con carga automática
- ✅ Cálculo de totales en tabla de facturas

**📚 Documentación:**
- ✅ Creado `docs/UI_v1.4_COMPLETO.md` con documentación exhaustiva
- ✅ Actualizado `docs/DICCIONARIO_CODIGO.md`

---

### 🎨 **Versión 1.3** (2025-01-05) - MEJORAS VISUALES

- ✅ Panel de bienvenida profesional
- ✅ Barra lateral de navegación con iconos
- ✅ Botones con estilos diferenciados (primario, secundario, peligro, éxito)
- ✅ Tablas elegantes sin cuadrícula
- ✅ Headers con fondo blanco y títulos grandes
- ✅ Formularios tipo tarjeta con bordes redondeados
- ✅ Ventana ampliada a 1200x750px
- ✅ Esquema de colores moderno

---

### 🆕 **Versión 1.2** (2025-01-03) - NUEVAS FUNCIONALIDADES

- ✅ Botón "Eliminar" en gestión de clientes
- ✅ Botón "Eliminar" en gestión de artículos
- ✅ Protección contra carácter `;` en todos los campos
- ✅ Mensajes de error claros por campo
- ✅ Métodos `delete()` en repositorios
- ✅ Métodos `deleteClient()` y `deleteArticle()` en servicios

---

### 🔒 **Versión 1.1** (Original) - MEJORAS DE SEGURIDAD

- ✅ Truncado automático en todos los setters
- ✅ Conversión de null a cadena vacía
- ✅ Escape mejorado de múltiples caracteres especiales (`; \ \n \r`)
- ✅ Constantes centralizadas para límites de longitud
- ✅ Método helper `truncate()` reutilizable
- ✅ Validaciones robustas en servicios

---

## 📖 RESUMEN PARA PROFESORES

**¿Qué hace este programa?**
Sistema de gestión de facturación con clientes, artículos y facturas. Interfaz gráfica moderna estilo Apple y persistencia en archivos de texto (CSV).

**Arquitectura:**
- **Modelo MVC:** Separación de capas (Model, Repository, Service, UI)
- **Modelos:** Representan datos (Client, Article, Invoice, InvoiceLine)
- **Repositorios:** Acceso a archivos CSV
- **Servicios:** Lógica de negocio y validaciones
- **UI:** Interfaz gráfica modular (v1.4) o consola

**Tecnologías:**
- Java Swing para interfaz gráfica
- Archivos CSV para persistencia
- Arquitectura de capas
- Validaciones exhaustivas

**Características destacadas:**
- Diseño minimalista estilo Apple
- Código modular y mantenible
- Validaciones robustas
- Protección contra caracteres especiales
- Cálculo automático de totales con IVA

**Puntos clave:**
1. **Separación de responsabilidades:** Cada clase tiene una función clara
2. **Validaciones:** Protección contra datos inválidos
3. **Escape de caracteres:** Protege la integridad del CSV
4. **Truncado automático:** Evita desbordamiento de campos
5. **UI modular:** Fácil de mantener y extender

---

## 🔍 ÍNDICE ALFABÉTICO DE ARCHIVOS

| Archivo | Propósito | Ubicación |
|---------|-----------|-----------|
| **AppleSidebar.java** | Barra lateral de navegación | `ui/components/` |
| **AppleStyler.java** | Utilidades de estilo | `ui/components/` |
| **Article.java** | Modelo de artículo | `model/` |
| **ArticleRepository.java** | Persistencia de artículos | `repository/` |
| **ArticleService.java** | Lógica de artículos | `service/` |
| **ArticlesPanel.java** | Panel gestión artículos | `ui/panels/` |
| **Client.java** | Modelo de cliente | `model/` |
| **ClientRepository.java** | Persistencia de clientes | `repository/` |
| **ClientService.java** | Lógica de clientes | `service/` |
| **ClientsPanel.java** | Panel gestión clientes | `ui/panels/` |
| **ConfigPanel.java** | Panel configuración IVA | `ui/panels/` |
| **ConfigService.java** | Gestión configuración | `service/` |
| **ConsoleUI.java** | Interfaz de consola | `ui/` |
| **GuiUI.java** | Orquestador interfaz gráfica | `ui/` |
| **Invoice.java** | Modelo de factura | `model/` |
| **InvoiceLine.java** | Modelo línea de factura | `model/` |
| **InvoiceLineRepository.java** | Persistencia líneas | `repository/` |
| **InvoiceRepository.java** | Persistencia facturas | `repository/` |
| **InvoiceService.java** | Lógica de facturas | `service/` |
| **InvoicesPanel.java** | Panel gestión facturas | `ui/panels/` |
| **Main.java** | Punto de entrada | `src/` |
| **TestRunner.java** | Tests automatizados | `src/` |
| **Validation.java** | Validaciones reutilizables | `util/` |
| **WelcomePanel.java** | Panel de bienvenida | `ui/panels/` |

---

## 💡 CONSEJOS FINALES

**Para modificar el proyecto:**
1. **Siempre** lee primero la documentación completa de UI en `docs/UI_v1.4_COMPLETO.md`
2. **Usa las constantes** para limitar longitudes (MAX_XXX_LENGTH)
3. **Añade validaciones** en los servicios, no en la UI
4. **Usa AppleStyler** para mantener estilos consistentes
5. **Testea** cada cambio con el TestRunner

**Orden de lectura recomendado:**
1. Este diccionario (vista general)
2. `docs/UI_v1.4_COMPLETO.md` (detalles UI)
3. Código fuente (empezando por Main.java)

**Flujo de datos:**
```
UI → Service → Repository → Archivo CSV
   ← Service ← Repository ←
```

**Recuerda:**
- Los **modelos** solo almacenan datos
- Los **repositorios** solo leen/escriben archivos
- Los **servicios** contienen la lógica y validaciones
- La **UI** solo muestra y captura datos del usuario

---

**📚 FIN DEL DICCIONARIO - Versión 1.4**
- `addFlexibleSpace()` - Espacio flexible
- `selectButton(JButton)` - Selección programática

---

### 📄 PANELES INDIVIDUALES (ui/panels/)

#### 🏠 **WelcomePanel.java** - Bienvenida (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/WelcomePanel.java`

**Componentes:** Icono "$", Título, Subtítulo, Versión, Botones de acceso rápido

**Métodos:**
- `setOnNewClient(Runnable)` - Configura acción botón cliente
- `setOnNewInvoice(Runnable)` - Configura acción botón factura

---

#### 👥 **ClientsPanel.java** - Gestión de clientes (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ClientsPanel.java`

**Constructor:** `ClientsPanel(ClientService, Consumer<String>)`

**Componentes:**
- Header: Título + botones (Nou, Eliminar, Actualitzar)
- Tabla: 7 columnas (DNI, Nom, Adreça, CP, Població, Província, Telèfon)
- Formulario: Card con 7 campos + botones

**Métodos:**
- `showForm()`, `hideForm()`, `clearForm()`, `saveClient()`, `deleteSelected()`, `refreshTable()`

---

#### 📦 **ArticlesPanel.java** - Gestión de artículos (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ArticlesPanel.java`

**Constructor:** `ArticlesPanel(ArticleService, Consumer<String>)`

**Componentes:**
- Tabla: 2 columnas (Nom, Preu)
- Formulario: Card con 2 campos

**Métodos:**
- `saveArticle()`, `deleteSelected()`, `refreshTable()`

---

#### 🧾 **InvoicesPanel.java** - Gestión de facturas (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/InvoicesPanel.java`

**Constructor:** `InvoicesPanel(InvoiceService, ClientService, ArticleService, ConfigService, Consumer<String>)`

**Componentes:**
- Búsqueda: Campo ID + botón
- Tabla: 5 columnas (ID, Data, DNI Client, IVA%, Total)
- Formulario: DNI cliente + ComboBox artículos + Spinner cantidad + JList líneas

**Métodos principales:**
- `refreshArticlesCombo()` - Carga artículos en combo
- `addLine()` - Añade línea a lista temporal
- `removeLine()` - Elimina línea
- `saveInvoice()` - Crea factura completa
- `searchInvoice()` - Busca y muestra detalles
- `refreshInvoicesTable()` - Actualiza tabla

**Flujo crear factura:** DNI → Seleccionar artículo → Cantidad → Afegir Linia (repetir) → Desar Factura

---

#### ⚙️ **ConfigPanel.java** - Configuración (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ConfigPanel.java`

**Constructor:** `ConfigPanel(ConfigService, Consumer<String>)`

**Componentes:**
- Card centrado (400x200px)
- Título "IVA (%)"
- Campo con valor actual
- Botón "Desar"

**Métodos:**
- `saveConfig()` - Valida y guarda IVA

---

## 🎯 ARQUITECTURA UI v1.4

**Jerarquía:**
```
GuiUI (Orquestador)
├── AppleStyler (Estilos)
├── AppleSidebar (Navegación)
└── CardLayout
    ├── WelcomePanel
    ├── ClientsPanel
    ├── ArticlesPanel
    ├── InvoicesPanel
    └── ConfigPanel
```

**Comunicación:**
- Paneles → GuiUI: Via `Consumer<String>` para actualizar status
- GuiUI → Paneles: Via métodos públicos y constructores
- Todos usan AppleStyler para estilos consistentes

---

## 📝 GUÍA RÁPIDA DE MODIFICACIONES UI

### Cambiar colores:
**Archivo:** `AppleStyler.java` → Modificar constantes `BLUE`, `RED`, etc.

### Añadir panel:
1. Crear clase en `ui/panels/`
2. Extender `JPanel`, usar `AppleStyler`
3. En `GuiUI.java`: Instanciar y añadir al CardLayout
4. En sidebar: Añadir botón con `addButton()`

### Añadir campo a tabla:
**Panel correspondiente:** Modificar `DefaultTableModel` con nuevas columnas

### Cambiar tamaño ventana:
**GuiUI.java:** `frame.setSize(ancho, alto);`

---

**📚 DOCUMENTACIÓN COMPLETA:** Para información exhaustiva de cada método, parámetro, ejemplo de código y diagramas, consulta **`docs/UI_v1.4_COMPLETO.md`**

---

## VALIDACIONES (UTIL)

### ✅ **Validation.java**
**Ubicación:** `src/util/Validation.java`

**Propósito:** Funciones estáticas de validación reutilizables

**Métodos principales:**
- `validDni(String)` - Valida DNI (9 caracteres)
- `validPhone(String)` - Valida teléfono (9 dígitos)
- `validCP(String)` - Valida código postal (5 dígitos)
- `validLength(String, int)` - Valida longitud máxima
- `validPrice(String)` - Valida precio (decimal, no negativo, max 6 chars)
- `validQuantity(String)` - Valida cantidad (1-9999)

---

## ARCHIVO PRINCIPAL (MAIN)

### 🚀 **Main.java**
**Ubicación:** `src/Main.java`

**Propósito:** Punto de entrada. Inicializa servicios y lanza interfaz.

**Flujo:**
1. Determina directorio de datos (`user.dir + /data`)
2. Inicializa servicios (Client, Article, Invoice, Config)
3. Detecta modo: `--console` → ConsoleUI, sino → GuiUI
4. Lanza interfaz correspondiente

**Ejecución:**
- Gráfica: `java Main`
- Consola: `java Main --console`

---

## GUÍA DE MODIFICACIONES

### Añadir campo a modelo:
1. Añadir atributo + getter/setter
2. Actualizar `toCSV()` y `fromCSV()`
3. Actualizar Service (validaciones)
4. Actualizar UI (formulario + tabla)

### Añadir validación:
1. Crear método en `Validation.java`
2. Usar en Service correspondiente

### Cambiar colores UI:
**Archivo:** `AppleStyler.java`
Modificar constantes `BLUE`, `RED`, etc.

### Añadir panel:
1. Crear clase en `ui/panels/`
2. Extender JPanel, usar AppleStyler
3. En GuiUI: instanciar y añadir a CardLayout
4. En sidebar: añadir botón navegación

---

## MEJORAS IMPLEMENTADAS (v1.1)
- ✅ Barra lateral de navegación con iconos
- ✅ Pantalla de bienvenida profesional
- ✅ Ventana ampliada (1200x750px)
- ✅ Esquema de colores moderno
- ✅ Botones con estilos diferenciados
- ✅ Tablas elegantes
- ✅ Formularios tipo tarjeta

**Estructura general:**
- Usa **CardLayout** para mostrar diferentes paneles en la misma ventana
- **Barra lateral** (sidebar) fija en el lado izquierdo
- Paneles de contenido en el área principal
- Barra de estado en la parte inferior con iconos

**Métodos principales:**

**`show()`**: Inicializa y muestra la ventana
- Aplica tema moderno mejorado
- Crea JFrame de **1200x750px** (✨ ampliado en v1.3)
- Construye barra lateral de navegación
- Construye todos los paneles
- Los añade al CardLayout
- Hace visible la ventana
- Muestra panel de bienvenida por defecto

**`applyModernTheme()`**: Aplica tema visual moderno
- Activa Look and Feel Nimbus (con fallback a sistema)
- Configura fuentes: Segoe UI (13-24px según contexto)
- Personaliza colores base:
  - Azul primario: #336298
  - Gris azulado: #6C757D
  - Fondo controles: #F8F9FA

**`applyComponentStyles(Component c)`**: Estiliza componentes recursivamente
- Aplica estilos a TextField, TextArea, ComboBox, Spinner, Label
- Llama a `styleTable()` para tablas
- Recorre recursivamente todos los componentes hijos

**`styleTable(JTable)`**: ✨ NUEVO v1.3 - Estiliza tablas con diseño elegante
- Filas más altas (35px)
- Sin líneas de cuadrícula
- Selección en azul claro (#DCEDFF)
- Cabecera con fondo gris claro (#F8F9FA)
- Bordes sutiles (#DEE2E6)

**Métodos de estilizado de botones (✨ NUEVOS v1.3):**

**`stylePrimaryButton(JButton)`**: Botones azules para acciones principales
- Color: #0D6EFD → #0A58CA (hover)
- Font: Segoe UI Bold 13px
- Padding: 10px 20px
- Efecto hover con cambio de color
- Cursor pointer

**`styleSecondaryButton(JButton)`**: Botones grises para acciones secundarias
- Color: #6C757D → #5A6268 (hover)
- Font: Segoe UI Regular 13px
- Mismos efectos que primario

**`styleSuccessButton(JButton)`**: Botones verdes para guardar/confirmar
- Color: #198754 → #146C43 (hover)
- Font: Segoe UI Bold 13px
- Para acciones de éxito

**`styleDangerButton(JButton)`**: Botones rojos para eliminar
- Color: #DC3545 → #BB2D3B (hover)
- Font: Segoe UI Regular 13px
- Para acciones destructivas

**`createFormLabel(String)`**: ✨ NUEVO v1.3 - Crea labels consistentes
- Font: Segoe UI Bold 13px
- Color: #343A40
- Para formularios

**Componentes de navegación (✨ NUEVOS v1.3):**

**`buildSidebar()`**: Construye barra lateral de navegación
- Fondo gris oscuro (#343A40)
- Ancho: 240px
- Logo/título: "💼 Facturació"
- Botones de navegación con iconos
- Botón "Sortir" fijado al final
- Efecto hover en botones

**`createNavButton(String text, String cardName)`**: Crea botones de navegación
- Fondo oscuro (#343A40)
- Texto gris claro (#E9ECEF)
- Alineación izquierda
- Efecto hover (#495057)
- Cursor pointer
- Altura fija: 45px

**`buildWelcomePanel()`**: ✨ NUEVO v1.3 - Panel de bienvenida
- Icono grande central (💼)
- Título: "Sistema de Facturació"
- Subtítulo descriptivo
- Versión del sistema
- Botones de acceso rápido:
  - "➕ Nou Client"
  - "📄 Nova Factura"
- Diseño centrado y espacioso

**Paneles (Cards):**

### 🏠 **Panel WELCOME** - Pantalla de bienvenida (✨ NUEVO v1.3)
**Método:** `buildWelcomePanel()`

- Panel de inicio profesional
- Icono grande (💼 80px)
- Título grande (32px Bold)
- Subtítulo (16px Regular)
- Versión del sistema (12px)
- Botones de acceso rápido a funciones comunes
- Fondo gris claro (#F0F2F5)

### 👥 **Panel CLIENTS** - Gestión de clientes (✨ RENOVADO v1.3)
**Método:** `buildClientsPanel()`

**Diseño moderno v1.3:**

**Header (cabecera blanca):**
- Título grande con icono: "👥 Gestió de Clients" (24px Bold)
- Botones de acción alineados a la derecha:
  - "➕ Nou Client" (botón primario azul)
  - "🗑️ Eliminar" (botón peligro rojo)
  - "🔄 Actualitzar" (botón secundario gris)
- Línea separadora sutil (#DEE2E6)
- Padding: 20px 25px

**Contenido:**
- Fondo gris claro (#F0F2F5)
- **Tabla elegante:**
  - Columnas: DNI, Nom, Adreça, CP, Població, Província, Telèfon
  - Filas altas (35px)
  - Sin cuadrícula
  - Selección azul claro
  - Borde sutil
- **Formulario tipo tarjeta** (oculto por defecto):
  - Fondo blanco
  - Borde gris claro
  - Título: "📝 Dades del Client" (18px Bold)
  - Campos en grid 2 columnas
  - Labels en negrita
  - Botones:
    - "💾 Desar" (verde éxito)
    - "❌ Cancel·lar" (gris secundario)

**Flujo de alta:**
1. Clic en "➕ Nou Client" → muestra formulario tipo card
2. Usuario rellena campos con bordes modernos
3. Clic en "💾 Desar" → valida y guarda
4. Actualiza tabla con estilo moderno
5. Muestra mensaje en barra de estado con icono (✅ o ❌)

**✨ v1.2 - Flujo de eliminación:**
1. Usuario selecciona fila en tabla
2. Clic en "🗑️ Eliminar"
3. Diálogo de confirmación (JOptionPane.WARNING_MESSAGE)
4. Si confirma: elimina y actualiza
5. Mensaje: "✅ Client eliminat correctament" o "❌ No s'ha pogut eliminar"

**✨ v1.3 - Mejoras visuales:**
- Header separado del contenido
- Botones con colores semánticos
- Formulario en tarjeta blanca
- Mensajes con emojis
- Espaciado generoso

**¿Dónde modificar?**
- Cambiar colores: editar métodos `style*Button()`
- Añadir campo: modificar `buildClientsPanel()`, añadir JTextField
- Cambiar iconos: modificar los emojis en los textos

### 📦 **Panel ARTICLES** - Gestión de artículos
**Método:** `buildArticlesPanel()`

**Componentes (diseño antiguo, pendiente actualizar a v1.3):**
- **Tabla** (`articlesTable`): Muestra artículos
  - Columnas: Nom, Preu
- **Botones laterales:** 
  - "Alta article": Muestra formulario
  - **✨ v1.2:** "Eliminar article": Elimina el artículo seleccionado
  - "Refrescar": Recarga la tabla
  - "Tornar menú": Vuelve al menú
- **Formulario:**
  - Campo nombre
  - Campo precio
  - Botones Desar/Cancel·lar

**✨ v1.2 - Flujo de eliminación:**
1. Usuario selecciona fila
2. Clic en "Eliminar article"
3. Confirmación
4. Elimina y actualiza tabla + combo de facturas
5. Mensaje: "Article eliminat correctament"

**📝 NOTA:** Pendiente de actualizar con diseño v1.3 similar a clientes

### 🧾 **Panel INVOICES** - Gestión de facturas
**Método:** `buildInvoicesPanel()`

**Componentes (diseño antiguo, pendiente actualizar a v1.3):**
- **Tabla** (`invoicesTable`): Lista de facturas
- **Botones laterales** y formulario de creación
- **ComboBox artículos** con artículos existentes
- **Spinner cantidad**
- **Lista de líneas** temporal

**Flujo de creación de factura:**
1-10. [Mismo flujo que antes]

**📝 NOTA:** Pendiente de actualizar con diseño v1.3

### 📄 **Panel INVOICE_VIEW** - Vista detalle
**Método:** `buildInvoiceViewPanel()`
**📝 NOTA:** Pendiente de actualizar con diseño v1.3

### ⚙️ **Panel CONFIG** - Configuración
**Método:** `buildConfigPanel()`
**📝 NOTA:** Pendiente de actualizar con diseño v1.3
4. Valida que sea número
5. Guarda con `configSvc.setIva()`

**¿Cuándo modificar GuiUI?**
- **Añadir campo a tabla:** Modifica el `DefaultTableModel` con columnas adicionales
- **Cambiar estilo:** Edita `applyModernTheme()` y `applyComponentStyles()`
- **Añadir validación en UI:** Agrega checks antes de llamar a servicios
- **Cambiar disposición:** Modifica el layout de los paneles (GridLayout, BorderLayout, etc.)
- **Añadir nueva funcionalidad:** Crea nuevo panel con `buildXxxPanel()` y añádelo al CardLayout

---

## VALIDACIONES (UTIL)

### ✅ **Validation.java**
**Ubicación:** `src/util/Validation.java`

**Propósito:** Contiene funciones estáticas de validación reutilizables.

**Métodos:**

**`validDni(String dni)`**
- Valida que el DNI tenga exactamente 9 caracteres
- **Retorna:** true si válido, false si no
- **Uso:** `ClientService.addClient()`

**`validPhone(String phone)`**
- Valida que el teléfono tenga exactamente 9 dígitos
- Usa expresión regular: `\\d{9}`
- **Uso:** `ClientService.addClient()`

**`validCP(String cp)`**
- Valida que el código postal tenga exactamente 5 dígitos
- Usa expresión regular: `\\d{5}`
- **Uso:** `ClientService.addClient()`

**`validLength(String s, int max)`**
- Valida que el string no supere la longitud máxima
- **Uso:** En múltiples servicios para nombres, direcciones, etc.

**`validPrice(String price)`**
- Valida formato de precio:
  1. Debe ser un número decimal válido
  2. No puede ser negativo
  3. Máximo 6 caracteres (sin contar el punto decimal)
- **Ejemplo válido:** "123.45" (5 caracteres + punto)
- **Ejemplo inválido:** "1234567" (7 caracteres)
- **Uso:** `ArticleService.addArticle()`, `InvoiceService.createInvoice()`

**`validQuantity(String q)`**
- Valida cantidad:
  1. Debe ser un número entero
  2. Entre 1 y 9999
- **Uso:** `ConsoleUI.manageInvoices()`

**¿Cuándo modificar?**
- Si cambias las reglas de validación (ej: DNI con letra)
- Si quieres añadir más validaciones (ej: email, URL)
- Si cambias los límites de caracteres
- Si quieres validar formatos diferentes (ej: teléfono internacional)

---

## ARCHIVO PRINCIPAL (MAIN)

### 🚀 **Main.java**
**Ubicación:** `src/Main.java`

**Propósito:** Punto de entrada de la aplicación. Inicializa servicios y lanza la interfaz.

**Método main:**

**Paso 1: Determinar directorio de datos**
```java
String dataDir = System.getProperty("user.dir") + File.separator + "data";
```
- Obtiene el directorio actual de trabajo
- Añade "/data" (en Windows: "\data")

**Paso 2: Inicializar servicios**
```java
ClientService clientService = new ClientService(dataDir);
ArticleService articleService = new ArticleService(dataDir);
ConfigService configService = new ConfigService(dataDir);
InvoiceService invoiceService = new InvoiceService(dataDir);
```
- Cada servicio inicializa su repositorio correspondiente
- Los repositorios crean los archivos si no existen

**Paso 3: Detectar modo de ejecución**
```java
boolean console = false;
for (String a : args) {
    if ("--console".equalsIgnoreCase(a)) { console = true; break; }
}
```
- Si se pasa el argumento `--console` → interfaz de texto
- Si no → interfaz gráfica

**Paso 4: Lanzar interfaz**
- **Modo consola:**
  ```java
  ConsoleUI ui = new ConsoleUI(clientService, articleService, invoiceService, configService);
  ui.run();
  ```
  
- **Modo gráfico:**
  ```java
  SwingUtilities.invokeLater(() -> {
      GuiUI gui = new GuiUI(clientService, articleService, invoiceService, configService);
      gui.show();
  });
  ```
  - `SwingUtilities.invokeLater()` asegura que Swing se ejecute en el hilo EDT (Event Dispatch Thread)

**¿Cómo ejecutar la aplicación?**
- **Interfaz gráfica:** `java Main`
- **Interfaz consola:** `java Main --console`

**¿Cuándo modificar?**
- Si quieres cambiar la ubicación de los archivos de datos
- Si quieres añadir más argumentos de línea de comandos
- Si quieres inicializar datos de prueba
- Si quieres añadir logging

---

## GUÍA DE MODIFICACIONES

### 📝 **Cómo añadir un campo nuevo a un modelo**

**Ejemplo: Añadir campo "categoría" a Article**

1. **Modificar Article.java:**
   ```java
   private String category;
   
   public String getCategory() { return category; }
   public void setCategory(String category) { this.category = category; }
   ```

2. **Actualizar toCSV():**
   ```java
   public String toCSV() {
       return String.join(";", escape(name), escape(price), escape(category));
   }
   ```

3. **Actualizar fromCSV():**
   ```java
   public static Article fromCSV(String line) {
       String[] parts = line.split(";");
       if (parts.length < 3) return null;
       Article a = new Article();
       a.setName(unescape(parts[0]));
       a.setPrice(unescape(parts[1]));
       a.setCategory(unescape(parts[2]));
       return a;
   }
   ```

4. **Actualizar ArticleService.java:**
   - Añadir validación si es necesario

5. **Actualizar GuiUI.java:**
   - Añadir campo en el formulario
   - Añadir columna en la tabla

6. **Actualizar ConsoleUI.java:**
   - Pedir categoría en manageArticles()

---

### 🔧 **Cómo añadir una nueva validación**

**Ejemplo: Validar email de cliente**

1. **Añadir método en Validation.java:**
   ```java
   public static boolean validEmail(String email) {
       if (email == null) return false;
       return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
   }
   ```

2. **Usar en ClientService.addClient():**
   ```java
   if (!Validation.validEmail(c.getEmail())) 
       throw new IllegalArgumentException("Email invàlid");
   ```

---

### 🎨 **Cómo cambiar el estilo de la interfaz gráfica**

**Colores:**
- Modificar `applyModernTheme()` en GuiUI.java
- Cambiar `UIManager.put()` para colores base

**Botones:**
- Modificar `styleButton()` en GuiUI.java
- Cambiar `setBackground()`, `setForeground()`, bordes, etc.

**Fuente:**
- Modificar `Font uiFont = new Font(...)` en `applyModernTheme()`

**Tamaño de ventana:**
- Cambiar `frame.setSize(900, 650)` en `show()`

---

### 📊 **Cómo añadir una columna a una tabla**

**Ejemplo: Añadir columna "Email" a tabla de clientes**

1. **En buildClientsPanel() de GuiUI.java:**
   ```java
   clientsTableModel = new DefaultTableModel(
       new String[]{"DNI","Nom","Adreça","CP","Població","Província","Telèfon","Email"}, 0
   );
   ```

2. **En refreshClientsList():**
   ```java
   clientsTableModel.addRow(new Object[]{
       c.getDni(), c.getName(), c.getAddress(), c.getPostalCode(), 
       c.getCity(), c.getProvince(), c.getPhone(), c.getEmail()
   });
   ```

---

### 💾 **Cómo cambiar el formato de los archivos**

**Ver la siguiente sección sobre cambio de formato `;` a espacios fijos**

---

### 🔍 **Cómo añadir una búsqueda**

**Ejemplo: Buscar clientes por ciudad**

1. **Añadir método en ClientRepository.java:**
   ```java
   public List<Client> findByCity(String city) {
       List<Client> result = new ArrayList<>();
       for (Client c : findAll()) {
           if (city.equalsIgnoreCase(c.getCity())) {
               result.add(c);
           }
       }
       return result;
   }
   ```

2. **Exponer en ClientService.java:**
   ```java
   public List<Client> findByCity(String city) {
       return repo.findByCity(city);
   }
   ```

3. **Usar en UI:**
   - ConsoleUI: Añadir opción al menú
   - GuiUI: Añadir campo de búsqueda y botón

---

### 📈 **Cómo añadir estadísticas**

**Ejemplo: Total facturado por cliente**

1. **Añadir método en InvoiceService.java:**
   ```java
   public BigDecimal getTotalByClient(String dni) {
       List<Invoice> invoices = listAll();
       BigDecimal total = BigDecimal.ZERO;
       for (Invoice inv : invoices) {
           if (dni.equals(inv.getClientDni())) {
               List<InvoiceLine> lines = findLines(inv.getId());
               Totals t = calculateTotals(lines, inv.getIva());
               total = total.add(t.total);
           }
       }
       return total;
   }
   ```

2. **Mostrar en UI según necesidad**

---

## 🎯 RESUMEN DE FLUJOS IMPORTANTES

### Flujo completo: Crear una factura

1. **Usuario** introduce DNI del cliente → GuiUI
2. **GuiUI** llama a `clientSvc.find(dni)` → ClientService
3. **ClientService** llama a `repo.findByDni(dni)` → ClientRepository
4. **ClientRepository** lee `clients.txt`, busca y devuelve Client
5. **Usuario** selecciona artículo del combo → GuiUI
6. **GuiUI** llama a `articleSvc.find(name)` → ArticleService
7. **ArticleService** devuelve Article con precio
8. **Usuario** añade línea → se guarda temporalmente en `currentInvoiceLines`
9. **Usuario** hace clic en "Desar factura" → GuiUI
10. **GuiUI** llama a `invoiceSvc.createInvoice(...)` → InvoiceService
11. **InvoiceService** valida:
    - Cliente existe (ya validado)
    - Tiene líneas (sí)
    - Máximo 10 líneas
    - Cada línea válida
12. **InvoiceService** genera ID con `nextId()`
13. **InvoiceService** crea objeto Invoice
14. **InvoiceService** llama a `invoiceRepo.save(invoice)` → InvoiceRepository
15. **InvoiceRepository** escribe línea en `factures.txt`
16. **InvoiceService** llama a `lineRepo.saveLine(line)` para cada línea → InvoiceLineRepository
17. **InvoiceLineRepository** escribe cada línea en `linies_factura.txt`
18. **InvoiceService** devuelve Invoice creado
19. **GuiUI** llama a `invoiceSvc.calculateTotals()` → InvoiceService
20. **InvoiceService** calcula base, IVA y total
21. **GuiUI** muestra resultado en barra de estado

---

## 📚 GLOSARIO DE TÉRMINOS

- **Model (Modelo):** Clase que representa datos (Article, Client, Invoice)
- **Repository (Repositorio):** Clase que lee/escribe archivos
- **Service (Servicio):** Clase con lógica de negocio y validaciones
- **UI (User Interface):** Interfaz de usuario (ConsoleUI, GuiUI)
- **CSV (Comma-Separated Values):** Formato de archivo con valores separados (aquí por `;`)
- **CardLayout:** Sistema de Swing para mostrar múltiples paneles en una ventana
- **Swing:** Librería de Java para interfaces gráficas
- **EDT (Event Dispatch Thread):** Hilo especial de Swing para eventos de UI
- **BufferedReader/Writer:** Clases para leer/escribir archivos de texto
- **BigDecimal:** Clase de Java para cálculos precisos con decimales
- **Escape/Unescape:** Técnica para proteger caracteres especiales (`;` se convierte en `\;`)

---

## 🆘 PROBLEMAS COMUNES Y SOLUCIONES

### "Client no existeix" al crear factura
- **Causa:** DNI mal escrito o cliente no creado
- **Solución:** Verifica que el cliente esté en clients.txt

### "No s'ha pogut desar (potser ja existeix)"
- **Causa:** Clave duplicada (DNI, nombre de artículo, ID de factura)
- **Solución:** Usa una clave diferente

### La interfaz gráfica no se muestra bien
- **Causa:** Look and Feel no disponible
- **Solución:** Nimbus viene con Java SE, pero si falla, la aplicación usa el LAF por defecto

### Caracteres especiales (ñ, á, ç) no se ven bien
- **Causa:** Codificación de archivos incorrecta
- **Solución:** Asegúrate de que los archivos estén en UTF-8
  - Los Repository usan `new InputStreamReader(..., "UTF-8")`

### Los totales no cuadran
- **Causa:** Precios con más de 2 decimales
- **Solución:** BigDecimal redondea a 2 decimales con HALF_UP

---

## 🎉 MEJORAS IMPLEMENTADAS (v1.1)

En la versión 1.1 se han implementado importantes mejoras de seguridad y robustez en los modelos, manteniendo **total compatibilidad** con los datos existentes.

### ✨ RESUMEN DE MEJORAS

#### 1. **Truncado Automático de Campos**
Todos los setters ahora truncan automáticamente los valores que exceden la longitud máxima:
- Ya no necesitas preocuparte por errores de longitud
- El sistema ajusta automáticamente los datos
- Experiencia de usuario mejorada

**Ejemplo:**
```java
Article a = new Article();
a.setName("Nombre extremadamente largo que supera los 40 caracteres permitidos");
// Se trunca automáticamente a 40 caracteres
```

#### 2. **Mejor Manejo de Nulls**
Todos los setters convierten `null` en cadena vacía:
- Elimina NullPointerException
- Código más robusto
- Menos errores en runtime

#### 3. **Escape Mejorado de Caracteres Especiales**
El método `escape()` ahora protege contra:
- `;` (punto y coma) → `\;`
- `\` (backslash) → `\\`
- `\n` (salto de línea) → `\\n`
- `\r` (retorno de carro) → `\\r`

**Orden correcto de reemplazo:** Se procesa primero `\` para evitar conflictos.

#### 4. **Constantes Centralizadas**
Todas las longitudes máximas ahora son constantes:
```java
private static final int MAX_NAME_LENGTH = 40;
```
- Fácil de modificar
- Sin valores "mágicos" en el código
- Código más mantenible

#### 5. **Validación de Cantidad Automática**
`InvoiceLine.setQuantity()` ahora limita automáticamente:
- Mínimo: 1
- Máximo: 9999
- No más errores por valores fuera de rango

#### 6. **Parsing CSV Mejorado**
`split(";", -1)` mantiene campos vacíos al final:
- Más robusto ante datos incompletos
- Compatible con archivos mal formados

### 📊 ANTES vs DESPUÉS

**ANTES:**
- ❌ Errores si campos muy largos
- ❌ NullPointerException posibles
- ❌ Solo escapaba `;`
- ❌ Valores mágicos dispersos

**DESPUÉS:**
- ✅ Truncado automático
- ✅ Nunca null (se convierte en "")
- ✅ Escapa múltiples caracteres
- ✅ Constantes centralizadas

### 🎯 VENTAJAS

1. **Compatible con datos existentes** - No necesitas migrar nada
2. **Sin desperdicio de espacio** - Mantiene formato CSV eficiente
3. **Más robusto** - Menos errores en runtime
4. **Mejor UX** - Usuario no ve errores por longitud
5. **Más mantenible** - Código más limpio y organizado

### 📝 DOCUMENTACIÓN ADICIONAL

Para más detalles sobre las mejoras, consulta:
- **MEJORAS_CSV.md** - Documentación completa de cambios
- Incluye ejemplos de uso
- Guía de cómo modificar límites
- Comparativas antes/después

---

## 🎨 MEJORAS VISUALES (v1.3) (2025-01-03)

Se ha modernizado completamente la interfaz gráfica del sistema, manteniendo **100% de la funcionalidad** pero con un diseño profesional y atractivo.

### ✨ PRINCIPALES CAMBIOS VISUALES

#### 1. **Barra Lateral de Navegación** 🔥
**Componente nuevo:** Sidebar oscura y elegante

**Características:**
- Fondo gris oscuro (#343A40)
- Ancho: 240px fijo
- Logo/título destacado: "💼 Facturació"
- Botones de navegación con iconos:
  - 🏠 Inici
  - 👥 Clients
  - 📦 Articles
  - 🧾 Factures
  - ⚙️ Configuració
  - 🚪 Sortir (fijado al final)
- Efecto hover (#495057)
- Navegación intuitiva y rápida

**Método:** `buildSidebar()`

**¿Por qué?**
- Navegación más accesible (siempre visible)
- Identificación visual rápida con iconos
- Look moderno tipo aplicación web

---

#### 2. **Pantalla de Bienvenida** 🏠
**Componente nuevo:** Panel de inicio profesional

**Características:**
- Icono grande central (💼 80px)
- Título grande: "Sistema de Facturació" (32px Bold)
- Subtítulo: "Gestió de clients, articles i factures" (16px)
- Versión del sistema: "Versió 1.2" (12px)
- Botones de acceso rápido:
  - "➕ Nou Client" (botón azul)
  - "📄 Nova Factura" (botón azul)
- Fondo gris claro (#F0F2F5)

**Método:** `buildWelcomePanel()`

**¿Por qué?**
- Primera impresión profesional
- Acceso rápido a funciones principales
- Contexto claro del sistema

---

#### 3. **Ventana Ampliada** 🖼️
**ANTES:** 900x650px  
**AHORA:** 1200x750px

**Ventajas:**
- Más espacio para trabajar
- Tablas más legibles
- Menos scroll necesario
- Mejor aprovechamiento de pantallas modernas

---

#### 4. **Esquema de Colores Moderno** 🎨

**Paleta profesional:**

**Botones:**
- **Primario (Azul):** #0D6EFD → #0A58CA (hover)
  - Uso: Acciones principales
- **Secundario (Gris):** #6C757D → #5A6268 (hover)
  - Uso: Acciones de soporte
- **Éxito (Verde):** #198754 → #146C43 (hover)
  - Uso: Guardar, confirmar
- **Peligro (Rojo):** #DC3545 → #BB2D3B (hover)
  - Uso: Eliminar, cancelar

**Fondos:**
- Fondo general: #F0F2F5 (gris muy claro)
- Paneles/cards: #FFFFFF (blanco puro)
- Sidebar: #343A40 (gris oscuro)
- Barra estado: #FFFFFF (blanco)

**Bordes:**
- Principal: #DEE2E6
- Inputs: #CED4DA

**Texto:**
- Principal: #212529 (negro suave)
- Secundario: #343A40 (gris oscuro)
- Terciario: #6C757D (gris medio)

**Métodos:** `stylePrimaryButton()`, `styleSecondaryButton()`, `styleSuccessButton()`, `styleDangerButton()`

---

#### 5. **Tipografía Mejorada** ✍️

**Fuente:** Segoe UI (moderna y legible)

**Tamaños y pesos:**
- Títulos principales: 24px Bold
- Títulos sección: 18px Bold
- Títulos sidebar: 20px Bold
- Botones: 13-14px Bold
- Labels formulario: 13px Bold
- Texto normal: 13px Regular
- Texto pequeño: 12px Regular

**Ventajas:**
- Mayor legibilidad
- Jerarquía visual clara
- Consistencia total

---

#### 6. **Botones con Estilo Diferenciado** 🎯

**4 tipos de botones con efectos hover:**

Cada tipo tiene:
- Color específico según función
- Cambio de color en hover
- Cursor pointer (manita)
- Padding: 10px 20px
- Sin borde de foco
- Bordes redondeados sutiles

**Código:**
```java
stylePrimaryButton(btn);    // Azul
styleSecondaryButton(btn);  // Gris
styleSuccessButton(btn);    // Verde
styleDangerButton(btn);     // Rojo
```

---

#### 7. **Tablas Elegantes** 📊

**Mejoras:**
- Filas más altas: 35px (antes ~20px)
- Sin líneas de cuadrícula (diseño limpio)
- Fondo blanco puro
- Selección en azul claro (#DCEDFF)
- Cabecera:
  - Fondo gris claro (#F8F9FA)
  - Línea separadora inferior (#DEE2E6)
  - Columnas no reordenables
- Borde exterior sutil (#DEE2E6)
- Texto negro suave (#212529)

**Método:** `styleTable(JTable)`

**Código ejemplo:**
```java
table.setRowHeight(35);
table.setShowGrid(false);
table.setSelectionBackground(new Color(220, 237, 255));
```

---

#### 8. **Paneles Tipo Tarjeta (Cards)** 📇

**Panel de clientes modernizado (ejemplo completo):**

**A) Header (cabecera blanca):**
- Fondo blanco con línea separadora
- Título con icono: "👥 Gestió de Clients" (24px Bold)
- Botones de acción alineados a la derecha
- Padding: 20px 25px

**B) Contenido:**
- Fondo gris claro (#F0F2F5)
- Tabla con borde sutil
- Espaciado amplio (20-25px)

**C) Formulario (card style):**
- Fondo blanco
- Borde gris claro
- Padding: 20px
- Título con icono: "📝 Dades del Client"
- Campos en grid 2 columnas
- Labels en negrita
- Botones alineados a la derecha

---

#### 9. **Campos de Formulario Mejorados** 📝

**Inputs de texto:**
- Borde: #CED4DA (1px)
- Padding interno: 6px 10px
- Fondo blanco
- Texto: #212529
- Bordes redondeados sutiles

**Labels:**
- Font: Segoe UI Bold 13px
- Color: #343A40
- Alineación consistente

**Método:** `createFormLabel(String)`

---

#### 10. **Barra de Estado con Iconos** 📊

**Mejoras:**
- Fondo blanco (#FFFFFF)
- Línea separadora superior
- Padding: 10px 20px
- Font: Segoe UI 12px

**Iconos de estado:**
- 🟢 Sistema preparat (inicio)
- ✅ Acción exitosa
- ❌ Error
- ⚠️ Advertencia
- 🔄 Actualizando

**Ejemplo:**
```java
statusLabel.setText("✅ Client desat correctament");
statusLabel.setText("❌ Error: El nom no pot contenir...");
statusLabel.setText("⚠️ Selecciona un client...");
```

---

#### 11. **Iconos con Emojis** 😊

**Uso estratégico de emojis:**
- 💼 Sistema/Aplicación
- 🏠 Inicio
- 👥 Clientes
- 📦 Artículos
- 🧾 Facturas
- ⚙️ Configuración
- 🚪 Salir
- ➕ Nuevo/Añadir
- 🗑️ Eliminar
- 🔄 Actualizar
- 💾 Guardar
- ❌ Cancelar
- 📝 Formulario
- 📄 Documento

**Ventajas:**
- Identificación visual inmediata
- Sin necesidad de iconos externos
- Look moderno y amigable
- Funciona en todas las plataformas

---

### 📊 COMPARATIVA VISUAL

**ANTES (v1.2):**
- Menú central simple
- Sin iconos
- Botones básicos (un solo color)
- Ventana pequeña (900x650)
- Tablas con cuadrícula
- Formularios simples
- Colores planos

**AHORA (v1.3):**
- Barra lateral con iconos
- Pantalla de bienvenida
- Botones con 4 colores semánticos
- Ventana amplia (1200x750)
- Tablas elegantes sin cuadrícula
- Formularios tipo tarjeta
- Esquema de colores profesional
- Efectos hover
- Mensajes con iconos

---

### 🎯 ESTADO DE ACTUALIZACIÓN

**✅ COMPLETADO:**
- Barra lateral de navegación
- Pantalla de bienvenida
- Panel de clientes (completo)
- Métodos de estilizado de botones
- Método de estilizado de tablas
- Esquema de colores
- Ventana ampliada
- Barra de estado con iconos

**📝 PENDIENTE:**
- Panel de artículos (misma estructura que clientes)
- Panel de facturas (similar con formulario más complejo)
- Panel de configuración (versión simplificada)
- Vista detalle de factura (diseño elegante)

**Estimación para completar:** ~30 minutos

---

### 💡 PRINCIPIOS DE DISEÑO APLICADOS

#### 1. **Consistencia**
Todos los elementos del mismo tipo tienen el mismo estilo en toda la aplicación.

#### 2. **Jerarquía Visual**
Uso de tamaño, peso y color para guiar la atención del usuario.

#### 3. **Espaciado Generoso**
Padding amplio (20-25px) para respiración visual.

#### 4. **Feedback Visual**
Efectos hover, cursor pointer, mensajes con iconos.

#### 5. **Colores Semánticos**
Azul = acción, Verde = éxito, Rojo = peligro, Gris = neutral.

#### 6. **Modernidad**
Diseño limpio, minimalista, sin elementos innecesarios.

---

### 🔧 MODIFICAR COLORES

**Para cambiar el esquema de colores:**

1. **Botones primarios:**
```java
// En stylePrimaryButton()
b.setBackground(new Color(13, 110, 253));  // Tu color
```

2. **Sidebar:**
```java
// En buildSidebar()
sidebar.setBackground(new Color(52, 58, 64));  // Tu color
```

3. **Fondos:**
```java
// En buildClientsPanel() y otros
panel.setBackground(new Color(240, 242, 245));  // Tu color
```

4. **Tablas:**
```java
// En styleTable()
table.setSelectionBackground(new Color(220, 237, 255));  // Tu color
```

---

### 📝 DOCUMENTACIÓN ADICIONAL

Para más detalles visuales, consulta:
- **docs/MEJORAS_VISUALES_v1.3.md** - Documentación completa con:
  - Códigos de colores exactos
  - Capturas de pantalla conceptuales
  - Comparativas detalladas
  - Guía de mantenimiento
  - Próximos pasos

---

## 📞 CONTACTO Y SOPORTE

Este diccionario cubre todo el código del proyecto. Si necesitas:
- Modificar algo específico → busca en la sección correspondiente
- Añadir funcionalidad → sigue los ejemplos de "Guía de Modificaciones"
- Entender un flujo → consulta "Resumen de flujos importantes"

**¿No encuentras lo que buscas?** Usa Ctrl+F para buscar en este documento.

