package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;
    private final MemberFormValidator memberFormValidator;
    private final MemberRepository memberRepository;

    @InitBinder
    public void initMemberBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberFormValidator);
    }

    @GetMapping("/member/signup")
    public String signupForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/signup";
    }

    @PostMapping("/member/signup")
    public String creatae(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "member/signup";
        }

        Member joinedMember = memberService.join(memberForm);
//        memberService.login(joinedMember);

        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "member/memberList";
    }

    @GetMapping("/member/forgot")
    public String forgot(Model model) {


        return "member/forgot";
    }

    @GetMapping("/member/checktoken")
    public String checkToken(Model model, String email, String token) {
        Member joindMember = memberRepository.findByEmail(email);
        String view = "member/checkedEmail";
        if (joindMember == null){
            model.addAttribute("error", "emailError");
            return view;
        }

        if (!joindMember.isValidToken(token)) {
            model.addAttribute("error", "tokenError");
            return view;
        }
        joindMember.completeSignup();
        memberService.login(joindMember);


        model.addAttribute("name", joindMember.getName());

        return view;
    }


}
