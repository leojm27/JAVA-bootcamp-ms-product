package com.ms.product.ms_product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "productos")
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "id_categoria", nullable = false)
    private Long id_categoria;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "cantidad_alert", nullable = false)
    private Long cantidad_alert;

    @Column(name = "created_at" , nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public Producto(String nombre, String descripcion, Double precio, Long id_categoria, Long cantidad, Long cantidad_alert) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.cantidad = cantidad;
        this.cantidad_alert = cantidad_alert;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
