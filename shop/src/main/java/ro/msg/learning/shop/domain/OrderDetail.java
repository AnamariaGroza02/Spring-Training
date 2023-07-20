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
    @Id
    private UUID orderId;

    @Id
    private UUID productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;



    @ManyToOne
    @JoinColumn(name = "productId",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "placedOrderId",nullable = false)
    private PlacedOrder placedOrder;

    @ManyToOne
    @JoinColumn(name = "locationId",nullable = false)
    private Location shippedFrom;



}
