package com.giantdwarf.demomarket.account;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    //회원가입
    @Transactional
    public Member join(MemberForm memberForm) {
        //저장
        Member newMember = saveMember(memberForm);
        newMember.generateToken();
        sendConfirmEmail(newMember);
        return newMember;
    }

    private void sendConfirmEmail(Member newMember) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newMember.getEmail());
        mailMessage.setSubject("본인 확인 인증");
        mailMessage.setText("/member/checktoken?email=" + newMember.getEmail() + "&token=" + newMember.getEmailToken());
        javaMailSender.send(mailMessage);
    }

    private Member saveMember(@Valid MemberForm memberForm) {
        Member newMember = Member.builder()
                .memberId(memberForm.getMemberId())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .email(memberForm.getEmail())
                .name(memberForm.getName())
                .updateDate(LocalDateTime.now())
                .joinedDate(LocalDateTime.now())
                .build();

        return memberRepository.save(newMember);
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
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserMember(member),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.println("token = " + SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("@@@#"+username);
        Member member = memberRepository.findByMemberId(username);
        if(member == null){
            throw new UsernameNotFoundException(username);
        }

        return new UserMember(member);
    }
    public void completeSignup(Member member) {
        member.completeSignup();

    }
}
