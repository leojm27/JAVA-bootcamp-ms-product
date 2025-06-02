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
                if(categoriaRepository.findAll().isEmpty() && productoRepository.findAll().isEmpty()){
                    System.out.println("Creando registros de categorias...");
                    Categoria categoria1 = new Categoria("Computación", "Productos relacionados con computadoras, accesorios y tecnología.");
                    Categoria categoria2 = new Categoria("Fotografía", "Cámaras, lentes y equipos fotográficos para aficionados y profesionales.");
                    Categoria categoria3 = new Categoria("Cafetería", "Cafeteras, accesorios y productos para los amantes del café.");

                    System.out.println("Categoria: " + categoriaRepository.save(categoria1));
                    System.out.println("Categoria: " + categoriaRepository.save(categoria2));
                    System.out.println("Categoria: " + categoriaRepository.save(categoria3));

                    System.out.println("Creando registros de productos...");
                    // Computación
                    Producto producto1 = new Producto("Mouse inalámbrico Logitech M185", "Mouse compacto con conexión USB inalámbrica", 5499.00, 1L);
                    Producto producto2 = new Producto("Teclado mecánico Redragon Kumara", "Teclado gamer con retroiluminación LED RGB", 18999.00, 1L);
                    Producto producto3 = new Producto("Monitor Samsung 24'' Full HD", "Pantalla LED con resolución 1920x1080 y tecnología Eye Saver", 65999.00, 1L);

                    // Fotografía
                    Producto producto4 = new Producto("Cámara réflex Canon EOS Rebel T7", "Cámara DSLR con lente 18-55mm incluida", 239999.00, 2L);
                    Producto producto5 = new Producto("Trípode Fotopro DIGI-3400", "Trípode liviano para cámaras compactas y réflex", 12999.00, 2L);
                    Producto producto6 = new Producto("Lente Canon EF 50mm f/1.8 STM", "Lente fijo ideal para retratos y fotografía en poca luz", 58999.00, 2L);

                    // Cafetería
                    Producto producto7 = new Producto("Cafetera Nespresso Essenza Mini", "Cafetera automática de cápsulas con diseño compacto", 74999.00, 3L);
                    Producto producto8 = new Producto("Molino de café manual Hario", "Molino con muelas de cerámica ajustables para café espresso", 21999.00, 3L);
                    Producto producto9 = new Producto("Tazas espresso de cerámica x2", "Set de dos tazas para espresso de 90ml", 4999.00, 3L);

                    System.out.println("Producto: " + productoRepository.save(producto1));
                    System.out.println("Producto: " + productoRepository.save(producto2));
                    System.out.println("Producto: " + productoRepository.save(producto3));
                    System.out.println("Producto: " + productoRepository.save(producto4));
                    System.out.println("Producto: " + productoRepository.save(producto5));
                    System.out.println("Producto: " + productoRepository.save(producto6));
                    System.out.println("Producto: " + productoRepository.save(producto7));
                    System.out.println("Producto: " + productoRepository.save(producto8));
                    System.out.println("Producto: " + productoRepository.save(producto9));
                    System.out.println("Base de Datos inicializada correctamente.");
                } else {
                    System.out.println("Base de Datos inicializada correctamente.");
                }
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("Error durante inicialización de Base de Datos" + e.getMessage());
            }
        };
    }
}
