package com.kevinvelasquez.InventorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevinvelasquez.InventorySystem.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

}
