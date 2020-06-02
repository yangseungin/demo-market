package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Member join(@Valid MemberForm memberForm){
        //패스워드 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = Member.builder().memberId(memberForm.getMemberId())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .email(memberForm.getEmail())
                .name(memberForm.getName())
                .updateDate(LocalDateTime.now())
                .joinedDate(LocalDateTime.now())
                .build();

        return memberRepository.save(member);
    }


    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
//    public Member findOne(Long memberId){
//        return memberRepository.findOne(memberId);
//    }
}
