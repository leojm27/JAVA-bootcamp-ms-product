package com.morales.ms_product;

import com.morales.ms_product.controllers.CategoriaController;
import com.morales.ms_product.models.Categoria;
import com.morales.ms_product.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoriaControllerTests {

    private MockMvc mockMvc;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
    }

    /* getCategorias */
    @Test
    void testGetCategorias() {
        when(categoriaService.getCategorias())
                .thenReturn(List.of(
                        new Categoria("Electronica", "Electronica"),
                        new Categoria("Libros", "Libros")
                ));
        ResponseEntity<?> response = categoriaController.getCategorias();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void errorInGetCategorias() {
        when(categoriaService.getCategorias())
                .thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = categoriaController.getCategorias();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldReturnEmptyListWhenGetCategorias() {
        when(categoriaService.getCategorias()).thenReturn(List.of());

        ResponseEntity<?> response = categoriaController.getCategorias();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(List.of());
    }

    /* getCategoriaById */
    @Test
    void testGetCategoriaById() {
        Long id = 1L;
        Categoria categoria = new Categoria("Electronica", "Electronica");
        when(categoriaService.getCategoriaById(id)).thenReturn(categoria);

        ResponseEntity<?> response = categoriaController.getCategoriaById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(categoria);
    }

    @Test
    void shouldReturnNotFoundWhenGetCategoriaById() {
        Long id = 1L;
        when(categoriaService.getCategoriaById(id)).thenReturn(null);

        ResponseEntity<?> response = categoriaController.getCategoriaById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnInternarServerErrorInGetCategoriaById(){
        Long id = 1L;
        when(categoriaService.getCategoriaById(id))
                .thenThrow(new RuntimeException("Error al obtener categoría"));

        ResponseEntity<?> response = categoriaController.getCategoriaById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* createCategoria */
    @Test
    void testCreateCategoria() {
        Categoria categoria = new Categoria("Electronica", "Electronica");
        when(categoriaService.createCategoria(categoria)).thenReturn(categoria);

        ResponseEntity<?> response = categoriaController.createCategoria(categoria);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(categoria);
    }

    @Test
    void errorTestReturnInternalServerErrorInCreateCategoria() {
        Categoria categoria = new Categoria("Electronica", "Electronica");
        when(categoriaService.createCategoria(categoria))
                .thenThrow(new RuntimeException("Error al crear categoría"));

        ResponseEntity<?> response = categoriaController.createCategoria(categoria);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Ocurrio un error al intentar crear nueva Categoria");
    }

    /* updateCategoria */
    @Test
    void shouldUpdateCategoria() {
        Long id = 1L;
        Categoria updateCategoria = new Categoria("Electronica Actualizada", "Electronica Actualizada");
        Categoria categoriaExistente = new Categoria("Electronica", "Electronica");

        when(categoriaService.updateCategoria(updateCategoria, id)).thenReturn(categoriaExistente);

        ResponseEntity<?> response = categoriaController.updateCategoria(updateCategoria, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(categoriaExistente);
    }

    @Test
    void shouldReturnInternalServerErrorWhenUpdateCategoria() {
        Long id = 1L;
        Categoria updateCategoria = new Categoria("Electronica Actualizada", "Electronica Actualizada");
        when(categoriaService.updateCategoria(updateCategoria, id))
                .thenThrow(new RuntimeException("Error al actualizar categoría"));

        ResponseEntity<?> response = categoriaController.updateCategoria(updateCategoria, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al actualizar categoría con ID " + id);
    }

    /* deleteCategoria */
    @Test
    void shouldDeleteCategoria() {
        Long id = 1L;
        doNothing().when(categoriaService).softDeleteCategoria(id);

        ResponseEntity<?> response = categoriaController.softDeleteCategoria(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void errorTestReturnNotFoundInDeleteCategoria() {
        Long id = 1L;
        doThrow(new IllegalArgumentException("Adopción no encontrada"))
                .when(categoriaService).softDeleteCategoria(id);

        ResponseEntity<?> response = categoriaController.softDeleteCategoria(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnInternalServerErrorInDeleteCategoria() {
        Long id = 1L;
        doThrow(new RuntimeException("Error al eliminar categoría"))
                .when(categoriaService).softDeleteCategoria(id);

        ResponseEntity<?> response = categoriaController.softDeleteCategoria(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
