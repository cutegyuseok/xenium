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

    public boolean addCartToDB(Object pocket,String uId){
        Map<String, List<LinkedHashMap<String, String>>> param = (Map<String, List<LinkedHashMap<String, String>>>) pocket;
        List<LinkedHashMap<String, String>> cartList = param.get("pocket");
        List<HashMap<String,String>> userCart = getUserCart(uId);
        ArrayList<String> notChanged = new ArrayList<>();
        ArrayList<String> presentID = new ArrayList<>();
        for(HashMap<String,String> compare:cartList){
            for (HashMap<String,String> org : userCart){
                //일치하는 품목인 경우
                if(compare.get("id").equals(org.get("id"))){
                    //품목의 수량이 같을 경우
                    String compAmount = String.valueOf(compare.get("amount"));
                    String compOrg = String.valueOf(org.get("amount"));
                    if (compAmount.equals(compOrg)){
                        notChanged.add(String.valueOf(compare.get("id")));
                        System.out.println(compare.get("name")+" 안바뀜");
                    }else {
                        presentID.add(compare.get("id"));
                    }
                }
            }
        }
        int updatedCNT =0;
        for (HashMap<String,String> updatedCart:cartList){
            boolean changed = true;
            String id=updatedCart.get("id");
            String amount = String.valueOf(updatedCart.get("amount"));
            Cart cart = new Cart(uId,id,amount);
            for (String num:notChanged){
                if(id.equals(num)) {
                changed =false;
                }
            }
            if (changed){
                boolean present = false;
                for(String num:presentID){
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

}
