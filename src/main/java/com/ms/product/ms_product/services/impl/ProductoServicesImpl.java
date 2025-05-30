package com.ms.product.ms_product.services.impl;

import com.ms.product.ms_product.models.Producto;
import com.ms.product.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServicesImpl implements ProductoService {

    @Override
    public List<Producto> getProductos() {
        return List.of();
    }

    @Override
    public Producto createProducto(Producto producto) {
        return null; // Reemplazar con la implementación real
    }

    @Override
    public Producto getProductoById(Long id) {
        return null; // Reemplazar con la implementación real
    }

    @Override
    public Producto updateProducto(Producto producto, Long id) {
        return null;
    }

    @Override
    public void deleteProducto(Long id) {
        // Lógica para eliminar un producto
    }
}
