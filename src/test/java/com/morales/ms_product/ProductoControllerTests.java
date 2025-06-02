package com.morales.ms_product;

import com.morales.ms_product.controllers.ProductoController;
import com.morales.ms_product.models.Producto;
import com.morales.ms_product.services.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductoControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    /* getProductos */
    @Test
    void testgetProductos(){
        when(productoService.getProductos())
                .thenReturn(
                        Arrays.asList(
                                new Producto( "Producto 1", "Descripción 1", 100.0, 1L),
                                new Producto( "Producto 2", "Descripción 2", 100.0, 1L)
                        ));

        ResponseEntity<?> response = productoController.getProductos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void errorTestGetProductos(){
        when(productoService.getProductos())
                .thenThrow(new RuntimeException("Error al obtener productos"));

        ResponseEntity<?> response = productoController.getProductos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* getProductoById */
    @Test
    void testgetProductoById(){
        Long id = 1L;
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        when(productoService.getProductoById(id)).thenReturn(producto);

        ResponseEntity<?> response = productoController.getProductoById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testgetProductoByIdNotFound(){
        Long id = 1L;
        when(productoService.getProductoById(id)).thenReturn(null);

        ResponseEntity<?> response = productoController.getProductoById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void errorTestgetProductoById(){
        Long id = 1L;
        when(productoService.getProductoById(id)).thenThrow(new RuntimeException("Error al obtener producto con ID " + id));

        ResponseEntity<?> response = productoController.getProductoById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al obtener producto con ID " + id);
    }

    /* createProducto */

    @Test
    void testcreateProducto() {
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        when(productoService.createProducto(producto)).thenReturn(producto);

        ResponseEntity<?> response = productoController.createProducto(producto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(producto);
    }

    @Test
    void errorTestcreateProducto() {
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        when(productoService.createProducto(producto)).thenThrow(new IllegalArgumentException("Error al crear producto"));

        ResponseEntity<?> response = productoController.createProducto(producto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Error al crear producto");
    }

    /* updateProducto */
    @Test
    void testUpdateProducto(){
        Long id = 1L;
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        when(productoService.updateProducto(producto, id)).thenReturn(producto);

        ResponseEntity<?> response = productoController.updateProducto(producto, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(producto);
    }

    @Test
    void errorTestUpdateProducto() {
        Long id = 1L;
        Producto producto = new Producto("Producto 1", "Descripción 1", 100.0, 1L);
        when(productoService.updateProducto(producto, id)).thenThrow(new RuntimeException("Error al actualizar producto"));

        ResponseEntity<?> response = productoController.updateProducto(producto, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al actualizar producto");
    }

    /* deleteProducto */
    @Test
    void testDeleteProducto() {
        Long id = 1L;
        doNothing().when(productoService).softDeleteProducto(id);

        ResponseEntity<?> response = productoController.deleteProducto(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void errorTestDeleteProducto() {
        Long id = 1L;
        doThrow(new IllegalArgumentException("no encontrada"))
                .when(productoService).softDeleteProducto(id);

        ResponseEntity<?> response = productoController.deleteProducto(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
