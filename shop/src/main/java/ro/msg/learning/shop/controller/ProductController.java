package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductDtoMapper;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/shop/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(ProductDtoMapper.INSTANCE.toProductDto(product, product.getProductCategory()));
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        ProductCategory productCategory = product.getProductCategory();
        return new ResponseEntity<>(ProductDtoMapper.INSTANCE.toProductDto(product, productCategory), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product productEntity = ProductDtoMapper.INSTANCE.toProduct(productDto);
        ProductCategory productCategory = productEntity.getProductCategory();
        productCategoryService.createProductCategory(productCategory);
        Product product = productService.createProduct(productEntity);

        return new ResponseEntity<>(ProductDtoMapper.INSTANCE.toProductDto(product, productCategory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        Product productEntity = ProductDtoMapper.INSTANCE.toProduct(productDto);
        ProductCategory productCategory = ProductDtoMapper.INSTANCE.toProduct(productDto).getProductCategory();
        productCategoryService.createProductCategory(productCategory);
        Product product = productService.updateProduct(id, productEntity);

        return new ResponseEntity<>(ProductDtoMapper.INSTANCE.toProductDto(product, productCategory), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);

    }


}
