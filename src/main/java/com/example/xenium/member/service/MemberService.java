package com.example.xenium.member.service;

import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
