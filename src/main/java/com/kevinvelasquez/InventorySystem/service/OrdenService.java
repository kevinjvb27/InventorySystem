package com.kevinvelasquez.InventorySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.kevinvelasquez.InventorySystem.entity.DetalleOrden;
import com.kevinvelasquez.InventorySystem.entity.Orden;
import com.kevinvelasquez.InventorySystem.entity.Producto;
import com.kevinvelasquez.InventorySystem.repository.DetalleOrdenRepository;
import com.kevinvelasquez.InventorySystem.repository.OrdenRepository;
import com.kevinvelasquez.InventorySystem.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Orden> getAllOrdenes() {
        try {
            return ordenRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Fallo al obtener ordenes: " + e.getMessage());
        }
    }

    public Orden getOrdenById(Integer id) {
        return ordenRepository.findById(id).orElse(null);
    }

    @Transactional
    public Orden createOrden(String responsable, List<DetalleOrden> productosSolicitados) {
        Orden orden = new Orden();
        orden.setResponsable(responsable);

        List<DetalleOrden> productosFinales = productosSolicitados.stream()
                .filter(detalle -> {
                    Producto producto = productoRepository.findById(detalle.getCodigoProducto()).orElse(null);
                    return producto != null && producto.getActivo() && producto.getCantidad() > 0;
                })
                .map(detalle -> {
                    Producto producto = productoRepository.findById(detalle.getCodigoProducto()).orElseThrow();
                    int cantidadDisponible = producto.getCantidad();
                    int cantidadARegistrar = Math.min(cantidadDisponible, detalle.getCantidad());
                    producto.setCantidad(cantidadDisponible - cantidadARegistrar);

                    DetalleOrden detalleOrden = new DetalleOrden();
                    detalleOrden.setCodigoProducto(producto.getCodigoproducto());
                    detalleOrden.setCantidad(cantidadARegistrar);
                    detalleOrden.setPrecio(producto.getPrecio());
                    detalleOrden.setOrden(orden);

                    productoRepository.save(producto);
                    return detalleOrden;
                }).toList();

        orden.setDetalleOrden(productosFinales);
        return ordenRepository.save(orden);
    }

    @Transactional
    public Orden updateOrden(Integer ordenId, String nuevoResponsable, List<DetalleOrden> nuevosDetalles) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (nuevoResponsable != null && !nuevoResponsable.equals(orden.getResponsable())) {
            orden.setResponsable(nuevoResponsable);
        }

        for (DetalleOrden detalleActual : orden.getDetalleOrden()) {
            Producto producto = productoRepository.findById(detalleActual.getCodigoProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int cantidadAnterior = detalleActual.getCantidad();
            if (cantidadAnterior > 0) {
                producto.setCantidad(producto.getCantidad() + cantidadAnterior);
                productoRepository.save(producto);
            }

            orden.getDetalleOrden().remove(detalleActual);
            detalleOrdenRepository.delete(detalleActual);
        }

        List<DetalleOrden> detallesFinales = nuevosDetalles.stream()
                .filter(detalle -> detalle.getCantidad() > 0)
                .map(detalle -> {
                    Producto producto = productoRepository.findById(detalle.getCodigoProducto()).orElseThrow();
                    int cantidadDisponible = producto.getCantidad();
                    int cantidadARegistrar = Math.min(cantidadDisponible, detalle.getCantidad());

                    producto.setCantidad(cantidadDisponible - cantidadARegistrar);

                    DetalleOrden nuevoDetalle = new DetalleOrden();
                    nuevoDetalle.setCodigoProducto(producto.getCodigoproducto());
                    nuevoDetalle.setCantidad(cantidadARegistrar);
                    nuevoDetalle.setPrecio(producto.getPrecio());
                    nuevoDetalle.setOrden(orden);

                    productoRepository.save(producto);
                    return nuevoDetalle;
                }).toList();

        orden.setDetalleOrden(detallesFinales);
        return ordenRepository.save(orden);
    }

    @Transactional
    public void deleteOrden(Integer ordenId) {
        Orden orden = ordenRepository.findById(ordenId).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        for (DetalleOrden detalleOrden : orden.getDetalleOrden()) {
            Producto producto = productoRepository.findById(detalleOrden.getCodigoProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setCantidad(producto.getCantidad() + detalleOrden.getCantidad());

            productoRepository.save(producto);
        }

        ordenRepository.delete(orden);
    }
}