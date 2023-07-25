package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(OrderDetailId.class)
public class OrderDetail {

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private PlacedOrder placedOrder;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location shippedFrom;


}
