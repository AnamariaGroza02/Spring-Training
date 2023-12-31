package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlacedOrder extends EntityBase {

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address.country", column = @Column(name = "Country")),
            @AttributeOverride(name = "address.city", column = @Column(name = "City")),
            @AttributeOverride(name = "address.county", column = @Column(name = "County")),
            @AttributeOverride(name = "address.streetAddress", column = @Column(name = "StreetAddress"))
    })
    private Address address;

    @OneToMany(mappedBy = "placedOrder")
    private List<OrderDetail> orderDetails;
}
