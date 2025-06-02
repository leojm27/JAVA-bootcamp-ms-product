package com.morales.ms_product.services.impl;

import com.morales.ms_product.models.Producto;
import com.morales.ms_product.repository.ProductoRepository;
import com.morales.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServicesImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos.
     * @return Lista de productos
     */
    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll()
                .stream()
                .filter(producto -> producto.getDeletedAt() == null)
                .toList();
    }

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto a buscar
     * @return Producto encontrado o null si no existe
     */
    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .filter(producto -> producto.getDeletedAt() == null)
                .orElse(null);
    }

    /**
     * Crea un nuevo producto.
     * @param producto Producto a crear
     * @return Producto creado
     */
    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente.
     * @param producto Producto con los datos actualizados
     * @param id ID del producto a actualizar
     * @return Producto actualizado o null si no existe
     */
    @Override
    public Producto updateProducto(Producto producto, Long id) {
        return productoRepository.findById(id).filter(existingProducto -> existingProducto.getDeletedAt() == null)
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

    /**
     * Elimina un producto de forma lógica (soft delete).
     * @param id ID del producto a eliminar
     */
    @Override
    public void softDeleteProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null || producto.getDeletedAt() != null) {
            throw new IllegalArgumentException("La producto con ID " + id + " no existe");
        }

        producto.setDeletedAt(new Date());
        productoRepository.save(producto);
    }
}
