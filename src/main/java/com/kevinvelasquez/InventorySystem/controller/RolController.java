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

import com.kevinvelasquez.InventorySystem.entity.Rol;
import com.kevinvelasquez.InventorySystem.service.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController {

  @Autowired
  private RolService rolService;

  // Create a new category
  @PostMapping()
  public ResponseEntity<Rol> saveRol(@RequestBody Rol rol) {
    Rol savedRol = rolService.saveRol(rol);
    return ResponseEntity.ok(savedRol);
  }

  // Get all Roles
  @GetMapping()
  public ResponseEntity<List<Rol>> getAllRols() {
    List<Rol> rol = rolService.fetchAllRols();
    return ResponseEntity.ok(rol);
  }

  // Get a Rol by ID
  @GetMapping("/{id}")
  public ResponseEntity<Rol> getRolById(@PathVariable String id) {
    Optional<Rol> rolOptional = rolService.fetchRolById(id);
    return rolOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Update a Rol
  @PutMapping(path = "/{id}")
  public ResponseEntity<Rol> updateRol(@PathVariable String id, @RequestBody Rol rol) {
    Optional<Rol> updatedRolOptional = rolService.updateCatergoriaOptional(id, rol);
    return updatedRolOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Delte a Rol
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteRol(@PathVariable String id) {
    boolean deletionStatus = rolService.deleteRol(id);
    if (deletionStatus) {
      return ResponseEntity.ok("Rol con codigo " + id + " ha sido eliminado satisfactoriamente");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Fallo al eliminar rol con codigo " + id);
    }
  }

}
