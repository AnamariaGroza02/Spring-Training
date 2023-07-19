package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@AttributeOverride(name = "identifier", column = @Column(name = "VIN"))
public class Location extends EntityBase{

    @OneToMany(mappedBy = "shippedFrom")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "location")
    private Set<Stock> stocks;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address.country", column = @Column(name = "Country")),
            @AttributeOverride(name = "address.city", column = @Column(name = "City")),
            @AttributeOverride(name = "address.county", column = @Column(name = "County")),
            @AttributeOverride(name = "address.streetAddress", column = @Column(name = "StreetAddress"))
    })
    private Address address;


}
