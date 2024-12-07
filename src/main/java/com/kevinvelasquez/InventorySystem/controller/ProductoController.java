package com.kevinvelasquez.InventorySystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevinvelasquez.InventorySystem.entity.Producto;
import com.kevinvelasquez.InventorySystem.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  // Create a new category
  @PostMapping()
  public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
    System.out.println("Producto: " + producto);
    Producto savedProducto = productoService.saveProducto(producto);
    return ResponseEntity.ok(savedProducto);
  }

  // Get all Productos
  @GetMapping()
  public ResponseEntity<List<Producto>> getAllProductos() {
    List<Producto> Productos = productoService.fetchAllProductos();
    return ResponseEntity.ok(Productos);
  }

  // Get a Producto by ID
  @GetMapping("/{id}")
  public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
    Optional<Producto> productoOptional = productoService.fetchProductoById(id);
    return productoOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Update a Producto
  @PutMapping(path = "/{id}")
  public ResponseEntity<Producto> updateProducto(@PathVariable String id,
      @RequestBody Producto producto) {
    Optional<Producto> updatedProductoOptional = productoService.updateCatergoriaOptional(id, producto);
    return updatedProductoOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
