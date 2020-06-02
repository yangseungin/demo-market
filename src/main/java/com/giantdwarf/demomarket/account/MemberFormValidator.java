package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberFormValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;
        if(memberRepository.existsByMemberId(memberForm.getMemberId())){
            errors.rejectValue("memberId","wrongValue","이미 사용중인 아이디");
        }
        if(memberRepository.existsByEmail(memberForm.getEmail())){
            errors.rejectValue("email","wrongValue","이미 사용중인 이메일");

       }

    }
}
