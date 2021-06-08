package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){   //model을 담으면 뷰에서 렌더링할때 쓴다.
        model.addAttribute("name",name);        // 파라미터로 넘어온 name을 넘겨보자 "name"이 key이고 name이 value이다.
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody                                                            //http에서 body부에서 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " +name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{     //자바빈 규약 private이어서 바로 못꺼내는데 method로 꺼낸다. java Bean 표준 방식
        private String name;

        public String getName() {       //Property 접근방식
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }
    }


}
