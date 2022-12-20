package com.example.xenium.member.repository;

import com.example.xenium.mapper.MemberMapper;
import com.example.xenium.member.dto.SignUpDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @Autowired
    MemberMapper memberMapper;

    public int signup(SignUpDTO dto){
        return memberMapper.signup(dto);
    }

}
