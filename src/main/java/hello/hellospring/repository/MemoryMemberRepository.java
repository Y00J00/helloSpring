package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();// 멤버 저장
    private static long sequence = 0L; //키값 생성.

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findById(Long id) { //store에서 꺼내기
        return Optional.ofNullable(store.get(id)); // 
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny();
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버 셋팅
        store.put(member.getId(), member); // 이름은 이미 들어온상태
        return member;
    }
    
    public void clearStore() {
        store.clear();
    }
}
