package com.utp.ventacomputadoras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Anotaciones de Lombok para generar getters, setters, constructores, etc.
@Data
@NoArgsConstructor
@AllArgsConstructor
// Anotación JPA para indicar que esta clase es una entidad
@Entity
// Anotación para especificar el nombre de la tabla en la base de datos
@Table(name = "computadoras")
public class Computadora {
    
    // Identificador único de la computadora
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Marca de la computadora (ej: HP, Dell, Lenovo)
    @Column(nullable = false)
    private String marca;
    
    // Modelo específico de la computadora
    @Column(nullable = false)
    private String modelo;
    
    // Procesador de la computadora (ej: Intel Core i5, AMD Ryzen 7)
    @Column(nullable = false)
    private String procesador;
    
    // Cantidad de memoria RAM en GB
    @Column(nullable = false)
    private Integer memoriaRam;
    
    // Capacidad de almacenamiento en GB
    @Column(nullable = false)
    private Integer almacenamiento;
    
    // Tipo de almacenamiento (HDD o SSD)
    @Column(nullable = false)
    private String tipoAlmacenamiento;
    
    // Precio de la computadora
    @Column(nullable = false)
    private Double precio;
    
    // Cantidad disponible en stock
    @Column(nullable = false)
    private Integer stock;
}