package com.example.xenium.member.repository;

import com.example.xenium.mapper.MemberMapper;
import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.Order;
import com.example.xenium.member.dto.SignUpDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MemberRepository {

    @Autowired
    MemberMapper memberMapper;

    public int signup(SignUpDTO dto){
        return memberMapper.signup(dto);
    }

    public SignUpDTO login(LoginDTO dto){
        return memberMapper.login(dto);
    }

    public int insertOrderBook(Order dto){
        return memberMapper.insertOrderBook(dto);
    }
    public int insertOrderDetail(HashMap<String,Object> map){
        return memberMapper.insertOrderDetail(map);
    }

    public int deleteCart(String id){
        return memberMapper.deleteCart(id);
    }

}
