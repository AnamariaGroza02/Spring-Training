package ro.msg.learning.shop.mapper;


import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import ro.msg.learning.shop.domain.PlacedOrder;
import ro.msg.learning.shop.dto.OrderDto;
@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "createdAt",target = "orderTimestamp")
    @Mapping(source = "address.country",target = "country")
    @Mapping(source = "address.city",target = "city")
    @Mapping(source = "address.county",target = "county")
    @Mapping(source = "address.streetAddress",target = "streetAddress")
    OrderDto toOrderDto(PlacedOrder order);

    @AfterMapping
    default void toProductQuantityPairDto(@MappingTarget OrderDto orderDto, PlacedOrder order){

    }


    @Mapping(source = "orderTimestamp",target = "createdAt")
    @Mapping(source = "country",target = "address.country")
    @Mapping(source = "city",target = "address.city")
    @Mapping(source = "county",target = "address.county")
    @Mapping(source = "streetAddress",target = "address.streetAddress")
    PlacedOrder toOrder(OrderDto order);

    @AfterMapping
   default void toProductQuantityPair(@MappingTarget OrderDto orderDto, PlacedOrder order){

    }

}
