package com.kevinvelasquez.InventorySystem.service;

import com.kevinvelasquez.InventorySystem.entity.Rol;
import com.kevinvelasquez.InventorySystem.repository.RolRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
  @Autowired
  private RolRepository rolRepository;

  public Rol saveRol(Rol rol) {
    try {
      return rolRepository.save(rol);
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al guardar el rol: " + e.getMessage());
    }
  }

  // Get all products
  public List<Rol> fetchAllRols() {
    try {
      return rolRepository.findAll();
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al encontrar todos los roles: " + e.getMessage());
    }
  }

  // Get a product by ID
  public Optional<Rol> fetchRolById(String id) {
    try {
      return rolRepository.findById(id);
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Failed to fetch product by ID: " + e.getMessage());
    }
  }

  public Optional<Rol> updateCatergoriaOptional(String id, Rol updatedRol) {
    try {
      Optional<Rol> existingRolOptional = rolRepository.findById(id);
      if (existingRolOptional.isPresent()) {
        Rol existingRol = existingRolOptional.get();
        existingRol.setRol(updatedRol.getRol());
        existingRol.setDescripcion(updatedRol.getDescripcion());
        Rol savedEntity = rolRepository.save(existingRol);
        return Optional.of(savedEntity);
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al actualizar el rol: " + e.getMessage());
    }
  }

  public boolean deleteRol(String id) {
    try {
      rolRepository.deleteById(id);
      return true; // Deletion successful
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al eliminar el rol: " + e.getMessage());
    }
  }
}
