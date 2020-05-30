package com.giantdwarf.demomarket.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;



@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
//    @Rollback(false)
    public void jpa_db_접근() throws Exception {
        //given
        Member joinedMember = Member.builder().memberId("yang")
                .password("1234")
                .name("양승인")
                .email("rhfpdk9@naver.com")
                .joinedDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        System.out.println("-----------2");

        //when
        memberRepository.save(joinedMember);

        //then
        Assertions.assertEquals(joinedMember,memberRepository.findAll().get(0));
    }



}