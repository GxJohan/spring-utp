package com.utp.ventacomputadoras.service;

import com.utp.ventacomputadoras.model.Computadora;
import com.utp.ventacomputadoras.repository.ComputadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Anotación para indicar que esta clase es un servicio
@Service
// Lombok genera un constructor con todos los campos final
@RequiredArgsConstructor
public class ComputadoraService {
    
    // Inyección de dependencia del repositorio
    private final ComputadoraRepository computadoraRepository;
    
    // Método para obtener todas las computadoras
    public List<Computadora> obtenerTodas() {
        return computadoraRepository.findAll();
    }
    
    // Método para obtener una computadora por su ID
    public Optional<Computadora> obtenerPorId(Long id) {
        return computadoraRepository.findById(id);
    }
    
    // Método para guardar una nueva computadora
    public Computadora guardar(Computadora computadora) {
        return computadoraRepository.save(computadora);
    }
    
    // Método para actualizar una computadora existente
    public Computadora actualizar(Long id, Computadora computadoraActualizada) {
        // Verificar si la computadora existe
        if (computadoraRepository.existsById(id)) {
            computadoraActualizada.setId(id);
            return computadoraRepository.save(computadoraActualizada);
        }
        return null; // Si no existe, retorna null
    }
    
    // Método para eliminar una computadora
    public boolean eliminar(Long id) {
        // Verificar si la computadora existe antes de eliminar
        if (computadoraRepository.existsById(id)) {
            computadoraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}