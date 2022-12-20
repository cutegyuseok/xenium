package com.example.xenium.member.controller;

import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"사용자 로그인 서비스"},description = "사용자 로그인 관련 서비스")
@RestController
public class LoginController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "사용자 로그인", notes = "사용자 로그인 시도")
    @PostMapping("/login")
    public String login(LoginDTO dto){
        return "LOGIN_SUCCESS";
    }

    @PostMapping("/signup")
    public int signup(SignUpDTO dto){
        return memberService.signup(dto);
    }
}
