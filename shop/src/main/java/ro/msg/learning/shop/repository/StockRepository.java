package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockId;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {

}
