package com.example.xenium.product.repository;

import com.example.xenium.mapper.ProductMapper;
import com.example.xenium.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {


    @Autowired
    ProductMapper productMapper;

    public List<Product> selectProduct(){
        return productMapper.selectProduct();
    }

}
