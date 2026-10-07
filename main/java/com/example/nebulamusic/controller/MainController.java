package com.example.nebulamusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import com.example.nebulamusic.model.Usuario;
import com.example.nebulamusic.service.UsuarioService;

@Controller
public class MainController {
    private final UsuarioService usuarioService;

    public MainController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping({ "/index", "/index.html" })
    public String index() {
        return "index";
    }

    @GetMapping({ "/", "/iniciar-sesion", "/iniciarSesion", "/iniciarSesion.html" })
    public String iniciarSesion() {
        return "iniciar-sesion";
    }

    @GetMapping({ "/registro", "/registrarUsuario", "/registrarUsuario.html" })
    public String registrarUsuario() {
        return "registro";
    }

    @GetMapping({ "/error", "error.html" })
    public String error() {
        return "error";
    }

    @PostMapping("/crear-cuenta")
    public String crearCuenta(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.existeUsuario(usuario.getCorreo())) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "redirect:/error";
        }
        usuarioService.registrarUsuario(usuario);
        return "redirect:/iniciar-sesion";
    }

    @PostMapping("/autenticacion")
    public String autenticar(@RequestParam String correo, @RequestParam String contrasenia, Model model) {
        if (usuarioService.validarCredenciales(correo, contrasenia)) {
            return "redirect:/index";
        }
        return "redirect:/error";

    }

    @GetMapping({ "/cerrarSesion", "/cerrar-sesion" })
    public String cerrarSesion() {
        return "redirect:/iniciar-sesion";
    }
}