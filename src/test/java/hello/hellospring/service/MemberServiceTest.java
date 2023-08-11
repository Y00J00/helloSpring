package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void BeforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //테스트는 서로 순서와 상관없이, 의존관계 없이 설계가 되어야한다.
    //하나의 테스트가 끝날때마다 저장소나 공용 데이터를 깔끔하게 지워줘야함
    @AfterEach //메소드가 끝날때마다 어떠한 동작을 한다
    public void afterEach() {
        memberRepository.clearStore();//테스트가 끝날대마다 repository를 비운다
    }
    
    @Test
    void testFindMembers() {

    }

    @Test
    void testFindeOne() {

    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findeOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());       

    }

    @Test
    public void  중복_회원_예약() {
        //given
        Member member1 =  new Member();
        member1.setName("spring");

        Member member2 =  new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //try catch 이용한 방법
        // try {
        //     memberService.join(member2);
        //     fail();// 예외가 발생해야함
        // } catch (IllegalStateException e) {
        //     Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");  
        // }

        //assertThrows 이용한 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //member2를 join시켰을때 IllegalStateException의 에러가 터져야함
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}
