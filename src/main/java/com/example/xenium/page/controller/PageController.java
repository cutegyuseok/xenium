package com.example.xenium.page.controller;

import com.example.xenium.aop.NonLogin;
import com.example.xenium.product.dto.ProductList;
import com.example.xenium.product.service.ProductService;
import com.example.xenium.util.dto.SearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Api(tags = {"페이지 서비스"},description = "페이지 관련 서비스")
public class PageController {


    @Autowired
    ProductService ps;

    @ApiOperation(value = "메인페이지", notes = "메인페이지(index) 이동")
    @GetMapping("/")
    public String mainPage(HttpSession session) {
        session.setAttribute("categories",ps.getCategories());
        return "index";
    }

    @ApiOperation(value = "회원가입 페이지", notes = "회원가입 페이지 이동")
    @GetMapping("/signupPage")
    @NonLogin
    public String signupPage() {
        return "user/signup";
    }

    @ApiOperation(value = "사용자 로그인 페이지", notes = "사용자 로그인 페이지 이동")
    @GetMapping("/loginPage")
    @NonLogin
    public String loginPage() {
        return "user/login";
    }

    @ApiOperation(value = "가게정보 페이지", notes = "가게 정보 페이지 이동")
    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }

    @ApiOperation(value = "상품 목록 페이지", notes = "상품 목록 페이지 이동")
    @GetMapping("/productList")
    public String productList(@RequestParam String searchWord, SearchDto params, Model model) {
        params.setKeyword(searchWord);
        ProductList product = ps.findAll(params);
        model.addAttribute("product", product);
        String key=params.getKeyword();
        if(key!=null){
            model.addAttribute("keyword", params.getKeyword());
        }
        return "productlist";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("GET방식으로 로그아웃");
        session.setAttribute("id",null);
        return "index";
    }
}
