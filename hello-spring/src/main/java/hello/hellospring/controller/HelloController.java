package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
