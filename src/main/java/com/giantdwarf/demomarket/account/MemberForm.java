package com.giantdwarf.demomarket.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class MemberForm {
//    @NotBlank
    private String memberId;

//    @NotBlank
    private String password;

//    @NotBlank
    private String name;

//    @Email
//    @NotBlank
    private String email;
}
