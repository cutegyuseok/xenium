package com.example.xenium.product.service;

import com.example.xenium.product.dto.Category;
import com.example.xenium.product.dto.Product;
import com.example.xenium.product.dto.ProductList;
import com.example.xenium.product.repository.ProductRepository;
import com.example.xenium.util.dto.Pagination;
import com.example.xenium.util.dto.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductList findAll(SearchDto dto){
        try {
            if (dto.getKeyword().equals("")) {
                Pagination pagination = new Pagination(productRepository.selectAllCount(), dto);
                dto.setPagination(pagination);
                List<Product> list = productRepository.selectProduct(dto);
                return new ProductList(list, pagination);
            } else {
                Pagination pagination = new Pagination(productRepository.selectDistinctCount(dto), dto);
                dto.setPagination(pagination);
                List<Product> list = productRepository.selectDistinctProduct(dto);
                return new ProductList(list, pagination);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ProductList(Collections.EMPTY_LIST,null);
    }

    public List<Category>getCategories(){
        return productRepository.getCategories();
    }
}
