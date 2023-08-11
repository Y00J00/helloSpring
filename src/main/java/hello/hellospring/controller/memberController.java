package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class memberController {

    //@Autowired 필드 주입. 스프링 뜰때만 넣어주고 중간에 바꿀수 있는 방법이 없다.
    private  MemberService memberService;

    @Autowired // 생성자 주입 => 가장 권장
    public memberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // getMapping: 기본적으로 url창에 치고 엔터 누르는게 getMapping.데이터 조회할때 사용
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    // PostMapping : 데이터를 폼에 넣어서 전달할떄 사용
    @PostMapping("/members/new")
    public String createMember(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
   
}


 // @Autowired // setter 을 통한 주입 . 꼭 public 이여야한다. -> 아무 개발자나 호출할수 있게 열려있는꼴
    // public void setSetMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }

    //스프링 컨테이너가 뜰때 멤버컨트롤러 생성 -> 생성자 호출
    // -> 생성자가  @Autowired가 있기 때문에 스프링 컨테이너에 있는 MemberService와 연결
    // -> dependency injection  
    
    // 인젝션하는 3가지 방법
    // 1. 생성자 주입 -> 가장 권장
    // 2. 필드 주입
    // 3. setter을 통한 주입
