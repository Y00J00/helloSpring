package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //optional -> null일경우 처리하는것.
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
