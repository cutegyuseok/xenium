package com.example.xenium.member.service;

import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.Order;
import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public int signup(SignUpDTO dto) {
        try {
            return memberRepository.signup(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public SignUpDTO login(LoginDTO dto) {
        try {
            return memberRepository.login(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public boolean buy(Object pocket,SignUpDTO user){
        boolean success = true;
        Order order =new Order(user);
        memberRepository.insertOrderBook(order);
        int buyId= order.getPreid();
        Map<String, List<LinkedHashMap<String, String>>> param = (Map<String, List<LinkedHashMap<String, String>>>) pocket;
        List<LinkedHashMap<String, String>> cartList = param.get("pocket");
        for (LinkedHashMap<String,String> cart : cartList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",buyId);
            map.put("userid",user.getId());
            map.put("pid",cart.get("id"));
            map.put("amount",cart.get("amount"));
            map.put("price",cart.get("price"));
            if(memberRepository.insertOrderDetail(map)<=0)success=false;
        }
        if (success){
            memberRepository.deleteCart(user.getId());
        }
        return success;
    }
}
