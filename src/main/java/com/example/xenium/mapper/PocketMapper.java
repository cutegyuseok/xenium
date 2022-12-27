package com.example.xenium.mapper;

import com.example.xenium.pocket.dto.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PocketMapper {

    public List<HashMap<String,Object>> getUserCart(String userid);

    public Integer getProductOrderedAmount(String pId);

    public int updateCart(Cart cart);
    public int insertCart(Cart cart);
    public int deleteCart(Cart cart);
}
