package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.Optional;
import java.util.UUID;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategory getProductCategoryById(UUID id){
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if (productCategory.isPresent()) {
            return productCategory.get();
        }
        return new ProductCategory();
    }

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

//    @Override
//    public ProductCategory getProductCategoryByName(String name) {
//        Optional<ProductCategory> productCategory = productCategoryRepository.findBy
//    }
}
