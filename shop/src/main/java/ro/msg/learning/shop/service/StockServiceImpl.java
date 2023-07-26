package ro.msg.learning.shop.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockId;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock updateStock(StockId stockId, Stock newStock, Integer newQuantity) {
        return stockRepository.findById(stockId).map(stock -> {
            stock.setProductId(newStock.getProductId());
            stock.setProduct(newStock.getProduct());
            stock.setLocationId(newStock.getLocationId());
            stock.setLocation(newStock.getLocation());
            stock.setQuantity(newQuantity);
            return stockRepository.save(stock);
        }).get();
    }

    @Override
    public Stock getStockById(StockId id) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
            return stock.get();
        }
        return new Stock();
    }

    @Override
    public Stock findStockWithMaxProductStock(UUID productID, Integer quantity){
        return stockRepository.findStockWithMaxProductStock(productID,quantity);
    }

    @Override
    public List<Stock> findStocksSameLocationApproach(UUID productID, Integer quantity){
        return stockRepository.findStocksSameLocationApproach(productID,quantity);
    }
}
