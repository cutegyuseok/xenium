package com.example.xenium.mapper;

import com.example.xenium.product.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    public List<Product> selectProduct();
}
