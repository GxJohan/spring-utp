package com.utp.ventacomputadoras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotación principal que combina:
// @Configuration: Indica que es una clase de configuración
// @EnableAutoConfiguration: Habilita la configuración automática de Spring Boot
// @ComponentScan: Escanea componentes en el paquete actual y subpaquetes
@SpringBootApplication
public class VentacomputadorasApplication {

    public static void main(String[] args) {
        // Método que inicia la aplicación Spring Boot
        SpringApplication.run(VentacomputadorasApplication.class, args);
    }

}