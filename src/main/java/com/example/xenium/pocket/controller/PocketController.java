package com.example.xenium.pocket.controller;

import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.pocket.service.PocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PocketController {

    @Autowired
    PocketService pocketService;

    @RequestMapping("/addCart")
    public @ResponseBody String addCart(@RequestBody(required = false) Object pocket, HttpSession session) {
        if (session.getAttribute("id")==null)return "loginFail";
        SignUpDTO user=(SignUpDTO) session.getAttribute("id");
        pocketService.updateCartInDB(pocket,user.getId());
        return "success";
    }
    @PostMapping("/selectAvailAmountCart")//상품id로 최대 구매 가능수량 확인
    public String selectAvailAmountCart(String id) {
    String fragment = String.valueOf(pocketService.selectAvailAmountCart(id));
        return fragment;
    }

    @PostMapping("/deleteCart")
    public String deleteCart(String id,@ApiIgnore HttpSession session){
        SignUpDTO user=(SignUpDTO) session.getAttribute("id");
        if(pocketService.deleteCart(id,user.getId())){
            return "success";
        }
        return "failed";
    }
}
