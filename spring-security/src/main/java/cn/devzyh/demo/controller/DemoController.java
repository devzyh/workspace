package cn.devzyh.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "Hello Spring Security";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello User";
    }

    @GetMapping("/other")
    public String other() {
        return "Hello Other";
    }

}
