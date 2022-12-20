package com.example.xenium.mapper;

import com.example.xenium.member.dto.LoginDTO;
import com.example.xenium.member.dto.SignUpDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public int signup(SignUpDTO dto);
    public SignUpDTO login(LoginDTO dto);
}
