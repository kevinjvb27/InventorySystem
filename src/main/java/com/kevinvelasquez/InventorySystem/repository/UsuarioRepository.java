package com.kevinvelasquez.InventorySystem.repository;

import com.kevinvelasquez.InventorySystem.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}