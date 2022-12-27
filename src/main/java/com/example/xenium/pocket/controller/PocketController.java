package com.example.xenium.pocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PocketController {


    @RequestMapping("/addCart")
    public @ResponseBody String addCart(@RequestBody(required = false) Object pocket, HttpSession session) {
        return "success";
    }
    @PostMapping("/selectAvailAmountCart")
    public String selectAvailAmountCart(String id) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", id);
        System.out.println(id.toString());
//        int result = ms.selectAvailAmount(data);
        return 1 + "";
    }
}
