package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.util.LocationProductQuantityEntity;

import java.util.ArrayList;
import java.util.List;

public class FindMostAbundantLocationStrategy implements FindLocationStrategy {
    private final ProductService productService;
    private final LocationService locationService;
//    private final StockRepository stockRepository;

    private final StockService stockService;
    public FindMostAbundantLocationStrategy(ProductService productService, LocationService locationService, StockService stockService) {
        this.productService = productService;
        this.locationService = locationService;
//        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }

    @Override
    public List<LocationProductQuantityEntity> findLocationWithStock(List<OrderDetail> orderDetails) {
        List<LocationProductQuantityEntity> listOfLocationProductQuantity = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            final Stock stock = stockService.findStockWithMaxProductStock(orderDetail.getProduct().getId(),orderDetail.getQuantity());
//            final Stock stock = stockService.findLocationWithMaxProductStock(orderDetail.getProduct().getId(), orderDetail.getQuantity());
            if (stock != null) {
                final LocationProductQuantityEntity locationProductQuantityEntity = LocationProductQuantityEntity.builder()
                        .location(stock.getLocation())
                        .product(stock.getProduct())
                        .quantity(orderDetail.getQuantity())
                        .build();
                listOfLocationProductQuantity.add(locationProductQuantityEntity);
            }
            // eroare daca nu gaseste
        }
        return listOfLocationProductQuantity;
    }
}
