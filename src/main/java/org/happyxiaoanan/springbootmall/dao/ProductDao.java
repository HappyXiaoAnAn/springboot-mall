package org.happyxiaoanan.springbootmall.dao;

import org.happyxiaoanan.springbootmall.dto.ProductRequest;
import org.happyxiaoanan.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
