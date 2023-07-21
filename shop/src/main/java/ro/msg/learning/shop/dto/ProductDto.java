package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private UUID productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;

    private UUID categoryId;
    private String categoryName;
    private String categoryDescription;
}
