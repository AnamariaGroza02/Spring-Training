package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends EntityBase {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCategoryId",nullable = false)
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private Set<Stock> stocks;

}
