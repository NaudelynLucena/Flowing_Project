# FLOWING

[FlowingProject](https://github.com/NaudelynLucena/Flowing_Project/blob/dev/Flowing_img.png)

## Información y descripción general

### Objetivo Principal

Crear una aplicación que fomente el cuidado emocional, físico y mental de los usuarios.

### Plataforma de Desarrollo

Aplicación web desarrollada con Java Spring Boot en Visual Studio Code.

FLOWING es una aplicación orientada al cuidado de la salud mental y el bienestar integral. Su objetivo es proporcionar a los usuarios herramientas digitales para registrar su estado emocional, reflexionar sobre su día a día y recibir sugerencias personalizadas de actividades que puedan ayudarles a mejorar su bienestar o complementar su rutina diaria.

## Funcionalidades Detalladas

### 1. Autenticación y Registro

Registro de usuario: Formulario de registro que solicita nombre, correo y contraseña.
Inicio de sesión: Validación del correo y contraseña mediante Spring Security (Basic Auth).
Control de acceso: Solo los usuarios autenticados podrán acceder a las funcionalidades de la aplicación.

### 2. CRUD de Actividades

* Crear Actividad: Que el usuario ingrese el nombre, descripción y tipo de actividad.
* Leer Actividad: Visualización de la lista de actividades personalizadas.
* Actualizar Actividad: Opción de modificar los datos de una actividad previamente registrada.
* Eliminar Actividad: El usuario podrá borrar actividades de su lista personal.

### 3. Seguimiento Diario de Estado de Ánimo

* El usuario podrá registrar el estado emocional diario.
* La aplicación sugiere actividades relacionadas con el estado de ánimo.
* Los registros de los estados de ánimo se almacenan en la base de datos.

### 4. Diario de Reflexiones (Journaling)

* Escribir reflexión: El usuario podrá escribir sobre sus emociones, pensamientos y reflexiones.
* Historial de reflexiones: Los usuarios podrán ver un historial con las reflexiones de días anteriores.

### 5. Mensajes diarios

* Al iniciar la aplicación, se mostrará un mensaje aleatorio con una cita o frase celebre.
* Los mensajes estarán almacenados en la base de datos.

## Requisitos Técnicos

* Lenguaje de Programación y Framework:
Java21 con Spring Boot (para la creación de la API REST y la lógica del servidor).
* Entorno de Desarrollo:
Visual Studio Code (editor de texto) con extensiones de Java y Spring Boot.
* Base de Datos:
MySQL para almacenar usuarios, actividades, estados de ánimo, entradas de diario, etc.
* Seguridad:
Spring Security (Basic Auth) para proteger las rutas y garantizar la autenticación de los usuarios.
* Control de Versiones:
Se utilizará Git y GitHub para llevar un control de los cambios en el código.

## Diagrama UML

[FlowingER](https://github.com/NaudelynLucena/Flowing_Project/blob/dev/FlowingER.png)

[FlowingClases](https://github.com/NaudelynLucena/Flowing_Project/blob/dev/FlowingUML.png)

## Testing

[TestCoverage](https://github.com/NaudelynLucena/Flowing_Project/blob/dev/TestCoverage_Flowing.png)