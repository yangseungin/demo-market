package com.giantdwarf.demomarket.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmailAndName(String email, String name);

}
