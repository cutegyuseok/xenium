package com.example.xenium.member.service;

import com.example.xenium.member.dto.SignUpDTO;
import com.example.xenium.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public int signup(SignUpDTO dto){
        return memberRepository.signup(dto);
    }
}
