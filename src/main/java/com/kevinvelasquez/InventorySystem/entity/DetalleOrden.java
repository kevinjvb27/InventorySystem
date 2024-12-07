package com.kevinvelasquez.InventorySystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalleordenes")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigodetalleorden;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "codigoorden")
    private Orden orden;

    private String codigoproducto;

    private Integer cantidad;

    private Float precio;

    public Integer getCodigoDetalleOrden() {
        return codigodetalleorden;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public String getCodigoProducto() {
        return codigoproducto;
    }

    public void setCodigoProducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}