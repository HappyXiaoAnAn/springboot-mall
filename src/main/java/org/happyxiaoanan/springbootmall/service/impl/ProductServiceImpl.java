package org.happyxiaoanan.springbootmall.service.impl;

import org.happyxiaoanan.springbootmall.dao.ProductDao;
import org.happyxiaoanan.springbootmall.dto.ProductRequest;
import org.happyxiaoanan.springbootmall.model.Product;
import org.happyxiaoanan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }
}
