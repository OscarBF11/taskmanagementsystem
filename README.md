# HELP.md

### Descripción del Programa
Este programa es un sistema de gestión de tareas que permite a los usuarios crear, editar y eliminar tareas, así como actualizar su estado. Cada usuario tiene sus tareas únicas y separadas de los demás usuarios. Además, los administradores pueden crear, editar y eliminar usuarios, pero no pueden ver las tareas de otros usuarios.
Pd: Este desarrollo se ha llevado a cabo en un corto período de tiempo por lo que podria contener errores de funcionamiento o de estructura así como código huérfano.

### Arquitectura
El sistema se basa en una arquitectura de cliente-servidor, donde el cliente interactúa con la aplicación a través de una interfaz web y el servidor procesa las solicitudes y gestiona la base de datos.

### Librerías Utilizadas
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Web
- Thymeleaf

### Funciones
- Crear, editar y eliminar tareas.
- Actualizar el estado de las tareas.
- Las tareas son únicas y separadas para cada usuario.
- Crear, editar y eliminar usuarios (Administrador). El administrador no puede ver las tareas de otros usuarios.

### Pre-configuración
Antes de ejecutar la aplicación, es necesario configurar una base de datos PostgreSQL y un usuario para la aplicación. Abre la terminal y ejecuta los siguientes comandos:
```
sudo -u postgres psql
create database nombre_base_datos;
create user nombre_usuario with encrypted password 'contraseña';
GRANT USAGE, CREATE ON SCHEMA public TO usuario;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO usuario;
```
Para conectarte a PostgreSQL, usa el comando `psql -U postgres`. Para listar usuarios y roles, usa el comando `\du`.

### Documentación de Referencia
Para obtener más información, consulta las siguientes secciones:
- [Documentación oficial de Apache Maven](https://maven.apache.org/guides/index.html)
- [Guía de referencia del plugin de Maven para Spring Boot](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/)
- [Crear una imagen OCI](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/#build-image)
- [Spring Security](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web.security)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
- [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web)
- [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)

### Guías
Las siguientes guías ilustran cómo utilizar algunas funciones concretamente:
- [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
- [Spring Boot y OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
- [Autenticación de un usuario con LDAP](https://spring.io/guides/gs/authenticating-ldap/)
- [Acceso a datos con JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Construcción de un servicio web RESTful](https://spring.io/guides/gs/rest-service/)
- [Servir contenido web con Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Construcción de servicios REST con Spring](https://spring.io/guides/tutorials/rest/)
- [Manejo de envíos de formularios](https://spring.io/guides/gs/handling-form-submission/)