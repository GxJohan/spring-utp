# Pruebas API REST - Venta de Computadoras

## Cómo ejecutar la aplicación

1. Navegar a la carpeta del proyecto:
   ```bash
   cd /home/gxjohan/projects/dwi/spring-utp
   ```

2. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

3. La aplicación estará disponible en: `http://localhost:8080`

4. Consola H2 (para ver la base de datos): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:ventacomputadorasdb`
   - Username: `sa`
   - Password: (dejar vacío)

## Endpoints disponibles

### 1. Obtener todas las computadoras
- **Método:** GET
- **URL:** `http://localhost:8080/api/computadoras`
- **Respuesta esperada:** Lista de computadoras (inicialmente vacía)

### 2. Crear una nueva computadora
- **Método:** POST
- **URL:** `http://localhost:8080/api/computadoras`
- **Headers:** `Content-Type: application/json`
- **Body (ejemplo):**
```json
{
    "marca": "Dell",
    "modelo": "Inspiron 15 3000",
    "procesador": "Intel Core i5-1135G7",
    "memoriaRam": 8,
    "almacenamiento": 512,
    "tipoAlmacenamiento": "SSD",
    "precio": 899.99,
    "stock": 15
}
```

### 3. Obtener una computadora por ID
- **Método:** GET
- **URL:** `http://localhost:8080/api/computadoras/{id}`
- **Ejemplo:** `http://localhost:8080/api/computadoras/1`

### 4. Actualizar una computadora
- **Método:** PUT
- **URL:** `http://localhost:8080/api/computadoras/{id}`
- **Headers:** `Content-Type: application/json`
- **Body (ejemplo):**
```json
{
    "marca": "Dell",
    "modelo": "Inspiron 15 3000",
    "procesador": "Intel Core i5-1135G7",
    "memoriaRam": 16,
    "almacenamiento": 1000,
    "tipoAlmacenamiento": "SSD",
    "precio": 1099.99,
    "stock": 10
}
```

### 5. Eliminar una computadora
- **Método:** DELETE
- **URL:** `http://localhost:8080/api/computadoras/{id}`
- **Ejemplo:** `http://localhost:8080/api/computadoras/1`

## Ejemplos adicionales para pruebas

### Computadora Gaming
```json
{
    "marca": "MSI",
    "modelo": "GF65 Thin",
    "procesador": "Intel Core i7-10750H",
    "memoriaRam": 16,
    "almacenamiento": 512,
    "tipoAlmacenamiento": "SSD",
    "precio": 1299.99,
    "stock": 5
}
```

### Computadora de Oficina
```json
{
    "marca": "HP",
    "modelo": "ProDesk 400 G7",
    "procesador": "Intel Core i3-10100",
    "memoriaRam": 4,
    "almacenamiento": 256,
    "tipoAlmacenamiento": "SSD",
    "precio": 499.99,
    "stock": 20
}
```

### Computadora Workstation
```json
{
    "marca": "Lenovo",
    "modelo": "ThinkStation P340",
    "procesador": "Intel Xeon W-1250",
    "memoriaRam": 32,
    "almacenamiento": 1000,
    "tipoAlmacenamiento": "SSD",
    "precio": 2499.99,
    "stock": 3
}
```

## Códigos de respuesta HTTP esperados

- **200 OK**: Operación exitosa (GET, PUT)
- **201 Created**: Recurso creado exitosamente (POST)
- **204 No Content**: Eliminación exitosa (DELETE)
- **404 Not Found**: Recurso no encontrado