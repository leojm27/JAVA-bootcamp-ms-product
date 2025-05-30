package com.ms.product.ms_product.init;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class LoadDatabase {

    @Transactional
    @Bean
    CommandLineRunner initDatabase(
            // ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {
        return args -> {
            try {
                System.out.println("Inicializando database...");

                if(categoriaRepository.findAll().isEmpty()){
                    System.out.println("Creando registros de categorias...");
                    Categoria categoria1 = new Categoria("Computacion", "Productos de computación y tecnología");
                    Categoria categoria2 = new Categoria("Hogar", "Artículos para el hogar y la cocina");
                    System.out.println("Carga inicial categorías 'Computacion':" + categoriaRepository.save(categoria1));
                    System.out.println("Carga inicial categoría 'Hogar': " + categoriaRepository.save(categoria2));
                }

                /*if(productoRepository.findAll().isEmpty()){
                    System.out.println("Creando registros de categorias...");
                    // Producto producto1 = new Producto("Laptop", "Dell XPS 13", 1200.00);
                }*/
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("Error during database initialization: " + e.getMessage());
            }
        };
    }
}
