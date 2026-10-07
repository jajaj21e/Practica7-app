package com.example.nebulamusic.service;

import com.example.nebulamusic.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio Java para la gestión de usuarios - Nébula Music
 */
@Service
public class UsuarioService {

    private final Map<String, Usuario> usuarios = new ConcurrentHashMap<>();

    public UsuarioService() {
        // Inicialización del mapa de usuarios
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario != null && usuario.getCorreo() != null) {
            usuarios.put(usuario.getCorreo(), usuario);
        }
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getCorreo() == null) {
            return false;
        }
        usuarios.put(usuario.getCorreo(), usuario);
        return true;
    }

    public boolean existeUsuario(String correo) {
        if (correo == null) return false;
        return usuarios.containsKey(correo);
    }

    public boolean validarCredenciales(String correo, String contrasenia) {
        if (correo == null || contrasenia == null) return false;
        Usuario usuario = usuarios.get(correo);
        return usuario != null && contrasenia.equals(usuario.getContrasenia());
    }

    public Optional<Usuario> obtenerPorCorreo(String correo) {
        if (correo == null) return Optional.empty();
        return Optional.ofNullable(usuarios.get(correo));
    }

    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios.values());
    }
}
