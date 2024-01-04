package org.happyxiaoanan.springbootmall.dao;

import org.happyxiaoanan.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
