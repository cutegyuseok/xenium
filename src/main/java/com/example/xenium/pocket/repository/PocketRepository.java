package com.example.xenium.pocket.repository;

import com.example.xenium.mapper.PocketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PocketRepository {


    @Autowired
    PocketMapper pm;

    public List<HashMap<String,Object>> getUserCart(String userid){
        return pm.getUserCart(userid);
    }
}
