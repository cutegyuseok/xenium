package com.example.xenium.pocket.service;

import com.example.xenium.pocket.dto.Cart;
import com.example.xenium.pocket.repository.PocketRepository;
import com.example.xenium.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PocketService {

    @Autowired
    PocketRepository pr;

    @Autowired
    ProductRepository productRepository;

    public List<HashMap<String,String>> getUserCart(String userId){
        List<HashMap<String, String>> par = new ArrayList<HashMap<String, String>>();
        List<HashMap<String, Object>> cList=pr.getUserCart(userId);
        for (Map<String, Object> component : cList) {
            HashMap<String, String> comMap = new HashMap<String, String>();
            comMap.put("id", String.valueOf(component.get("pid")));
            comMap.put("amount", String.valueOf(component.get("amount")));
            comMap.put("name", String.valueOf(component.get("productName")));
            comMap.put("price", String.valueOf(component.get("price")));
            par.add(comMap);
        }
        return par;
    }


    public int selectAvailAmountCart(String pId){
        int amount = productRepository.getProductAmount(pId);
        int ordered=0;
        if(pr.getProductOrderedAmount(pId)!=null){
            ordered=pr.getProductOrderedAmount(pId);
        }
        return amount-ordered;
    }

    public boolean updateCartInDB(Object pocket,String uId){
        Map<String, List<LinkedHashMap<String, String>>> param = (Map<String, List<LinkedHashMap<String, String>>>) pocket;
        List<LinkedHashMap<String, String>> cartList = param.get("pocket");
        List<HashMap<String,String>> userCart = getUserCart(uId);
        ArrayList<String> unchangedIds = new ArrayList<>();
        ArrayList<String> presentIds = new ArrayList<>();

        cartList.stream().forEach(compare -> {
            userCart.stream().forEach(org -> {
                if (compare.get("id").equals(org.get("id"))) {
                    String compareAmount = String.valueOf(compare.get("amount"));
                    String originalAmount = String.valueOf(org.get("amount"));
                    if (compareAmount.equals(originalAmount)) {
                        unchangedIds.add(compare.get("id"));
                    } else {
                        presentIds.add(compare.get("id"));
                    }
                }
            });
        });
        int updatedCNT =0;
        for (HashMap<String,String> updatedCart:cartList){
            boolean changed = true;
            String id=updatedCart.get("id");
            String amount = String.valueOf(updatedCart.get("amount"));
            Cart cart = new Cart(uId,id,amount);
            for (String num:unchangedIds){
                if(id.equals(num)) {
                changed =false;
                }
            }
            if (changed){
                boolean present = false;
                for(String num:presentIds){
                    if (id.equals(num)){
                        present=true;
                    }
                }
                if (present){
                    if(amount.equals("0")){
                            updatedCNT+=pr.deleteCart(cart);
                        }else {
                            updatedCNT+=pr.updateCart(cart);
                        }
                }else {
                    if(!amount.equals("0")) {
                        updatedCNT += pr.insertCart(cart);
                    }
                }
            }
        }
        return updatedCNT>0;
    }


    public boolean deleteCart(String id,String uId){
        Cart cart = new Cart(uId,id,"0");
        return pr.deleteCart(cart)>0;
    }


}
