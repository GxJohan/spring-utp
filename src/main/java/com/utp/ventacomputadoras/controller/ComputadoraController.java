package com.utp.ventacomputadoras.controller;

import com.utp.ventacomputadoras.model.Computadora;
import com.utp.ventacomputadoras.service.ComputadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Anotación para indicar que esta clase es un controlador REST
@RestController
// Define la ruta base para todos los endpoints de este controlador
@RequestMapping("/api/computadoras")
// Lombok genera un constructor con todos los campos final
@RequiredArgsConstructor
public class ComputadoraController {
    
    // Inyección de dependencia del servicio
    private final ComputadoraService computadoraService;
    
    // GET: Obtener todas las computadoras
    @GetMapping
    public List<Computadora> obtenerTodas() {
        return computadoraService.obtenerTodas();
    }
    
    // GET: Obtener una computadora por ID
    @GetMapping("/{id}")
    public ResponseEntity<Computadora> obtenerPorId(@PathVariable Long id) {
        return computadoraService.obtenerPorId(id)
                .map(computadora -> ResponseEntity.ok(computadora))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // POST: Crear una nueva computadora
    @PostMapping
    public ResponseEntity<Computadora> crear(@RequestBody Computadora computadora) {
        Computadora nuevaComputadora = computadoraService.guardar(computadora);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaComputadora);
    }
    
    // PUT: Actualizar una computadora existente
    @PutMapping("/{id}")
    public ResponseEntity<Computadora> actualizar(@PathVariable Long id, 
                                                  @RequestBody Computadora computadora) {
        Computadora computadoraActualizada = computadoraService.actualizar(id, computadora);
        if (computadoraActualizada != null) {
            return ResponseEntity.ok(computadoraActualizada);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE: Eliminar una computadora
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (computadoraService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}