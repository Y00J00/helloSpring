package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;


public class MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // memberRepository를 직접 new 해서 생성하는게 아닌 외부에서 넣어주게끔 바꾼다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 
    회원가입
    */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
        //Optional로 감싸면 Optional의 여러 메소드 사용 가능. 또한 null처리에 용이

        // 하지만 optional로 바로 반환하는건 좋지 않은 방법 (아래 방법)
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(m -> {
        //     throw new IllegalStateException("이미 존재하는 회원입니다.");
        // }); // 만약에 member 값이 있다면 throw

        validateDuplicateMember(member); //어차피 반환이 optional이여서 괜찮다.

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /** 
    전체 회원 조회
    */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findeOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
