package com.kevinvelasquez.InventorySystem.entity;

// import java.security.Timestamp;
import java.time.LocalDateTime;
// import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
  @Id
  private String codigocategoria;
  private String categoria;
  private String descripcion;
  @CreationTimestamp
  private LocalDateTime fechacreacion;

  public String getCodigocategoria() {
    return codigocategoria;
  }

  public String getCategoria() {
    return categoria;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public LocalDateTime getFechacreacion() {
    return fechacreacion;
  }

  public void setCodigocategoria(String codigocategoria) {
    this.codigocategoria = codigocategoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
