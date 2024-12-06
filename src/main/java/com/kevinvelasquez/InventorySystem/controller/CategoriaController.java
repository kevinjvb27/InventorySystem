package com.kevinvelasquez.InventorySystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevinvelasquez.InventorySystem.entity.Categoria;
import com.kevinvelasquez.InventorySystem.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  // Create a new category
  @PostMapping()
  public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
    Categoria savedCategoria = categoriaService.saveCategoria(categoria);
    return ResponseEntity.ok(savedCategoria);
  }

  // Get all Categorias
  @GetMapping()
  public ResponseEntity<List<Categoria>> getAllCategorias() {
    List<Categoria> categorias = categoriaService.fetchAllCategorias();
    return ResponseEntity.ok(categorias);
  }

  // Get a Categoria by ID
  @GetMapping("/{id}")
  public ResponseEntity<Categoria> getCategoriaById(@PathVariable String id) {
    Optional<Categoria> categoriaOptional = categoriaService.fetchCategoriaById(id);
    return categoriaOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Update a Categoria
  @PutMapping(path = "/{id}")
  public ResponseEntity<Categoria> updateCategoria(@PathVariable String id, @RequestBody Categoria categoria) {
    Optional<Categoria> updatedCategoriaOptional = categoriaService.updateCatergoriaOptional(id, categoria);
    return updatedCategoriaOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Delte a Categoria
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteCategoria(@PathVariable String id) {
    boolean deletionStatus = categoriaService.deleteCategoria(id);
    if (deletionStatus) {
      return ResponseEntity.ok("Categoria con codigo " + id + " ha sido eliminado satisfactoriamente");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Fallo al eliminar categoria con codigo " + id);
    }
  }

}
