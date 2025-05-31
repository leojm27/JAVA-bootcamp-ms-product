package com.ms.product.ms_product.init;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.models.Producto;
import com.ms.product.ms_product.repository.CategoriaRepository;
import com.ms.product.ms_product.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class LoadDatabase {

    @Transactional
    @Bean
    CommandLineRunner initDatabase(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {
        return args -> {
            try {
                if(categoriaRepository.findAll().isEmpty() && productoRepository.findAll().isEmpty()){
                    System.out.println("Creando registros de categorias...");
                    Categoria categoria1 = new Categoria("Computacion", "Productos de computación y tecnología");
                    Categoria categoria2 = new Categoria("Hogar", "Artículos para el hogar y la cocina");
                    System.out.println("Carga inicial categorías 'Computacion':" + categoriaRepository.save(categoria1));
                    System.out.println("Carga inicial categoría 'Hogar': " + categoriaRepository.save(categoria2));

                    System.out.println("Creando registros de productos...");
                    Producto producto1 = new Producto("Laptop Dell XPS 13", "Laptop Dell XPS 13", 1200.00, 1L, 200L, 50L);
                    Producto producto2 = new Producto("Smartphone Samsung Galaxy S21", "Smartphone Samsung Galaxy S21", 800.00, 1L, 200L, 50L);
                    Producto producto3 = new Producto("Cafetera Philips", "Cafetera Philips para espresso", 150.00, 2L,200L ,50L);
                    Producto producto4 = new Producto("Aspiradora Robot", "Aspiradora robot inteligente", 300.00, 2L, 200L, 50L);
                    System.out.println("Carga inicial producto 'Laptop Dell XPS 13': " + productoRepository.save(producto1));
                    System.out.println("Carga inicial producto 'Smartphone Samsung Galaxy S21': " + productoRepository.save(producto2));
                    System.out.println("Carga inicial producto 'Cafetera Philips': " + productoRepository.save(producto3));
                    System.out.println("Carga inicial producto 'Aspiradora Robot': " + productoRepository.save(producto4));
                } else {
                    System.out.println("La base de datos ya contiene registros, no se realizarán inserciones iniciales.");
                }
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("Error durante inicialización de Base de Datos" + e.getMessage());
            }
        };
    }
}
