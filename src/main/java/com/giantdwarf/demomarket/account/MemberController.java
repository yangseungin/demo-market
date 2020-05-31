package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/signup")
    public String signupForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/signup";
    }

    @PostMapping("/member/signup")
    public String creatae(@Valid MemberForm form, BindingResult result) {

        if(result.hasErrors()){
            return "member/signup";
        }

        //패스워드 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        Member member = Member.builder().memberId(form.getMemberId())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .name(form.getName())
                .updateDate(LocalDateTime.now())
                .joinedDate(LocalDateTime.now())
                .build();
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("member/list")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "member/memberList";
    }


}
