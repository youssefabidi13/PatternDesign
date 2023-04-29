package com.youssefabidi.ecommerce.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="sku")
    private String sku;

    @Column(name="nb_achat")
    private int nbAchat;
    @Column(name="name")
    private String name ;

    @Column(name="description")
    private String description;

    @Column(name="unit_price")
    private BigDecimal unitPrice;

    @Column(name="sales_price")
    private BigDecimal salesPrice;

    @Column(name="active")
    private boolean active;

    @Column(name="units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "product",fetch=FetchType.EAGER)
    @JsonManagedReference

    private Set<Image> images = new HashSet<>();

   @OneToMany(mappedBy = "product", fetch=FetchType.EAGER)
   @JsonManagedReference

   private Set<Size> sizes = new HashSet<>();

}
