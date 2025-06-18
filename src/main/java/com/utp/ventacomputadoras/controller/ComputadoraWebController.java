package com.utp.ventacomputadoras.controller;

import com.utp.ventacomputadoras.model.Computadora;
import com.utp.ventacomputadoras.service.ComputadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/computadoras")
public class ComputadoraWebController {

    @Autowired
    private ComputadoraService computadoraService;

    @GetMapping
    public String listarComputadoras(Model model) {
        model.addAttribute("computadoras", computadoraService.obtenerTodas());
        return "computadoras/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("computadora", new Computadora());
        return "computadoras/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Computadora> computadora = computadoraService.obtenerPorId(id);
        if (computadora.isPresent()) {
            model.addAttribute("computadora", computadora.get());
            return "computadoras/formulario";
        }
        return "redirect:/computadoras";
    }

    @PostMapping("/guardar")
    public String guardarComputadora(@ModelAttribute Computadora computadora, 
                                   RedirectAttributes redirectAttributes) {
        computadoraService.guardar(computadora);
        redirectAttributes.addFlashAttribute("mensaje", 
            "Computadora guardada exitosamente!");
        return "redirect:/computadoras";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarComputadora(@PathVariable Long id, 
                                    RedirectAttributes redirectAttributes) {
        computadoraService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", 
            "Computadora eliminada exitosamente!");
        return "redirect:/computadoras";
    }
}