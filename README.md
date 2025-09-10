# Gestor de Personas(Backend)🚀

Este es un proyecto de backend para gestionar personas utilizando Java, SpringBoot y Postgresql. Proporciona una API
RESTful para crear, leer, actualizar y eliminar registros de personas.

## Tecnologías Utilizadas

- **Java**
- **SpringBoot**
- **Postgresql**
- **Maven**
- **JPA/Hibernate**
- **Spring Data JPA**
- **Spring Web**
- **validation**

---

## 📦 Instalación y Ejecución Local

### 🔗 1. Clonar y configurar el entorno

⚡ Ejecuta el siguiente comando para clonar un repositorio **Git remoto**:

```bash
git clone https://github.com/jhoan636/person-crud.git
````

⚡ Navega al directorio del proyecto clonado:

```bash
cd person-crud
```

⚡ Asegúrate de tener Java y Maven instalados en tu máquina. Puedes verificarlo con:

```bash
java -version
mvn -version
```

⚡ Configura la base de datos PostgreSQL. Asegúrate de tener PostgreSQL instalado y en ejecución. Crea una base de datos
llamada `persondb` y actualiza las credenciales en el archivo `src/main/resources/application.properties` si es
necesario.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/persondb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 🚀 2. Ejecutar la aplicación

⚡ Compila y ejecuta la aplicación utilizando Maven:

```bash
mvn spring-boot:run
```

⚡ La aplicación debería estar corriendo en `http://localhost:8080`.

