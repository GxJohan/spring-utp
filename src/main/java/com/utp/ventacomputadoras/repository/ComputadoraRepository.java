package com.utp.ventacomputadoras.repository;

import com.utp.ventacomputadoras.model.Computadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Anotación para indicar que esta interfaz es un repositorio
@Repository
public interface ComputadoraRepository extends JpaRepository<Computadora, Long> {
    // JpaRepository proporciona métodos CRUD básicos:
    // - save(): guardar o actualizar
    // - findById(): buscar por ID
    // - findAll(): obtener todos los registros
    // - deleteById(): eliminar por ID
    // - count(): contar registros
    // Y muchos más...
    
    // Spring Data JPA implementará automáticamente esta interfaz
}