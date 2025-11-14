# DOCUMENTACIÓN COMPLETA UI v1.4

Esta es la documentación completa y actualizada de la sección de Interfaz de Usuario para el diccionario.

---

### 💻 **ConsoleUI.java** - Interfaz de consola
**Ubicación:** `src/ui/ConsoleUI.java`
**Estado:** Sin cambios en v1.4

**Propósito:** Interfaz de texto para terminal

**Métodos:** `run()`, `manageClients()`, `manageArticles()`, `manageInvoices()`, `manageConfig()`

---

### 🖼️ **GuiUI.java** - Interfaz gráfica principal (✨ REFACTORIZADO v1.4)
**Ubicación:** `src/ui/GuiUI.java`

**🍎 CAMBIO IMPORTANTE v1.4:** Ahora es un **orquestador** (~150 líneas) en lugar de monolítico (~900 líneas)

**Responsabilidad:**
- Coordinar todos los componentes y paneles
- Gestionar la navegación (CardLayout)
- Actualizar la barra de estado
- Conectar paneles con servicios

**Métodos principales:**

**`show()`**: Inicializa y muestra la ventana
- Llama a `AppleStyler.applyTheme()`
- Crea JFrame de **1300x800px** (v1.4 Final)
- Crea sidebar con `createSidebar()`
- Crea paneles:
  - `WelcomePanel`
  - `ClientsPanel`
  - `ArticlesPanel`
  - `InvoicesPanel`
  - `ConfigPanel`
- Los añade al CardLayout
- Crea barra de estado
- Muestra ventana centrada

**`createSidebar()`**: Construye la barra lateral
- Crea instancia de `AppleSidebar`
- Añade logo: "Facturacio"
- Añade botones de navegación (sin iconos problemáticos):
  - Inici
  - Clients
  - Articles
  - Factures
  - Configuracio
  - Sortir
- Selecciona botón inicial

**`createStatusBar()`**: Crea barra de estado minimalista
- JLabel con fondo blanco
- Fuente: Segoe UI 12px
- Borde superior sutil
- Padding: 8px 20px

**`showCard(String cardName)`**: Navega entre paneles
- Usa CardLayout.show()
- Actualiza mensaje de estado

**`updateStatus(String message)`**: Actualiza barra de estado
- Recibe mensajes de los paneles vía Consumer<String>
- Muestra feedback al usuario

---

## 🧩 COMPONENTES REUTILIZABLES (ui/components/)

### 🎨 **AppleStyler.java** - Utilidades de estilo (✨ NUEVO v1.4)
**Ubicación:** `src/ui/components/AppleStyler.java`

**Propósito:** Centralizar todos los estilos visuales de la aplicación

**COLORES:**
```java
// Colores principales
BLUE = #007AFF           // Botones primarios
BLUE_HOVER = #0064E6     // Hover azul
RED = #FF3B30            // Botones eliminar
RED_HOVER = #E62D23      // Hover rojo
GRAY = #8E8E93           // Elementos secundarios

// Fondos
BG_WHITE = #FFFFFF       // Paneles/cards
BG_LIGHT = #F8F8FA       // Fondo general
BG_SIDEBAR = #FAFAFC     // Barra lateral

// Texto
TEXT_BLACK = #000000     // Texto principal
TEXT_GRAY = #646469      // Texto secundario
TEXT_LIGHT = #96969B     // Texto terciario

// Bordes
BORDER = #E6E6EB         // Bordes sutiles
```

**FUENTES:**
```java
FONT_TITLE = Segoe UI Bold 24px      // Títulos principales
FONT_SUBTITLE = Segoe UI Regular 16px // Subtítulos
FONT_BODY = Segoe UI Regular 14px     // Texto normal
FONT_SMALL = Segoe UI Regular 12px    // Texto pequeño
FONT_BUTTON = Segoe UI Bold 14px      // Botones
```

**MÉTODOS DE ESTILIZACIÓN:**

**`styleButtonPrimary(JButton)`**: Botones azules
- Fondo azul sólido (#007AFF)
- Texto blanco
- Sin borde pintado
- Efecto hover (más oscuro)
- Cursor pointer
- Padding: 10px 20px

**`styleButtonSecondary(JButton)`**: Botones grises
- Fondo gris claro (#F8F8FA)
- Texto negro
- Borde gris sutil
- Efecto hover
- Padding: 9px 19px (ajustado por borde)

**`styleButtonDanger(JButton)`**: Botones rojos
- Fondo rojo sólido (#FF3B30)
- Texto blanco
- Sin borde pintado
- Efecto hover (más oscuro)

**`styleTextField(JTextField)`**: Campos de texto
- Borde gris (#E6E6EB)
- Padding interno: 8px 10px
- Fondo blanco
- Font: Segoe UI 14px

**`styleTable(JTable)`**: Tablas minimalistas
- Filas de 40px de altura
- Sin líneas de cuadrícula
- Selección azul transparente (30% opacity)
- Cabecera gris clara (#F8F8FA)
- Borde sutil
- Font: Segoe UI 14px

**`createCard()`**: Crea panel tipo tarjeta
- Fondo blanco
- Borde gris claro
- Padding: 20px
- Retorna JPanel configurado

**`applyTheme()`**: Aplica tema global
- Configura Look and Feel del sistema
- Establece fuentes por defecto
- Se llama una vez al inicio

**¿Cuándo modificar AppleStyler?**
- Cambiar colores globales: Edita las constantes de colores
- Añadir nuevo tipo de botón: Crea método `styleButtonXxx()`
- Cambiar fuentes: Modifica las constantes FONT_xxx
- Ajustar espaciados: Cambia los valores de padding en los métodos

---

### 📊 **AppleSidebar.java** - Barra lateral de navegación (✨ NUEVO v1.4)
**Ubicación:** `src/ui/components/AppleSidebar.java`

**Propósito:** Componente reutilizable para navegación lateral

**Características:**
- Ancho fijo: 200px
- Fondo gris claro (#FAFAFC)
- Borde derecho sutil
- Gestión automática de selección

**Métodos principales:**

**`addLogo(String text)`**: Añade logo/título
- Fuente: Segoe UI Bold 20px
- Padding: 25px 20px
- Alineación izquierda

**`addButton(String text, ActionListener action)`**: Añade botón de navegación
- Texto del botón
- Acción al hacer clic
- Gestión automática de selección visual
- Efecto hover (gris más claro)
- Selección en azul (#007AFF)
- Altura: 45px
- Retorna el JButton creado

**`addSpace(int height)`**: Añade espacio fijo
- Útil para separar secciones

**`addFlexibleSpace()`**: Añade espacio flexible
- Se expande para empujar elementos hacia abajo
- Útil para fijar botones al final (ej: "Sortir")

**`selectButton(JButton button)`**: Selecciona botón programáticamente
- Pone fondo azul y texto blanco
- Quita selección del botón anterior
- Se llama automáticamente al hacer clic

**Ejemplo de uso:**
```java
AppleSidebar sidebar = new AppleSidebar();
sidebar.addLogo("Facturacio");
sidebar.addSpace(10);
JButton btnHome = sidebar.addButton("Inici", e -> showCard("WELCOME"));
sidebar.addButton("Clients", e -> showCard("CLIENTS"));
sidebar.addFlexibleSpace();
sidebar.addButton("Sortir", e -> System.exit(0));
sidebar.selectButton(btnHome); // Selecciona inicial
```

---

## 📄 PANELES INDIVIDUALES (ui/panels/)

### 🏠 **WelcomePanel.java** - Pantalla de bienvenida (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/WelcomePanel.java`

**Propósito:** Pantalla inicial del sistema

**Componentes:**
- Icono grande: "$" (80px en azul)
- Título: "Sistema de Facturació" (24px Bold)
- Subtítulo: "Gestió de clients, articles i factures" (16px)
- Versión: "Versió 1.4" (12px)
- Botones de acceso rápido:
  - "Nou Client" (azul primario)
  - "Nova Factura" (gris secundario)

**Métodos:**

**`setOnNewClient(Runnable action)`**: Configura acción del botón cliente
**`setOnNewInvoice(Runnable action)`**: Configura acción del botón factura

**Layout:** GridBagLayout centrado sobre fondo gris claro

---

### 👥 **ClientsPanel.java** - Gestión de clientes (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ClientsPanel.java`

**Propósito:** Panel completo para gestionar clientes

**Constructor:**
```java
ClientsPanel(ClientService service, Consumer<String> statusUpdater)
```
- `service`: Para operaciones CRUD
- `statusUpdater`: Para actualizar barra de estado

**Componentes:**

**Header:**
- Título: "Clients" (24px Bold)
- Botones: "Nou" (azul), "Eliminar" (rojo), "Actualitzar" (gris)

**Tabla:**
- Columnas: DNI, Nom, Adreça, CP, Població, Província, Telèfon
- Estilo minimalista (40px filas, sin cuadrícula)
- Dentro de card blanco

**Formulario (oculto por defecto):**
- Card blanco con título "Dades del Client"
- 7 campos: DNI, Nom, Adreça, Població, CP, Província, Telèfon
- Botones: "Cancel·lar" (gris), "Desar" (azul)

**Métodos internos:**

**`buildUI()`**: Construye la interfaz
**`createForm()`**: Crea el formulario
**`showForm()`**: Muestra el formulario
**`hideForm()`**: Oculta el formulario
**`clearForm()`**: Limpia todos los campos
**`saveClient()`**: Valida y guarda cliente
**`deleteSelected()`**: Elimina cliente seleccionado con confirmación
**`refreshTable()`**: Recarga datos de la tabla

**Flujo de uso:**
1. Ver lista de clientes en tabla
2. Clic "Nou" → Formulario aparece abajo
3. Rellenar campos → Clic "Desar"
4. Validaciones + guardar + actualizar tabla
5. Mensaje en barra de estado

---

### 📦 **ArticlesPanel.java** - Gestión de artículos (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ArticlesPanel.java`

**Propósito:** Panel completo para gestionar artículos

**Constructor:**
```java
ArticlesPanel(ArticleService service, Consumer<String> statusUpdater)
```

**Estructura similar a ClientsPanel:**

**Tabla:**
- Columnas: Nom, Preu
- Estilo minimalista

**Formulario:**
- 2 campos: Nom, Preu
- Card blanco

**Métodos:**
- `saveArticle()`: Valida y guarda
- `deleteSelected()`: Elimina con confirmación
- `refreshTable()`: Actualiza lista

---

### 🧾 **InvoicesPanel.java** - Gestión de facturas (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/InvoicesPanel.java`

**Propósito:** Panel completo para crear y consultar facturas

**Constructor:**
```java
InvoicesPanel(InvoiceService invoiceService, ClientService clientService,
              ArticleService articleService, ConfigService configService,
              Consumer<String> statusUpdater)
```

**Componentes:**

**Búsqueda (parte superior):**
- Campo: ID de factura
- Botón: "Buscar"
- Muestra detalles en diálogo

**Tabla de facturas:**
- Columnas: ID, Data, DNI Client, IVA (%), Total
- Calcula totales automáticamente

**Formulario de nueva factura:**
- **Campo DNI cliente**
- **ComboBox de artículos**: Se carga automáticamente con `refreshArticlesCombo()`
- **Spinner cantidad**: 1-9999
- **Botón "Afegir Linia"**: Añade línea a lista temporal
- **JList de líneas**: Muestra líneas añadidas con formato
- **Botones:**
  - "Eliminar Linia" (rojo)
  - "Netejar Tot" (gris)
  - "Cancel·lar" (gris)
  - "Desar Factura" (azul)

**Métodos principales:**

**`showForm()`**: Muestra formulario y carga artículos
**`refreshArticlesCombo()`**: Carga artículos disponibles en el combo
**`addLine()`**: Añade línea a la lista temporal
- Valida que haya artículos
- Busca el artículo seleccionado
- Crea InvoiceLine
- Añade a `currentLines` y a la JList visual

**`removeLine()`**: Elimina línea seleccionada de la lista

**`saveInvoice()`**: Crea la factura
- Valida DNI no vacío
- Valida al menos 1 línea
- Valida máximo 10 líneas
- Verifica que el cliente existe
- Crea fecha actual (YYYY-MM-DD)
- Llama a `invoiceService.createInvoice(date, dni, lines, iva)`
- Calcula y muestra totales
- Actualiza tabla

**`searchInvoice()`**: Busca y muestra factura
- Busca por ID con `invoiceService.find(id)`
- Obtiene líneas con `invoiceService.findLines(id)`
- Calcula totales
- Muestra en diálogo con JTextArea

**`refreshInvoicesTable()`**: Actualiza tabla
- Lista todas con `invoiceService.listAll()`
- Para cada factura:
  - Obtiene líneas
  - Calcula total
  - Añade fila a la tabla

**Flujo crear factura:**
1. Clic "Nova Factura"
2. Introduce DNI del cliente
3. Selecciona artículo del combo
4. Indica cantidad
5. Clic "Afegir Linia" (repite para más líneas)
6. Clic "Desar Factura"
7. Sistema valida, crea factura, muestra totales

---

### ⚙️ **ConfigPanel.java** - Configuración (✨ NUEVO v1.4)
**Ubicación:** `src/ui/panels/ConfigPanel.java`

**Propósito:** Panel para configurar el IVA del sistema

**Constructor:**
```java
ConfigPanel(ConfigService service, Consumer<String> statusUpdater)
```

**Componentes:**
- Header con título "Configuració"
- Card centrado (400x200px) con:
  - Título: "IVA (%)"
  - Subtítulo explicativo
  - Campo de texto con valor actual
  - Botón "Desar" (azul)

**Métodos:**

**`saveConfig()`**: Guarda configuración
- Lee valor del campo
- Llama a `service.setIva(iva)`
- Valida que sea número válido
- Muestra mensaje de éxito o error

**Layout:** Card centrado en GridBagLayout sobre fondo gris claro

---

## 🎯 ARQUITECTURA v1.4

**Flujo de creación de UI:**
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

**Dependencias:**
```
AppleStyler (Independiente - solo constantes y métodos estáticos)
    ↑
    ├── AppleSidebar (Usa AppleStyler)
    ├── WelcomePanel (Usa AppleStyler)
    ├── ClientsPanel (Usa AppleStyler)
    ├── ArticlesPanel (Usa AppleStyler)
    ├── InvoicesPanel (Usa AppleStyler)
    ├── ConfigPanel (Usa AppleStyler)
    └── GuiUI (Orquesta todo)
```

---

## 📝 GUÍA DE MODIFICACIONES UI

### Cambiar colores globales:
**Archivo:** `AppleStyler.java`
```java
public static final Color BLUE = new Color(0, 122, 255); // Cambiar aquí
```

### Añadir nuevo panel:
1. Crear `MiPanel.java` en `ui/panels/`
2. Extender `JPanel`
3. Usar `AppleStyler` para estilos
4. En `GuiUI.java`:
   ```java
   MiPanel miPanel = new MiPanel(...);
   contentPanel.add(miPanel, "MI_PANEL");
   sidebar.addButton("Mi Panel", e -> showCard("MI_PANEL"));
   ```

### Añadir campo a tabla:
**Archivo:** Panel correspondiente (ej: `ClientsPanel.java`)
```java
tableModel = new DefaultTableModel(
    new String[]{"DNI", "Nom", "NuevoCampo"}, 0) // Añadir columna
```

### Cambiar tamaño de ventana:
**Archivo:** `GuiUI.java`
```java
frame.setSize(1300, 800); // Modificar aquí
```

### Personalizar botón sidebar:
**Archivo:** `AppleSidebar.java`
Modificar en el método `addButton()` los colores/fuentes

---

