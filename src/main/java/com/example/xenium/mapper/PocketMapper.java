package com.example.xenium.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PocketMapper {

    public List<HashMap<String,Object>> getUserCart(String userid);
}
