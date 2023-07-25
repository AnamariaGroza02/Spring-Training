package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.PlacedOrder;

public interface OrderService {
    public PlacedOrder createOrder(PlacedOrder order);
}
