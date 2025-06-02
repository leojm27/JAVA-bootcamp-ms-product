package com.morales.ms_product;

import com.morales.ms_product.models.Categoria;
import com.morales.ms_product.repository.CategoriaRepository;
import com.morales.ms_product.services.impl.CategoriaServiceImpl;
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

public class CategoriaServiceImplTests {

    private MockMvc mockMvc;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaServiceImpl).build();
    }

    /* getCategorias */
    @Test
    void testGetCategorias(){
        when(categoriaRepository.findAll()).thenReturn(List.of(
                new Categoria("Categoria 1", "Descripción 1"),
                new Categoria("Categoria 2", "Descripción 2")
        ));
        List<Categoria> response = categoriaServiceImpl.getCategorias();
        System.out.println(response);
        assertThat(response).isNotEmpty();
    }

    /* getCategoriaById */
    @Test
    void testGetCategoriaById(){
        when(categoriaRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new Categoria("Categoria 1", "Descripción 1")));

        Categoria response = categoriaServiceImpl.getCategoriaById(1L);
        assertThat(response).isNotNull();
    }

    /* createCategoria */
    @Test
    void testCreateCategoria(){
        Categoria categoria = new Categoria("Categoria 1", "Descripción 1");
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria response = categoriaServiceImpl.createCategoria(categoria);
        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("Categoria 1");
    }

    /* updateCategoria */
    @Test
    void testUpdateCategoria() {
        Categoria existingCategoria = new Categoria("Categoria 1", "Descripción 1");
        existingCategoria.setId(1L);

        Categoria updateCategoria = new Categoria("Categoria Actualizada", "Descripción Actualizada");

        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(existingCategoria));
        when(categoriaRepository.save(existingCategoria)).thenReturn(existingCategoria);

        Categoria response = categoriaServiceImpl.updateCategoria(updateCategoria, 1L);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("Categoria Actualizada");
    }

    /* softDeleteCategoria */
    @Test
    void testSoftDeleteCategoria() {
        Categoria categoria = new Categoria("Categoria 1", "Descripción 1");
        categoria.setId(1L);

        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria));
        categoriaServiceImpl.softDeleteCategoria(1L);
        assertThat(categoria.getDeletedAt()).isNotNull();
    }
}
