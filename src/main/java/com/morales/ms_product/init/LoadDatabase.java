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
                    Categoria categoria1 = new Categoria("Hogar","Artículos para el hogar y la cocina.");
                    Categoria categoria2 = new Categoria("Alimentos","Alimentos no perecederos y de consumo masivo.");
                    Categoria categoria3 = new Categoria("Cuidado personal","Productos de higiene y cuidado personal.");

                    System.out.println("Categoria Hogar: " + categoriaRepository.save(categoria1));
                    System.out.println("Categoria Alimentos: " + categoriaRepository.save(categoria2));
                    System.out.println("Categoria Cuidado personal: " + categoriaRepository.save(categoria3));

                    System.out.println("Creando registros de productos...");
                    // Hogar
                    Producto producto1 = new Producto("Juego de sábanas 2 plazas", "Sábanas de algodón 144 hilos", 15999.00,1L);
                    Producto producto2 = new Producto("Olla Essen 24cm", "Olla de aluminio fundido con tapa de vidrio", 34999.00, 1L);
                    Producto producto3 = new Producto("Set de 6 vasos de vidrio", "Vasos de vidrio templado para bebidas", 6999.00, 1L);
                    Producto producto4 = new Producto("Cuchillo Tramontina Chef", "Cuchillo de cocina profesional", 7999.00, 1L);
                    Producto producto5 = new Producto("Alfombra antideslizante baño", "Alfombra de microfibra con base de goma", 4999.00,1L);

                    // Alimentos
                    Producto producto6 = new Producto("Arroz largo fino 1kg", "Arroz blanco tipo largo fino", 899.00, 2L);
                    Producto producto7 = new Producto("Aceite de girasol 1.5L", "Aceite comestible de girasol", 2699.00, 2L);
                    Producto producto8 = new Producto("Yerba mate Taragüi 1kg", "Yerba mate con palo tradicional", 2799.00, 2L);
                    Producto producto9 = new Producto("Harina 0000", "1kg	Harina de trigo refinada", 799.00, 2L);
                    Producto producto10 = new Producto("Lentejas secas 500g", "Lentejas secas para guisos", 1199.00, 2L);

                    // Cuidado personal
                    Producto producto11 = new Producto("Shampoo Pantene 400ml", "Shampoo para cabello normal a seco", 4299.00, 3L);
                    Producto producto12 = new Producto("Jabón Dove 90g", "Jabón en barra con crema hidratante", 1299.00, 3L);
                    Producto producto13 = new Producto("Pasta dental Colgate 90g", "Pasta dental triple acción", 1399.00, 3L);
                    Producto producto14 = new Producto("Desodorante Rexona aerosol", "Antitranspirante en aerosol", 2899.00, 3L);
                    Producto producto15 = new Producto("Toalla femenina Always 8u", "Toallas nocturnas con alas", 1899.00, 3L);

                    System.out.println("Producto de categoria Hogar: " + productoRepository.save(producto1));
                    System.out.println("Producto de categoria Hogar: " + productoRepository.save(producto2));
                    System.out.println("Producto de categoria Hogar: " + productoRepository.save(producto3));
                    System.out.println("Producto de categoria Hogar: " + productoRepository.save(producto4));
                    System.out.println("Producto de categoria Hogar: " + productoRepository.save(producto5));
                    System.out.println("Producto de categoria Alimentos: " + productoRepository.save(producto6));
                    System.out.println("Producto de categoria Alimentos: " + productoRepository.save(producto7));
                    System.out.println("Producto de categoria Alimentos: " + productoRepository.save(producto8));
                    System.out.println("Producto de categoria Alimentos: " + productoRepository.save(producto9));
                    System.out.println("Producto de categoria Alimentos: " + productoRepository.save(producto10));
                    System.out.println("Producto de categoria Cuidado personal: " + productoRepository.save(producto11));
                    System.out.println("Producto de categoria Cuidado personal: " + productoRepository.save(producto12));
                    System.out.println("Producto de categoria Cuidado personal: " + productoRepository.save(producto13));
                    System.out.println("Producto de categoria Cuidado personal: " + productoRepository.save(producto14));
                    System.out.println("Producto de categoria Cuidado personal: " + productoRepository.save(producto15));
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
