package com.example.xenium.member.controller;

import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.member.service.MemberService;
import com.example.xenium.pocket.service.PocketService;
import com.example.xenium.product.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@RestController
@Api(tags = {"사용자 서비스"},description = "사용자 관련 서비스")
public class MemberController {

    @Autowired
    ProductService productService;
    @Autowired
    PocketService pocketService;

    @Autowired
    MemberService memberService;
    @PostMapping("/confirmBuying")
    public String confirmBuying(@RequestBody(required = false) Object pocket, @ApiIgnore HttpSession session){
        if (session.getAttribute("id")==null)return "loginFail";
        SignUpDTO user = (SignUpDTO)session.getAttribute("id");
        if (productService.checkAvailProductId(pocket,pocketService.getUserCart(user.getId()))){
            return "success";
        }
        return "notAvail";
    }

    @PostMapping("/confirmRest")
    public String confirmRest(@RequestBody(required = false) Object pocket, HttpSession session){
        if(memberService.buy(pocket,(SignUpDTO)session.getAttribute("id"))){
            return "success";
        }
        return "failed";
    }

}
