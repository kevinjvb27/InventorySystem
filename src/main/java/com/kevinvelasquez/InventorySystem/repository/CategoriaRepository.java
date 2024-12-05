package com.kevinvelasquez.InventorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevinvelasquez.InventorySystem.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}
