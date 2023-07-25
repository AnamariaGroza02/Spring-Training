package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockId;

import java.util.UUID;

public interface StockService {
    public Stock updateStock(StockId stockId, Stock newStock, Integer newQuantity);

    public Stock getStockById(StockId id);
}
