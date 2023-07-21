package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    public Product createProduct(Product product);
    public Product getProductById(UUID id);
    public Product updateProduct(UUID id,Product product);
    public void deleteProduct(UUID product);
    public List<Product> getProducts();

}
