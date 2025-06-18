# Instrucciones para Demostrar las Dos Alternativas de UI

## Preparación

1. **Iniciar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```
   La aplicación se ejecutará en el puerto 8081.

2. **Abrir el navegador:**
   Ir a http://localhost:8081

## Demostración

### 1. Página de Inicio
- Muestra las dos opciones disponibles
- Explica brevemente cada enfoque
- Enlaces directos a cada implementación

### 2. Opción 1: Thymeleaf (MVC Tradicional)

**URL:** http://localhost:8081/computadoras

**Características a demostrar:**
- **Listar computadoras:** La página carga con todas las computadoras
- **Crear nueva:** Click en "Nueva Computadora"
  - Llena el formulario
  - Al guardar, la página se recarga mostrando el mensaje de éxito
- **Editar:** Click en "Editar" en cualquier fila
  - Modifica los datos
  - Al guardar, regresa a la lista con mensaje de confirmación
- **Eliminar:** Click en "Eliminar"
  - Confirma la acción
  - La página se recarga sin el elemento eliminado

**Puntos clave para explicar:**
- Cada acción recarga la página completa
- El servidor genera el HTML completo
- Los datos vienen procesados desde el servidor
- URLs cambian con cada navegación

### 3. Opción 2: HTML/JS + API REST

**URL:** http://localhost:8081/static.html

**Características a demostrar:**
- **Listar computadoras:** JavaScript carga los datos via AJAX
- **Crear nueva:** Click en "Nueva Computadora"
  - Se abre un modal sin recargar la página
  - Al guardar, la tabla se actualiza dinámicamente
- **Editar:** Click en "Editar"
  - El modal se llena con los datos actuales
  - La actualización es instantánea sin recarga
- **Eliminar:** Click en "Eliminar"
  - La fila desaparece sin recargar la página

**Puntos clave para explicar:**
- La página nunca se recarga
- JavaScript consume la API REST en `/api/computadoras`
- Los datos se actualizan dinámicamente en el DOM
- Mejor experiencia de usuario (más fluida)

## Comparación Técnica

### Thymeleaf (Server-Side Rendering)
```java
@Controller
@RequestMapping("/computadoras")
public class ComputadoraWebController {
    @GetMapping
    public String listarComputadoras(Model model) {
        model.addAttribute("computadoras", computadoraService.obtenerTodas());
        return "computadoras/lista";
    }
}
```

### API REST (Client-Side Rendering)
```java
@RestController
@RequestMapping("/api/computadoras")
public class ComputadoraController {
    @GetMapping
    public List<Computadora> obtenerTodas() {
        return computadoraService.obtenerTodas();
    }
}
```

## Herramientas de Desarrollo

### 1. Ver la base de datos H2:
- URL: http://localhost:8081/h2-console
- JDBC URL: `jdbc:h2:mem:ventacomputadorasdb`
- Usuario: `sa`
- Contraseña: (dejar en blanco)

### 2. Ver las peticiones en DevTools:
- Abrir Chrome DevTools (F12)
- Ir a la pestaña Network
- En Thymeleaf: ver las recargas de página
- En HTML/JS: ver las peticiones AJAX a la API

### 3. Probar la API directamente:
```bash
# Listar todas
curl http://localhost:8081/api/computadoras

# Crear nueva
curl -X POST http://localhost:8081/api/computadoras \
  -H "Content-Type: application/json" \
  -d '{"marca":"HP","modelo":"Pavilion","procesador":"Intel i5","memoriaRam":8,"almacenamiento":512,"tipoAlmacenamiento":"SSD","precio":899.99,"stock":10}'
```

## Ventajas y Desventajas

### Thymeleaf
**Ventajas:**
- Más simple de entender para principiantes
- SEO friendly
- Funciona sin JavaScript
- Menor complejidad inicial

**Desventajas:**
- Recargas de página constantes
- Mayor consumo de ancho de banda
- Experiencia de usuario menos fluida

### HTML/JS + API REST
**Ventajas:**
- Experiencia de usuario moderna
- Menor consumo de recursos del servidor
- Puede funcionar como PWA
- Separación clara frontend/backend

**Desventajas:**
- Requiere conocimientos de JavaScript
- Mayor complejidad inicial
- Posibles problemas de SEO
- Requiere manejo de estados en el cliente