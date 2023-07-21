package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.ProductCategory;

import java.util.UUID;

public interface ProductCategoryService {
    public ProductCategory getProductCategoryById(UUID id);
    public ProductCategory createProductCategory(ProductCategory productCategory);
//    public ProductCategory getProductCategoryByName(String name);
    //public ProductCategory getProductCategory(Prdu)
}
