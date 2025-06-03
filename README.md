# 🎓 Proyecto Bootcamp - MS-PRODUCT

Este proyecto forma parte del **Bootcamp "Microservicios con Java"** dictado en la plataforma **Código Facilito**.  
El objetivo principal es aplicar los conocimientos adquiridos para desarrollar un sistema basado en microservicios, cumpliendo con buenas prácticas de arquitectura, separación de responsabilidades y escalabilidad.

---


# 🧩 MS-PRODUCT - Microservicio de Gestión de Productos y Categorías

## 📋 Descripción

**MS-PRODUCT** es un microservicio encargado de gestionar productos y sus categorías.  
Forma parte de una arquitectura de microservicios y expone un conjunto de endpoints RESTful para realizar operaciones de alta, baja, modificación y consulta (ABM).

Este servicio es consumido por otros microservicios como `catalog-bff`, y permite mantener organizada la información de productos para un sistema de catálogo.

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- JPA / Hibernate
- Spring Security
- Springdoc OpenAPI 3.0
- Lombok
- Actuator

---

## 📦 Endpoints disponibles

### Productos

- `GET /api/productos` → Listar todos los productos
- `GET /api/productos/{id}` → Obtener producto por ID
- `POST /api/productos` → Crear nuevo producto
- `PUT /api/productos/{id}` → Actualizar producto existente
- `DELETE /api/productos/{id}` → Eliminar producto (lógico)

### Categorías

- `GET /api/categorias` → Listar todas las categorías
- `GET /api/categorias/{id}` → Obtener categoría por ID
- `POST /api/categorias` → Crear nueva categoría
- `PUT /api/categorias/{id}` → Actualizar categoría existente
- `DELETE /api/categorias/{id}` → Eliminar categoría (lógica)

---

## 🔧 Variables de entorno requeridas

Para conectar correctamente la base de datos y configurar la autenticación, asegurate de definir las siguientes variables:

```env
DB_USERNAME=postgres
DB_PASSWORD=password
DB_URL=jdbc:postgresql://localhost:5432/ms_product

SECURITY_USERNAME=auth_username
SECURITY_PASSWORD=auth_password
```

## 🐳 Cómo levantar el proyecto con Docker
### 1. Clonar el repositorio
- git clone https://github.com/leojm27/JAVA-bootcamp-ms-product.git
- cd JAVA-bootcamp-ms-product
### 2. Construir el JAR y la imagen Docker
- desde el IDE y a maven/gradle -> clean, -> package
- docker build -t ms-product:v1 .

### 3. Ejecutar con Docker Compose
- docker compose up -d

## 🌐 Acceso
Una vez levantado el contenedor, accedé al servicio MS-PRODUCT desde:
http://localhost:8082

Si tenés habilitada la documentación Swagger:
http://localhost:8082/swagger-ui.html

