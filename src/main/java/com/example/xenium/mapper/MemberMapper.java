package com.example.xenium.mapper;

import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.Order;
import com.example.xenium.member.dto.SignUpDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MemberMapper {

    public int signup(SignUpDTO dto);
    public SignUpDTO login(LoginDTO dto);

    public int insertOrderBook(Order order);

    public int insertOrderDetail(HashMap<String,Object> map);

    public int deleteCart(String id);
}
