package ro.msg.learning.shop.mapper;


import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.PlacedOrder;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductQuantityPairDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "createdAt", target = "orderTimestamp")
    @Mapping(source = "address.country", target = "country")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.county", target = "county")
    @Mapping(source = "address.streetAddress", target = "streetAddress")
    OrderDto toOrderDto(PlacedOrder order);

    @AfterMapping
    default void toProductQuantityPairDto(@MappingTarget OrderDto orderDto, PlacedOrder order) {
        List<ProductQuantityPairDto> productQuantityPairDtoList = order.getOrderDetails().stream().map(
                orderItem -> {
                    ProductQuantityPairDto productQuantityPairDto = new ProductQuantityPairDto();
                    productQuantityPairDto.setId(orderItem.getProduct().getId());
                    productQuantityPairDto.setQuantity(orderItem.getQuantity());
                    return productQuantityPairDto;
                }).collect(Collectors.toList());
        orderDto.setProductsInOrder(productQuantityPairDtoList);

    }


    @Mapping(source = "orderTimestamp", target = "createdAt")
    @Mapping(source = "country", target = "address.country")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "county", target = "address.county")
    @Mapping(source = "streetAddress", target = "address.streetAddress")
    PlacedOrder toOrder(OrderDto orderDto);

    @AfterMapping
    default void toProductQuantityPair(@MappingTarget PlacedOrder order, OrderDto orderDto) {
        List<OrderDetail> orderItems = orderDto.getProductsInOrder().stream().map(
                productQuantityPairDto -> {
                    OrderDetail orderItem = new OrderDetail();
                    Product product = new Product();
                    product.setId(productQuantityPairDto.getId());
                    orderItem.setProduct(product);
                    orderItem.setQuantity(productQuantityPairDto.getQuantity());
//                    orderItem.setPlacedOrder(order);
                    return orderItem;
                }
        ).collect(Collectors.toList());
        order.setOrderDetails(orderItems);

    }

}
