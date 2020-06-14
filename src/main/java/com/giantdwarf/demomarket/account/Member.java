package com.giantdwarf.demomarket.account;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String memberId;

    private String password;

    private String name;

    @Column(unique = true)
    private String email;

    private LocalDateTime joinedDate;

    private LocalDateTime updateDate;

    private boolean emailAuthentication;

    private String emailToken;

    private LocalDateTime tokenGenerateDate;

    public void generateToken() {
        this.emailToken = UUID.randomUUID().toString();
        this.tokenGenerateDate = LocalDateTime.now();
    }

    public boolean isValidToken(String token) {
        return this.emailToken.equals(token);
    }

    public void completeSignup() {
        this.emailAuthentication = true;
        this.joinedDate = LocalDateTime.now();

    }
}
