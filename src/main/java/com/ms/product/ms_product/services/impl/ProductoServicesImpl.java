package com.ms.product.ms_product.services.impl;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.models.Producto;
import com.ms.product.ms_product.repository.ProductoRepository;
import com.ms.product.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServicesImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Producto::getId))
                .filter(producto -> producto.getDeletedAt() == null)
                .toList();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .filter(producto -> producto.getDeletedAt() == null)
                .orElse(null);
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto, Long id) {
        return productoRepository.findById(id)
                .map(existingProducto -> {
                    if (producto.getNombre() != null) {
                        existingProducto.setNombre(producto.getNombre());
                    }
                    if (producto.getDescripcion() != null) {
                        existingProducto.setDescripcion(producto.getDescripcion());
                    }
                    if (producto.getPrecio() != null) {
                        existingProducto.setPrecio(producto.getPrecio());
                    }
                    if (producto.getIdCategoria() != null) {
                        existingProducto.setIdCategoria(producto.getIdCategoria());
                    }
                    return productoRepository.save(existingProducto);
                }).orElse(null);
    }

    @Override
    public void softDeleteProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null){
            throw new IllegalArgumentException("La producto con ID " + id + " no existe");
        }

        producto.setDeletedAt(new Date());
        productoRepository.save(producto);
    }
}
