package com.giantdwarf.demomarket.account;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String memberId;

    private String password;

    private String name;

    @Column(unique = true)
    private String email;

    private LocalDateTime joinedDate;

    private LocalDateTime updateDate;

}
