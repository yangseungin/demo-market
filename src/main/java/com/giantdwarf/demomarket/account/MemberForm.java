package com.giantdwarf.demomarket.account;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class MemberForm {
//    @NotEmpty(message = "회원 id는 필수")
    private String memberId;

    private String password;

    private String name;

    private String email;
}
