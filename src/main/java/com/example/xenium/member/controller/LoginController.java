package com.example.xenium.member.controller;

import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(tags = {"사용자 로그인 서비스"},description = "사용자 로그인 관련 서비스")
@RestController
public class LoginController {

    @Autowired
    MemberService memberService;

    @Autowired
    HttpSession session;

    @ApiOperation(value = "사용자 로그인", notes = "사용자 로그인 시도")
    @PostMapping("/login")
    public String login(LoginDTO dto) {
        SignUpDTO info = memberService.login(dto);
        if (info != null) {
            session.setAttribute("id", info);
            return "LOGIN_SUCCESS";
        } else {
            return "LOGIN_FAILED";
        }
    }

    @ApiOperation(value = "사용자 회원가입", notes = "사용자 회원가입 시도")
    @PostMapping("/signup")
    public int signup(SignUpDTO dto){
        return memberService.signup(dto);
    }

    @ApiOperation(value = "사용자 로그아웃", notes = "사용자 로그아웃 시도")
    @PostMapping("/logout")
    public String logout(){
        if(session.getAttribute("id")!=null) {
            session.setAttribute("id", null);
            return "success";
        }else {
            return "fail";
        }
    }

    @PostMapping("/checkLogin")
    public String checkLogin(){
        if(session.getAttribute("id")!=null){
            return "Login";
        }else return "need";
    }


}
