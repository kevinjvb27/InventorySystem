package com.kevinvelasquez.InventorySystem.service;

import com.kevinvelasquez.InventorySystem.entity.Usuario;
import com.kevinvelasquez.InventorySystem.repository.UsuarioRepository;
import com.kevinvelasquez.InventorySystem.security.JwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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

    public String login(Usuario usuario) {
        Usuario existingUser = usuarioRepository.findById(usuario.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(usuario.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtUtil.generateToken(existingUser.getUsername());
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
            return usuarioRepository.save(oldUsuario);
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