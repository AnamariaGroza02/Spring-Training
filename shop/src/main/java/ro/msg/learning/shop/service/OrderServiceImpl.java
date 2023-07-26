package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.PlacedOrderRepository;
import ro.msg.learning.shop.strategy.FindLocationStrategy;
import ro.msg.learning.shop.util.LocationProductQuantityEntity;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {



    private PlacedOrderRepository orderRepository;

    private ProductService productService;

    private LocationService locationService;

    private StockService stockService;

    private OrderDetailService orderDetailService;

    private FindLocationStrategy findLocationStrategy;

    public OrderServiceImpl(PlacedOrderRepository orderRepository, ProductService productService, LocationService locationService, StockService stockService, OrderDetailService orderDetailService, FindLocationStrategy findLocationStrategy) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.locationService = locationService;
        this.stockService = stockService;
        this.orderDetailService = orderDetailService;
        this.findLocationStrategy = findLocationStrategy;
    }


    @Override
    public PlacedOrder createOrder(PlacedOrder order) {
        List<OrderDetail> orderDetails = order.getOrderDetails();
//        Location location = findLocationStrategy.findLocationWithStock(orderDetails);
        List<LocationProductQuantityEntity> locationProductQuantityEntityList = findLocationStrategy.findLocationWithStock(orderDetails);


//        if (location == null) {
//            throw new RuntimeException("No location with required stock for all products");
//        }

//        updateStockForLocation(location, orderDetails);
        updateStockForProducts(locationProductQuantityEntityList);

        order.setCreatedAt(LocalDateTime.now());
        order.setAddress(order.getAddress());
        order.setOrderDetails(new ArrayList<>());

        PlacedOrder savedOrder = orderRepository.save(order);

        List<OrderDetail> newOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setPlacedOrder(savedOrder);
//            Stock stockWithProductIdAndQuantity = stockService.findStockWithMaxProductStock(orderDetail.getProduct().getId(),orderDetail.getQuantity());
//            orderDetail.setShippedFrom(stockWithProductIdAndQuantity.getLocation());
            orderDetail.setShippedFrom(getLocation(locationProductQuantityEntityList,orderDetail));
            newOrderDetails.add(orderDetailService.createOrderDetail(orderDetail));
        }
        savedOrder.setOrderDetails(newOrderDetails);
        return orderRepository.save(savedOrder);
    }

    private void updateStockForProducts(List<LocationProductQuantityEntity> locationProductQuantityEntityList) {
        for (LocationProductQuantityEntity locationProductQuantityEntity : locationProductQuantityEntityList) {
            StockId stockId = new StockId(locationProductQuantityEntity.getProduct().getId(), locationProductQuantityEntity.getLocation().getId());
            Stock stock = stockService.getStockById(stockId);
            stockService.updateStock(stockId, stock, stock.getQuantity() - locationProductQuantityEntity.getQuantity());
        }

    }
    private Location getLocation(List<LocationProductQuantityEntity> locationProductQuantityEntityList,OrderDetail orderDetail){
        for (LocationProductQuantityEntity locationProductQuantityEntity : locationProductQuantityEntityList){
            if (locationProductQuantityEntity.getProduct().getId().equals(orderDetail.getProduct().getId()))
                return locationProductQuantityEntity.getLocation();
        }
        return null;

    }


//    private void updateStockForLocation(Location location, List<OrderDetail> orderDetails) {
//        for (OrderDetail orderDetail : orderDetails) {
//            Product product = productService.getProductById(orderDetail.getProduct().getId());
//            StockId stockId = new StockId(product.getId(), location.getId());
//            Stock stock = stockService.getStockById(stockId);
//            stockService.updateStock(stockId, stock, stock.getQuantity() - orderDetail.getQuantity());
//
//        }
//
//    }
//
//
//    private Location findLocationWithStock(List<OrderDetail> orderDetails) {
//        Map<UUID, Integer> locationAppearance = new HashMap<>();
//        for (OrderDetail orderDetail : orderDetails) {
//            Product product = productService.getProductById(orderDetail.getProduct().getId());
//            List<Stock> stocksForProduct = product.getStocks();
//
//            Integer quantityToOrder = orderDetail.getQuantity();
//            for (Stock stock : stocksForProduct) {
//                if (stock.getQuantity() > quantityToOrder) {
//                    UUID key = stock.getLocation().getId();
//                    if (locationAppearance.containsKey(key)) {
//                        locationAppearance.replace(key, locationAppearance.get(key) + 1);
//                    } else {
//                        locationAppearance.put(key, 1);
//                    }
//                }
//
//            }
//        }
//        for (Map.Entry<UUID, Integer> locationFrequency : locationAppearance.entrySet()) {
//            if (locationFrequency.getValue() == orderDetails.size())
//                return locationService.getLocationById(locationFrequency.getKey());
//
//        }
//
//        return null;
//    }




}
