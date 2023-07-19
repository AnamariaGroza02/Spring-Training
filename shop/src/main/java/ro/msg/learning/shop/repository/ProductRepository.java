package ro.msg.learning.shop.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
