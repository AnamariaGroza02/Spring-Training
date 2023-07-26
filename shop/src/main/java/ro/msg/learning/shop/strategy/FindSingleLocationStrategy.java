package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.util.LocationProductQuantityEntity;

import java.util.*;

public class FindSingleLocationStrategy implements FindLocationStrategy {

    private final ProductService productService;
    private final LocationService locationService;
    private final StockService stockService;

    public FindSingleLocationStrategy(ProductService productService, LocationService locationService, StockService stockService) {
        this.productService = productService;
        this.locationService = locationService;
        this.stockService = stockService;
    }

    @Override
    public List<LocationProductQuantityEntity> findLocationWithStock(List<OrderDetail> orderDetails) {
        Map<UUID, Integer> locationAppearance = new HashMap<>();
        List<LocationProductQuantityEntity> listOfLocationProductQuantity = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            Product product = productService.getProductById(orderDetail.getProduct().getId());
            final List<Stock> stocksForProduct = stockService.findStocksSameLocationApproach(orderDetail.getProduct().getId(), orderDetail.getQuantity());

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
        for (OrderDetail orderDetail : orderDetails) {
            Location locationForEveryProduct = findLocation(locationAppearance,orderDetails);
            if (locationForEveryProduct != null) {
                StockId stockId = new StockId(orderDetail.getProduct().getId(),locationForEveryProduct.getId());
                Stock stock = stockService.getStockById(stockId);
                final LocationProductQuantityEntity locationProductQuantityEntity = LocationProductQuantityEntity.builder()
                        .location(locationForEveryProduct)
                        .product(stock.getProduct())
                        .quantity(orderDetail.getQuantity())
                        .build();
                listOfLocationProductQuantity.add(locationProductQuantityEntity);
            }
            // eroare daca nu gaseste
        }
        return listOfLocationProductQuantity;
    }

    public Location findLocation(Map<UUID, Integer> locationAppearance, List<OrderDetail> orderDetails) {
        for (Map.Entry<UUID, Integer> locationFrequency : locationAppearance.entrySet()) {
            if (locationFrequency.getValue() == orderDetails.size())
                return locationService.getLocationById(locationFrequency.getKey());

        }
        return null;
    }
}
