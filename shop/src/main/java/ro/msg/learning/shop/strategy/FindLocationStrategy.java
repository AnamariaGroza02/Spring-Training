package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.util.LocationProductQuantityEntity;

import java.util.List;

public interface FindLocationStrategy {
    public List<LocationProductQuantityEntity> findLocationWithStock(List<OrderDetail> orderDetails);
//public Location findLocationWithStock(List<OrderDetail> orderDetails);
}
