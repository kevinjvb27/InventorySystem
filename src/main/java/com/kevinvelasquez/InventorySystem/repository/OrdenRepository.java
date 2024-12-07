package com.kevinvelasquez.InventorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevinvelasquez.InventorySystem.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
}