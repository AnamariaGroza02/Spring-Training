package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.PlacedOrder;

import java.util.UUID;

@Repository
public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, UUID> {
}
