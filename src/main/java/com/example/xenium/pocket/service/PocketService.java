package com.example.xenium.pocket.service;

import com.example.xenium.pocket.repository.PocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PocketService {

    @Autowired
    PocketRepository pr;

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
        System.out.println(par.toString());
        return par;
    }
}
