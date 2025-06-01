package com.morales.ms_product.services;

import com.morales.ms_product.models.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> getProductos();
    Producto createProducto(Producto producto);

    Producto getProductoById(Long id);

    Producto updateProducto(Producto producto, Long id);

    void softDeleteProducto(Long id);

}
