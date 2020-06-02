package com.giantdwarf.demomarket.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입_성공() throws Exception{
        mockMvc.perform(post("/member/signup")
                .param("memberId","yang")
                .param("password","12345")
                .param("name","양승인")
                .param("email","rhfpdk92@naver.com"))
                .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"))
        ;
    }

    @Test
    public void 회원가입_실패_제약조건_위반() throws Exception {
        mockMvc.perform(post("/member/signup")
                .param("memberId","yang2")
                .param("password","1234")
                .param("name","양승인")
                .param("email","rhfpdk12"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/signup"))
        ;
    }


}