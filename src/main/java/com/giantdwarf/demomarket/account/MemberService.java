package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public Member join(@Valid MemberForm memberForm) {
        //패스워드 암호화


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
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
//    public Member findOne(Long memberId){
//        return memberRepository.findOne(memberId);
//    }
    public void login(Member member) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getMemberId(),
                member.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            System.out.println("principal = " + ((UserDetails) principal).getUsername());
        }else{
            System.out.println("principal2 = " + principal.toString()); //??? 왜 여기로 빠지는가
        }
        System.out.println("!@#!@"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }
}
