# 💼 Sistema de Facturació

**Versión:** 1.4  
**Fecha:** Enero 2025

## 📋 Descripción

Sistema de gestión de facturación completo desarrollado en Java con interfaz gráfica Swing. Permite gestionar clientes, artículos y facturas con un diseño minimalista estilo Apple.

## ✨ Características

- **Gestión de Clientes** - CRUD completo (Crear, Leer, Actualizar, Eliminar)
- **Gestión de Artículos** - CRUD completo con validación de precios
- **Gestión de Facturas** - Creación de facturas con múltiples líneas
- **Configuración de IVA** - Configurable desde la interfaz
- **Interfaz Moderna** - Diseño minimalista estilo Apple
- **Persistencia en CSV** - Datos guardados en archivos de texto
- **Validaciones Robustas** - Protección contra datos inválidos

## 🚀 Ejecución Rápida

### Compilar
```bash
javac -encoding UTF-8 -d bin -sourcepath src src\Main.java
```

### Ejecutar Interfaz Gráfica
```bash
java -cp bin Main
```

### Ejecutar Interfaz de Consola
```bash
java -cp bin Main --console
```

## 📁 Estructura del Proyecto

```
ProgramaFacturacio/
├── src/
│   ├── Main.java                 # Punto de entrada
│   ├── model/                    # Modelos de datos
│   │   ├── Client.java
│   │   ├── Article.java
│   │   ├── Invoice.java
│   │   └── InvoiceLine.java
│   ├── repository/               # Acceso a datos (CSV)
│   │   ├── ClientRepository.java
│   │   ├── ArticleRepository.java
│   │   ├── InvoiceRepository.java
│   │   └── InvoiceLineRepository.java
│   ├── service/                  # Lógica de negocio
│   │   ├── ClientService.java
│   │   ├── ArticleService.java
│   │   ├── InvoiceService.java
│   │   └── ConfigService.java
│   ├── ui/                       # Interfaz de usuario
│   │   ├── GuiUI.java           # Orquestador gráfico
│   │   ├── ConsoleUI.java       # Interfaz de consola
│   │   ├── components/          # Componentes reutilizables
│   │   │   ├── AppleStyler.java
│   │   │   └── AppleSidebar.java
│   │   └── panels/              # Paneles individuales
│   │       ├── WelcomePanel.java
│   │       ├── ClientsPanel.java
│   │       ├── ArticlesPanel.java
│   │       ├── InvoicesPanel.java
│   │       └── ConfigPanel.java
│   └── util/
│       └── Validation.java      # Validaciones
├── data/                        # Archivos de datos
│   ├── clients.txt
│   ├── articles.txt
│   ├── factures.txt
│   ├── linies_factura.txt
│   └── config.txt
└── docs/                        # Documentación
    ├── README.md               # Guía técnica
    ├── DICCIONARIO_CODIGO.md  # Referencia completa
    └── UI_v1.4_COMPLETO.md    # Documentación de UI
```

## 🎨 Interfaz Gráfica

### Características de Diseño
- **Sidebar de navegación** (200px)
- **Diseño minimalista** estilo Apple
- **Colores suaves** (#007AFF, #FF3B30)
- **Tablas sin cuadrícula** (filas de 40px)
- **Botones con hover** y efectos visuales
- **Ventana de 1300x800px** (mínimo 1000x600px)

### Navegación
- **Inici** - Pantalla de bienvenida
- **Clients** - Gestión de clientes
- **Articles** - Gestión de artículos
- **Factures** - Crear y consultar facturas
- **Configuració** - Configurar IVA

## 📊 Formato de Datos

### clients.txt
```
DNI;Nombre;Dirección;Ciudad;CP;Provincia;Teléfono
12345678Z;Maria Serra;C/ Major 12;Palma;07001;Illes Balears;612345678
```

### articles.txt
```
Nombre;Precio
Bolígraf blau;0.45
Llibreta A5;2.50
```

### factures.txt
```
ID;Fecha;DNI_Cliente;IVA
F00001;2025-01-07;12345678Z;21
```

### linies_factura.txt
```
ID_Factura;Cantidad;Nombre_Artículo;Precio
F00001;3;Bolígraf blau;0.45
F00001;2;Llibreta A5;2.50
```

### config.txt
```
IVA=21
```

## 🔒 Validaciones

- **DNI:** Exactamente 9 caracteres
- **Teléfono:** Exactamente 9 dígitos
- **Código Postal:** Exactamente 5 dígitos
- **Precio:** Número decimal, máximo 6 caracteres
- **Cantidad:** Entre 1 y 9999
- **Caracteres prohibidos:** `;` (separador CSV)
- **Truncado automático:** Campos que exceden longitud máxima

## 🛠️ Requisitos

- **Java:** JDK 8 o superior
- **Sistema Operativo:** Windows, Linux, macOS
- **Memoria:** Mínimo 256 MB RAM
- **Espacio:** 10 MB

## 📚 Documentación

### Documentos Disponibles

1. **docs/README.md** - Guía técnica detallada
2. **docs/DICCIONARIO_CODIGO.md** - Referencia completa de todo el código
3. **docs/UI_v1.4_COMPLETO.md** - Documentación exhaustiva de la interfaz

### Guía Rápida de Uso

**Ver documentación técnica:**
```bash
# Abrir docs/README.md para guía de uso
# Abrir docs/DICCIONARIO_CODIGO.md para referencia del código
```

## 🎯 Arquitectura

**Patrón:** MVC (Model-View-Controller)

```
UI (View) → Service (Controller) → Repository → Data (CSV)
              ↓
           Validation
```

**Separación de responsabilidades:**
- **Model:** Solo datos (Article, Client, Invoice, InvoiceLine)
- **Repository:** Solo lectura/escritura de archivos
- **Service:** Lógica de negocio y validaciones
- **UI:** Solo visualización e interacción

## 🔄 Flujo de Trabajo

### Crear una Factura
1. Crear cliente (si no existe)
2. Crear artículos necesarios
3. Ir a "Factures" → "Nova Factura"
4. Introducir DNI del cliente
5. Seleccionar artículo del combo
6. Indicar cantidad
7. Clic "Afegir Linia" (repetir para más artículos)
8. Clic "Desar Factura"
9. Ver totales calculados automáticamente

## 🐛 Solución de Problemas

### La aplicación no arranca
```bash
# Verificar que Java está instalado
java -version

# Recompilar
javac -encoding UTF-8 -d bin -sourcepath src src\Main.java
```

### Errores de codificación
```bash
# Asegurarse de usar UTF-8
javac -encoding UTF-8 ...
```

### Datos no se guardan
- Verificar que existe la carpeta `data/`
- Verificar permisos de escritura
- Los archivos se crean automáticamente si no existen

## 📝 Licencia

Proyecto educativo - GSDAM 2025-26

## 👤 Autor

Desarrollado como proyecto de Desarrollo de Aplicaciones Multiplataforma

---

**Versión:** 1.4  
**Última actualización:** Noviembre 2025

