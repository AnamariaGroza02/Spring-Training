package ro.msg.learning.shop.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationProductQuantityEntity {
    private Location location;
    private Product product;
    private Integer quantity;
}
