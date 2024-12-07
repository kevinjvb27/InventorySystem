package com.kevinvelasquez.InventorySystem.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String codigorol;
  private String rol;
  private String descripcion;
  @CreationTimestamp
  private LocalDateTime fechacreacion;

  public String getCodigoRoles() {
    return codigorol;
  }

  public String getRol() {
    return rol;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public LocalDateTime getFechacreacion() {
    return fechacreacion;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
