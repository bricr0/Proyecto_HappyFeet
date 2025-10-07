# 🐾 Sistema de Gestión Veterinaria – Happy Feet

## 📌 Descripción del Proyecto
El sistema **Happy Feet** es una aplicación de escritorio desarrollada para la gestión integral de una veterinaria.  
Su objetivo principal es facilitar el registro, administración y consulta de la información relacionada con los dueños, pacientes (mascotas), citas médicas y servicios veterinarios.  

Este proyecto resuelve la necesidad de la veterinaria de contar con un sistema confiable que permita centralizar los datos y optimizar los procesos administrativos, evitando errores comunes derivados del manejo manual.
## CAMBIOS EN TEMA DE LA RAMA EXAMEN !
Ya no hace factura por Txt, si no por Consola
---

## ⚙️ Tecnologías Utilizadas
- **Java 21** – Lenguaje principal de desarrollo.  
- **MySQL 9** – Sistema de gestión de base de datos relacional.  
- **JDBC (Java Database Connectivity)** – Conexión entre Java y MySQL.  
- **Maven** – Herramienta de gestión de dependencias y construcción del proyecto.  
- **Dotenv Java** – Manejo de variables de entorno para credenciales de base de datos.  

---

## 🛠️ Funcionalidades Implementadas
El sistema cuenta con los siguientes módulos principales:  

1. **Gestión de Dueños**
   - Registro, consulta, actualización y eliminación de dueños.  
   - Asociación de dueños con sus mascotas.  

2. **Gestión de Pacientes (Mascotas)**
   - Registro de nuevas mascotas.  
   - Consulta de historial médico.  
   - Actualización y eliminación de registros.  

3. **Gestión de Citas Médicas**
   - Programación de citas.  
   - Consulta de citas activas y pasadas.  
   - Cancelación de citas.  

4. **Gestión de Servicios Médicos**
   - Registro de servicios disponibles (vacunación, desparasitación, cirugías, etc.).  
   - Asignación de servicios a mascotas.  

5. **Menú de Navegación Interactivo**
   - Interfaz por consola organizada en menús.  
   - Fácil navegación entre los distintos módulos.  

---

## 🗄️ Modelo de la Base de Datos
La base de datos contiene las siguientes tablas principales:  
- **Dueños**: Información de los propietarios de las mascotas.  
- **Pacientes**: Registro de las mascotas.  
- **Citas**: Agenda de las consultas veterinarias.  
- **Servicios**: Catálogo de servicios médicos veterinarios.  

📌 Ejemplo de relaciones:  
- Un **dueño** puede tener varias **mascotas**.  
- Una **mascota** puede tener varias **citas**.  
- Una **cita** puede incluir uno o varios **servicios**.  

## 🚀 Instrucciones de Instalación y Ejecución

### 🔹 Requisitos Previos
- **JDK 21 o superior** instalado y configurado.  
- **Maven** instalado.  
- **MySQL Server** en ejecución.  

### 🔹 Clonación del Proyecto
```bash
git clone https://github.com/bricr0/Proyecto_HappyFeet
cd HappyFeet
```
### Configurar el archivo .env

- En la raíz del proyecto crear un archivo llamado .env con los datos de conexión:
```
DB_HOST=jdbc:mysql://localhost:3306/
DB_USER=tu_usuario
DB_PASSWORD=tu_password
DB_NAME=happyfeet
```
### 🗄️ Ejecución de Scripts

- Ejecutar en orden los scripts disponibles en la carpeta /sql:
```
source schema.sql;
source data.sql;
```

### 📖 Guía de Uso

Al iniciar la aplicación, se muestra el menú principal.

Desde allí se puede acceder a:

- Gestión de dueños.

- Gestión de pacientes.

- Gestión de citas.

- Gestión de servicios médicos.

Cada módulo cuenta con opciones de crear, consultar, actualizar y eliminar registros.

### 👨‍💻 Autor(es)

Estudiante 1 – Juan Sebastian Martinez Tapias

Estudiante 2 – Brian Claros Viola
