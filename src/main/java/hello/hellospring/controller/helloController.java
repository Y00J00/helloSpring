package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class helloController {
    
    @GetMapping("hello") //http://localhost:8080/hello 에 매칭이 됨.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // resources/template/hello를 찾아서 렌더링
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    //기존에는  view resolver가 동작을 하지만 
    //@ResponseBody가 있으면 HttpMessageConverter가 작동한다
    //반환하는 것이 단순 스트링 -> stringConverter 작동
    //반환하는 것이 객체 -> JsonConverter 작동. 디폴트 json 방식으로 데이터를 만들어서 반환하겠다.
    @GetMapping("hello-string")
    @ResponseBody //http의 바디 부분에 이 데이터를 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; 
        //hello kim  이 문자가 요청한 클라이언트에 그대로 넘어감. 
        //helloMvc와 다른점 : view같은게 없고 이 문자 그대로 가는것.  
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //객체를 반환하고 ResponseBody하면 결과값이 디폴트 json으로 나옴
        //{"name":"spring!"} json으로 결과값 나옴
    }

    static class Hello {
        private String name;

        //자바빈 규약. 프로퍼티 접근 방식
        //private 이니까 밖에서 못꺼냄 => get set 메소드를 통해 접근
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        


    }




}
