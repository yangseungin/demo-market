package com.giantdwarf.demomarket.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class MemberForm {
    @NotBlank
    @Length(min = 3, max = 20)
    private String memberId;

    @NotBlank
    @Length(min = 5, max = 20)
    private String password;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
}
