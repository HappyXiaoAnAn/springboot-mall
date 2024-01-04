package org.happyxiaoanan.springbootmall.service;

import org.happyxiaoanan.springbootmall.dto.ProductQueryParams;
import org.happyxiaoanan.springbootmall.dto.ProductRequest;
import org.happyxiaoanan.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
