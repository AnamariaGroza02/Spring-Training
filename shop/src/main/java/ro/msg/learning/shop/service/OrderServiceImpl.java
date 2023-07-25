package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PlacedOrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private StockService stockService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public PlacedOrder createOrder(PlacedOrder order) {
        List<OrderDetail> orderDetails = order.getOrderDetails();
        Location location = findLocationWithStock(orderDetails);

        if (location == null) {
            throw new RuntimeException("No location with required stock for all products");
        }

        updateStockForLocation(location, orderDetails);

        order.setCreatedAt(LocalDateTime.now());
        order.setAddress(order.getAddress());
        order.setOrderDetails(new ArrayList<>());

        PlacedOrder savedOrder = orderRepository.save(order);

        List<OrderDetail> newOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setPlacedOrder(savedOrder);
            orderDetail.setShippedFrom(location);
            newOrderDetails.add(orderDetailService.createOrderDetail(orderDetail));
        }
        savedOrder.setOrderDetails(newOrderDetails);
        return orderRepository.save(savedOrder);
    }

    private void updateStockForLocation(Location location, List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            Product product = productService.getProductById(orderDetail.getProduct().getId());
            StockId stockId = new StockId(product.getId(), location.getId());
            Stock stock = stockService.getStockById(stockId);
            stockService.updateStock(stockId, stock, stock.getQuantity() - orderDetail.getQuantity());

        }

    }

    private Location findLocationWithStock(List<OrderDetail> orderDetails) {
        Map<UUID, Integer> locationAppearance = new HashMap<>();
        for (OrderDetail orderDetail : orderDetails) {
            Product product = productService.getProductById(orderDetail.getProduct().getId());
            List<Stock> stocksForProduct = product.getStocks();
            Integer quantityToOrder = orderDetail.getQuantity();
            for (Stock stock : stocksForProduct) {
                if (stock.getQuantity() > quantityToOrder) {
                    UUID key = stock.getLocation().getId();
                    if (locationAppearance.containsKey(key)) {
                        locationAppearance.replace(key, locationAppearance.get(key) + 1);
                    } else {
                        locationAppearance.put(key, 1);
                    }
                }

            }
        }
        for (Map.Entry<UUID, Integer> locationFrequency : locationAppearance.entrySet()) {
            if (locationFrequency.getValue() == orderDetails.size())
                return locationService.getLocationById(locationFrequency.getKey());

        }

        return null;
    }
}
