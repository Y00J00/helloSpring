package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.hellospring.service.MemberService;

@Controller
public class memberController {

    private final MemberService memberService;

    @Autowired
    public memberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //스프링 컨테이너가 뜰때 멤버컨트롤러 생성 -> 생성자 호출
    // -> 생성자가  @Autowired가 있기 때문에 스프링 컨테이너에 있는 MemberService와 연결
    // -> dependency injection    
}
