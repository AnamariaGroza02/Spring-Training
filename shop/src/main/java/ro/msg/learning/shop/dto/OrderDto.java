package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private LocalDateTime orderTimestamp;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private List<ProductQuantityPairDto> productsInOrder;
}
