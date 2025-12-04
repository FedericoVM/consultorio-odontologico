# 🦷 Sistema de Gestión para Consultorio Odontológico

Este proyecto es el **Trabajo Práctico Integrador** desarrollado como parte de un curso de **Java Web**, demostrando la aplicación de tecnologías clave en una arquitectura Cliente-Servidor.

---

## 🌟 Conceptos del Curso Aplicados

El diseño y la funcionalidad del sistema se basan en los siguientes pilares de la programación web con Java:

### 1. ⚙️ Arquitectura y Control

**Arquitectura Cliente-Servidor:**  
El sistema se comunica mediante peticiones HTTP entre el navegador (cliente) y el Servidor Apache Tomcat.

**Servlets (Controladores):**  
Los Servlets actúan como el punto de entrada y control de la lógica, gestionando las peticiones y respuestas.

**Métodos HTTP:**  
Implementación de `doGet` (para traer datos, como la lista de odontólogos) y `doPost` (para enviar datos de formularios, como el alta) del Protocolo HTTP.

**Manejo de HTTP Sessions:**  
Implementación del sistema de Login y uso de `HttpSession` para mantener el estado del usuario logeado, un tema cubierto en la Parte 5 del curso.

**Paso de Parámetros:**  
Uso de `request.getParameter()` dentro de los Servlets para capturar datos enviados desde el cliente.

---

### 2. 🖥️ Vista y Dinamismo

**JSP (Java Server Pages):**  
Utilizado para generar el contenido HTML de forma dinámica, cumpliendo con el objetivo de separar la lógica de la presentación.

**Etiquetas JSP:**  
Empleadas para incluir código Java (scriptlets) directamente en el HTML.

**Componentes de Frontend:**  
Uso de JSP para estructurar y enlazar componentes de la interfaz de usuario.

---

### 3. 💾 Persistencia de Datos

**JPA (Java Persistence API) e Hibernate:**  
Utilizado para mapear clases de Java a la base de datos.

**Herencia con JPA:**  
Configuración del modelo de datos para gestionar la herencia de entidades (ej. `Persona`), según la Parte 2 del trabajo práctico.

**Operaciones CRUD:**  
Implementación de las siguientes funcionalidades a través de JSP + JPA:

- **CREATE:** Alta de nuevos registros (Odontólogos, Pacientes).  
- **READ:** Lectura y listado de registros.  
- **DELETE & UPDATE:** Odontologos, pacientes, turnos y usuarios.

---

## 💻 Tecnologías Utilizadas

- **Backend:** Java Servlet / JSP  
- **Persistencia:** JPA con Hibernate  
- **Gestor de Dependencias:** Maven  
- **Servidor de Aplicaciones:** Apache Tomcat  
- **Base de Datos:** *[Menciona tu base de datos: MySQL, etc.]*  
- **Frontend:** HTML, CSS, Bootstrap (SB Admin 2)

---

## 🚀 Instalación y Despliegue

### 1. Requisitos Previos
- JDK  
- Apache Tomcat (Servidor de Aplicaciones)  
- IDE (NetBeans, Eclipse, o IntelliJ)  
- Sistema de Gestión de Bases de Datos (ej: MySQL)

### 2. Configuración de la Base de Datos
1. Crea la base de datos `clinica_odontologica`.  
2. Verifica que las entidades y la configuración de persistencia (`persistence.xml`) sean correctas para que JPA pueda crear las tablas o mapear las existentes.

### 3. Despliegue con Maven
1. Clona el repositorio.  
2. Abre el proyecto en tu IDE.  
3. Utiliza las herramientas de Maven para construir y desplegar el proyecto en el servidor Tomcat.

### 4. Acceso
El sistema estará disponible en:
http://localhost:8080/[nombre-de-tu-proyecto]/login.jsp

## 👤 Credenciales de Prueba (Login)

*usuario: Secretario  
contraseña: secretario123*

## 📸 Capturas
<img width="1075" height="518" alt="login" src="https://github.com/user-attachments/assets/2c83f128-95cf-4e2d-aad6-fbdf90367028" />

<img width="1347" height="646" alt="Panel-principal" src="https://github.com/user-attachments/assets/6aee0e6e-cef7-4414-8c4f-79bc934a7a12" />

<img width="1347" height="646" alt="Panel-principal" src="https://github.com/user-attachments/assets/b662e31d-208f-4bae-9fff-e624c659226a" />

<img width="1351" height="649" alt="Panel-Odontolgos" src="https://github.com/user-attachments/assets/03bcee84-2b25-4e5d-ad30-734c07f219e1" />

<img width="434" height="470" alt="crear-odontologo" src="https://github.com/user-attachments/assets/3d4c8bc0-5306-4288-bf78-b0ee80610d16" />

<img width="1365" height="563" alt="Panel-pacientes" src="https://github.com/user-attachments/assets/052d2a91-3c3c-437f-a798-bd6733ebdb02" />

<img width="1138" height="572" alt="Panel-turnos" src="https://github.com/user-attachments/assets/9e9bd3f7-c5eb-43f0-963f-7d535fce921a" />

<img width="1120" height="573" alt="crear-usuario" src="https://github.com/user-attachments/assets/44805105-b7df-4b8b-a1d6-7079084bb859" />

<img width="730" height="635" alt="crear-paciente" src="https://github.com/user-attachments/assets/b5b5a359-681c-4dbe-aef7-edaf6d62c6f4" />

