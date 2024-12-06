package com.kevinvelasquez.InventorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevinvelasquez.InventorySystem.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {

}
