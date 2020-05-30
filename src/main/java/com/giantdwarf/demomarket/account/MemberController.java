package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/member/signup")
    public String signupForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/signup";
    }

    @PostMapping("/member/signup")
    public String creatae(@Valid MemberForm form){
        Member member = Member.builder().memberId(form.getMemberId())
                .password(form.getPassword())
                .email(form.getEmail())
                .name(form.getName())
                .updateDate(LocalDateTime.now())
                .joinedDate(LocalDateTime.now())
                .build();
        memberService.join(member);
        return "redirect:/";
    }



}
