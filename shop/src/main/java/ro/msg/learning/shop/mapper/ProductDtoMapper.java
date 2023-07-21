package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;

@Mapper
public interface ProductDtoMapper {

    ProductDtoMapper INSTANCE = Mappers.getMapper(ProductDtoMapper.class);
    @Mapping(source = "product.id",target = "productId")
    @Mapping(source = "product.name",target = "productName")
    @Mapping(source = "product.description",target = "productDescription")
    @Mapping(source = "productCategory.id",target = "categoryId")
    @Mapping(source = "productCategory.name",target = "categoryName")
    @Mapping(source = "productCategory.description",target = "categoryDescription")
    ProductDto toProductDto(Product product, ProductCategory productCategory);

    @Mapping(source = "productId",target = "id")
    @Mapping(source = "productName",target = "name")
    @Mapping(source = "productDescription",target = "description")
    @Mapping(source = "categoryId",target = "productCategory.id")
    @Mapping(source = "categoryName",target = "productCategory.name")
    @Mapping(source = "categoryDescription",target = "productCategory.description")
    Product toProduct(ProductDto productDto);

}
