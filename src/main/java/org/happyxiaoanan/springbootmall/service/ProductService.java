package org.happyxiaoanan.springbootmall.service;

import org.happyxiaoanan.springbootmall.constant.ProductCategory;
import org.happyxiaoanan.springbootmall.dto.ProductRequest;
import org.happyxiaoanan.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
