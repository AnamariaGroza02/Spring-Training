package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockId;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {
    //    @Query("SELECT * FROM shop.stock WHERE product_id=?1 AND quantity = (SELECT MAX(quantity) from shop.stock AND quantity >= ?2 LIMIT 1;")
    @Query("SELECT s FROM Stock s WHERE s.productId=?1 AND s.quantity>= ?2 ORDER BY quantity DESC LIMIT 1")
//    @Query("SELECT * FROM Stock WHERE productId=?1 AND quantity = (SELECT MAX(quantity) from Stock where quantity>= ?2) LIMIT 1;")
    Stock findStockWithMaxProductStock(UUID productID, Integer quantity);

    @Query("SELECT s FROM Stock s WHERE s.productId=?1 AND s.quantity>= ?2 ORDER BY s.locationId")
    List<Stock> findStocksSameLocationApproach(UUID productID, Integer quantity);

}
