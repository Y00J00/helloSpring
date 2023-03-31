package hello.hellospring.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 서로 순서와 상관없이, 의존관계 없이 설계가 되어야한다.
    //하나의 테스트가 끝날때마다 저장소나 공용 데이터를 깔끔하게 지워줘야함

    @AfterEach //메소드가 끝날때마다 어떠한 동작을 한다
    public void afterEach() {
        repository.clearStore();//테스트가 끝날대마다 repository를 비운다
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result =  repository.findById(member.getId()).get();
        System.out.println("result =" + (result == member) );
        Assertions.assertEquals(member,result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertEquals(member1,result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        List<Member> result = repository.findAll();
        Assertions.assertEquals(2, result.size());

    }
}
