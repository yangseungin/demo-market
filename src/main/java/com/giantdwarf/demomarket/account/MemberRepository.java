package com.giantdwarf.demomarket.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmailAndName(String email, String name);

    boolean existsByMemberId(String memberId);

    boolean existsByEmail(String email);

    Member findByEmail(String email);

    Member findByMemberId(String memberId);

}
