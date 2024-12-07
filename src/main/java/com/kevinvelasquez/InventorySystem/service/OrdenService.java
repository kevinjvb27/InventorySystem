package com.kevinvelasquez.InventorySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kevinvelasquez.InventorySystem.entity.DetalleOrden;
import com.kevinvelasquez.InventorySystem.entity.Orden;
import com.kevinvelasquez.InventorySystem.entity.Producto;
import com.kevinvelasquez.InventorySystem.repository.OrdenRepository;
import com.kevinvelasquez.InventorySystem.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

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
    public Orden updateOrden(Integer ordenId, Orden nuevaOrden) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (!orden.getResponsable().equals(nuevaOrden.getResponsable())) {
            throw new RuntimeException("El responsable no coincide con el original");
        }

        Map<String, DetalleOrden> nuevosDetallesMap = nuevaOrden.getDetalleOrden().stream()
                .collect(Collectors.toMap(DetalleOrden::getCodigoProducto, detalle -> detalle));

        List<DetalleOrden> detallesActuales = orden.getDetalleOrden();
        Iterator<DetalleOrden> iterator = detallesActuales.iterator();
        while (iterator.hasNext()) {
            DetalleOrden detalleActual = iterator.next();
            Producto producto = productoRepository.findById(detalleActual.getCodigoProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (nuevosDetallesMap.containsKey(detalleActual.getCodigoProducto())) {
                DetalleOrden nuevoDetalle = nuevosDetallesMap.get(detalleActual.getCodigoProducto());

                int diferencia = nuevoDetalle.getCantidad() - detalleActual.getCantidad();

                if (diferencia != 0) {
                    if (producto.getCantidad() < diferencia) {
                        throw new RuntimeException(
                                "Stock insuficiente para el producto: " + detalleActual.getCodigoProducto());
                    }

                    producto.setCantidad(producto.getCantidad() - diferencia);
                    productoRepository.save(producto);

                    detalleActual.setCantidad(nuevoDetalle.getCantidad());
                }

                nuevosDetallesMap.remove(detalleActual.getCodigoProducto());
            } else {
                producto.setCantidad(producto.getCantidad() + detalleActual.getCantidad());
                productoRepository.save(producto);

                iterator.remove();
                // detalleOrdenRepository.delete(detalleActual); // NO FUNCIONA :(
            }
        }

        for (DetalleOrden nuevoDetalle : nuevosDetallesMap.values()) {
            Producto producto = productoRepository.findById(nuevoDetalle.getCodigoProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getCantidad() < nuevoDetalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + nuevoDetalle.getCodigoProducto());
            }

            producto.setCantidad(producto.getCantidad() - nuevoDetalle.getCantidad());
            productoRepository.save(producto);

            nuevoDetalle.setOrden(orden);
            nuevoDetalle.setPrecio(producto.getPrecio());
            detallesActuales.add(nuevoDetalle);
        }

        orden.setDetalleOrden(detallesActuales);
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