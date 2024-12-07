package com.kevinvelasquez.InventorySystem.service;

import com.kevinvelasquez.InventorySystem.entity.Producto;
import com.kevinvelasquez.InventorySystem.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
  @Autowired
  private ProductoRepository productoRepository;

  public Producto saveProducto(Producto producto) {
    try {
      return productoRepository.save(producto);
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al guardar el producto: " + e.getMessage());
    }
  }

  // Get all products
  public List<Producto> fetchAllProductos() {
    try {
      return productoRepository.findAll();
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al encontrar todas los productos: " +
          e.getMessage());
    }
  }

  // Get a product by ID
  public Optional<Producto> fetchProductoById(String id) {
    try {
      return productoRepository.findById(id);
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Failed to fetch product by ID: " +
          e.getMessage());
    }
  }

  public Optional<Producto> updateCatergoriaOptional(String id, Producto updatedProducto) {
    try {
      Optional<Producto> existingProductoOptional = productoRepository.findById(id);
      if (existingProductoOptional.isPresent()) {
        Producto existingProducto = existingProductoOptional.get();
        existingProducto.setProducto(updatedProducto.getProducto());
        existingProducto.setDescripcion(updatedProducto.getDescripcion());
        Producto savedEntity = productoRepository.save(existingProducto);
        return Optional.of(savedEntity);
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      // Handle exception or log the error
      throw new RuntimeException("Fallo al actualizar el producto: " +
          e.getMessage());
    }
  }

}
