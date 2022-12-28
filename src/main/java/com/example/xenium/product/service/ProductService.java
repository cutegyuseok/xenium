package com.example.xenium.product.service;

import com.example.xenium.pocket.repository.PocketRepository;
import com.example.xenium.product.dto.Category;
import com.example.xenium.product.dto.Product;
import com.example.xenium.product.dto.ProductList;
import com.example.xenium.product.repository.ProductRepository;
import com.example.xenium.util.dto.Pagination;
import com.example.xenium.util.dto.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PocketRepository pocketRepository;

    public ProductList findAll(SearchDto dto){
        try {
            if (dto.getKeyword().equals("")) {
                Pagination pagination = new Pagination(productRepository.selectAllCount(), dto);
                dto.setPagination(pagination);
                List<Product> list = productRepository.selectProduct(dto);
                for (Product product:list){
                    int ordered=0;
                    if(pocketRepository.getProductOrderedAmount(product.getId())!=null){
                        ordered=pocketRepository.getProductOrderedAmount(product.getId());
                    }
                    String availAmount =String.valueOf(product.getAmount()-ordered);
                    product.setAvailAmount(availAmount);
                }
                return new ProductList(list, pagination);
            } else {
                Pagination pagination = new Pagination(productRepository.selectDistinctCount(dto), dto);
                dto.setPagination(pagination);
                List<Product> list = productRepository.selectDistinctProduct(dto);
                for (Product product:list){
                    int ordered=0;
                    if(pocketRepository.getProductOrderedAmount(product.getId())!=null){
                        ordered=pocketRepository.getProductOrderedAmount(product.getId());
                    }
                    String availAmount =String.valueOf(product.getAmount()-ordered);
                    product.setAvailAmount(availAmount);
                }
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

    public boolean checkAvailProductId(Object pocket,List<HashMap<String,String>> userCart){
        Map<String, List<LinkedHashMap<String, String>>> param = (Map<String, List<LinkedHashMap<String, String>>>) pocket;
        List<LinkedHashMap<String, String>> cartList = param.get("pocket");
        List<HashMap<String, Object>> idList = productRepository.checkAvailProductId();
        boolean success = true;
        for (LinkedHashMap<String,String> cart: cartList){
            String id = cart.get("id");
            String amount = String.valueOf(cart.get("amount"));
            boolean isAvail = false;
            //구매 가능 상품인지 확인
            for (HashMap<String,Object> availId:idList){
                if (String.valueOf(availId.get("id")).equals(id)){
                    //데이터베이스와 일치하는 정보인지
                    for (HashMap<String,String> dbCart : userCart){
                        String dbId = dbCart.get("id");
                        String dbAmount =String.valueOf(dbCart.get("amount"));

                        if (dbId.equals(id)&&dbAmount.equals(amount)){
                            isAvail = true;
                        }
                    }
                }
            }
            if (!isAvail)success = false;
        }
        return success;
    }
}
