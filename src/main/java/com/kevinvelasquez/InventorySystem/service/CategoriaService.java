package com.kevinvelasquez.InventorySystem.service;

import com.kevinvelasquez.InventorySystem.entity.Categoria;
import com.kevinvelasquez.InventorySystem.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository categoriaRepository;

  public Categoria saveCategoria(Categoria categoria) {
    try {
      return categoriaRepository.save(categoria);
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al guardar categoria: " + e.getMessage());
    }
  }

  // Get all products
  public List<Categoria> fetchAllCategorias() {
    try {
      return categoriaRepository.findAll();
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al encontrar todas las categorias: " + e.getMessage());
    }
  }

  // Get a product by ID
  public Optional<Categoria> fetchCategoriaById(String id) {
    try {
      return categoriaRepository.findById(id);
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Failed to fetch product by ID: " + e.getMessage());
    }
  }

  public Optional<Categoria> updateCatergoriaOptional(String id, Categoria updatedCategoria) {
    try {
      Optional<Categoria> existingCategoriaOptional = categoriaRepository.findById(id);
      if (existingCategoriaOptional.isPresent()) {
        Categoria existingCategoria = existingCategoriaOptional.get();
        existingCategoria.setCategoria(updatedCategoria.getCategoria());
        existingCategoria.setDescripcion(updatedCategoria.getDescripcion());
        Categoria savedEntity = categoriaRepository.save(existingCategoria);
        return Optional.of(savedEntity);
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al actualizar la categoria: " + e.getMessage());
    }
  }

  public boolean deleteCategoria(String id) {
    try {
      categoriaRepository.deleteById(id);
      return true; // Deletion successful
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al eliminar la categoria: " + e.getMessage());
    }
  }
}
