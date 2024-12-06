package com.kevinvelasquez.InventorySystem.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String username;

    private String nombre;

    private String apellido;

    private String password;

    private int codigorol;

    @CreationTimestamp
    private LocalDateTime fechacreacion;

    @UpdateTimestamp
    private LocalDateTime fechamodificacion;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodigorol() {
        return codigorol;
    }

    public void setCodigorol(int codigorol) {
        this.codigorol = codigorol;
    }

    public LocalDateTime getFechacreacion() {
        return fechacreacion;
    }

    public LocalDateTime getFechamodificacion() {
        return fechamodificacion;
    }
}