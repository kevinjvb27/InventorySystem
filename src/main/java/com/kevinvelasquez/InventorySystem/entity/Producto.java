package com.kevinvelasquez.InventorySystem.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
  @Id
  private String codigoproducto;
  private String producto;
  private String descripcion;
  private Float precio;
  private Integer cantidad;
  private Boolean activo;
  private String codigocategoria;

  @CreationTimestamp
  private LocalDateTime fechacreacion;

  @UpdateTimestamp
  private LocalDateTime modificacion;

  public String getCodigoproducto() {
    return codigoproducto;
  }

  public void setCodigoproducto(String codigoproducto) {
    this.codigoproducto = codigoproducto;
  }

  public String getProducto() {
    return producto;
  }

  public void setProducto(String producto) {
    this.producto = producto;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Float getPrecio() {
    return precio;
  }

  public void setPrecio(Float precio) {
    this.precio = precio;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Boolean getActivo() {
    return activo;
  }

  public void setActivo(Boolean activo) {
    this.activo = activo;
  }

  public String getCodigocategoria() {
    return codigocategoria;
  }

  public void setCodigocategoria(String codigocategoria) {
    this.codigocategoria = codigocategoria;
  }

  public LocalDateTime getFechacreacion() {
    return fechacreacion;
  }

  public LocalDateTime getModificacion() {
    return modificacion;
  }

}
