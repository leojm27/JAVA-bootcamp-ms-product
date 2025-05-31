package com.ms.product.ms_product.services.impl;

import com.ms.product.ms_product.models.Producto;
import com.ms.product.ms_product.repository.ProductoRepository;
import com.ms.product.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @AllArgsConstructor
public class ProductoServicesImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServicesImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return null;
    }

    @Override
    public Producto createProducto(Producto producto) {
        return null;
    }

    @Override
    public Producto updateProducto(Producto producto, Long id) {
        return null;
    }

    @Override
    public void softDeleteProducto(Long id) {
        // Lógica para eliminar un producto
    }
}
