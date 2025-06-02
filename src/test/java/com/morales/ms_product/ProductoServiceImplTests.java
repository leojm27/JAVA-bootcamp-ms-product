package com.morales.ms_product;

import com.morales.ms_product.models.Producto;
import com.morales.ms_product.repository.ProductoRepository;
import com.morales.ms_product.services.impl.ProductoServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ProductoServiceImplTests {

    private MockMvc mockMvc;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServicesImpl productoServicesImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productoServicesImpl).build();
    }

    /* getProductos */
    @Test
    void testGetProductos(){
        when(productoRepository.findAll()).thenReturn(List.of(
                new Producto("producto 1", "Descripción 1", 100.0, 1L),
                new Producto("producto 2", "Descripción 2", 200.0, 2L)
        ));
        List<Producto> response = productoServicesImpl.getProductos();
        assertThat(response).isNotEmpty();
    }

    /* getProductoById */
    @Test
    void testGetProductoById(){
        when(productoRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new Producto("Producto 1", "Descripción 1", 100.0, 1L)));

        Producto response = productoServicesImpl.getProductoById(1L);
        assertThat(response).isNotNull();
    }

    /* createProducto */
    @Test
    void testCreateProducto(){
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto response = productoServicesImpl.createProducto(producto);
        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("Producto 1");
    }

    /* updateProducto */
    @Test
    void testUpdateProducto() {
        Producto existingProducto = new Producto("Producto Existente", "Descripción Existente", 100.0, 1L);
        existingProducto.setId(1L);

        Producto updateProducto = new Producto("Producto Actualizado", "Descripción Actualizada", 150.0, 1L);

        when(productoRepository.findById(1L)).thenReturn(java.util.Optional.of(existingProducto));
        when(productoRepository.save(existingProducto)).thenReturn(existingProducto);

        Producto response = productoServicesImpl.updateProducto(updateProducto, 1L);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("Producto Actualizado");
    }

    /* softDeleteProducto */
    @Test
    void testSoftDeleteProducto() {
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        producto.setId(1L);

        when(productoRepository.findById(1L)).thenReturn(java.util.Optional.of(producto));
        productoServicesImpl.softDeleteProducto(1L);
        assertThat(producto.getDeletedAt()).isNotNull();
    }

}
