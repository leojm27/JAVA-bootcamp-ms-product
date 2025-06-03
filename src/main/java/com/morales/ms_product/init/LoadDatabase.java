package com.morales.ms_product.init;

import com.morales.ms_product.models.Categoria;
import com.morales.ms_product.models.Producto;
import com.morales.ms_product.repository.CategoriaRepository;
import com.morales.ms_product.repository.ProductoRepository;
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
                if(categoriaRepository.findAll().isEmpty()){
                    System.out.println("Creando registros de categorias...");
                    Categoria categoria1 = new Categoria("Computación", "Productos relacionados con computadoras, accesorios y tecnología.");
                    Categoria categoria2 = new Categoria("Fotografía", "Cámaras, lentes y equipos fotográficos para aficionados y profesionales.");
                    Categoria categoria3 = new Categoria("Cafetería", "Cafeteras, accesorios y productos para los amantes del café.");

                    System.out.println("Categoria: " + categoriaRepository.save(categoria1));
                    System.out.println("Categoria: " + categoriaRepository.save(categoria2));
                    System.out.println("Categoria: " + categoriaRepository.save(categoria3));
                    System.out.println("Base de Datos inicializada correctamente.");
                } 
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("Error durante inicialización de Base de Datos" + e.getMessage());
            }
        };
    }
}
