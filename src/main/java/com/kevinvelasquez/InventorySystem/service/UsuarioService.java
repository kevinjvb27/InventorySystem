package com.kevinvelasquez.InventorySystem.service;

import com.kevinvelasquez.InventorySystem.entity.Usuario;
import com.kevinvelasquez.InventorySystem.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Fallo al obtener usuarios: " + e.getMessage());
    }
        
    }

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));
    }

    public Usuario createUsuario(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Fallo al guardar usuario: " + e.getMessage());
        }
    }

    public Usuario updateUsuario(String username, Usuario updatedUsuario) {
        try {
            Usuario oldUsuario = getUsuarioByUsername(username);
            oldUsuario.setNombre(updatedUsuario.getNombre());
            oldUsuario.setApellido(updatedUsuario.getApellido());
            if (updatedUsuario.getPassword() != null && !updatedUsuario.getPassword().isEmpty()) {
                oldUsuario.setPassword(passwordEncoder.encode(updatedUsuario.getPassword()));
            }
            oldUsuario.setCodigorol(updatedUsuario.getCodigorol());
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Fallo al actualizar el usuario: " + e.getMessage());
        }
    }

    public void deleteUsuario(String username) {
        try {
            Usuario usuario = getUsuarioByUsername(username);
            usuarioRepository.delete(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Fallo al eliminar el usuario: " + e.getMessage());
        }
    }
}