package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
