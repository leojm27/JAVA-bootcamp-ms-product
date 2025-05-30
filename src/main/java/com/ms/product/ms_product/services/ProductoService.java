package com.ms.product.ms_product.services;

import com.ms.product.ms_product.models.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> getProductos();
    Producto createProducto(Producto producto);

    Producto getProductoById(Long id);

    Producto updateProducto(Producto producto, Long id);

    void deleteProducto(Long id);

}
