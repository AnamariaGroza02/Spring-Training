package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(UUID id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return new Product();
    }
    @Override
    public Product updateProduct(UUID id,Product newProduct){
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setDescription(newProduct.getDescription());
                    product.setWeight(newProduct.getWeight());
                    product.setProductCategory(newProduct.getProductCategory());
                    product.setImageUrl(newProduct.getImageUrl());
                    return productRepository.save(product);
                })
                .orElseGet(()->{
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }
    @Override
    public void deleteProduct(UUID id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
