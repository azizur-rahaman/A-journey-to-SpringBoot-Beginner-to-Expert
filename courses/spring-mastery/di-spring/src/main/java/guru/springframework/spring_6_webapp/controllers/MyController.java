package guru.springframework.spring_6_webapp.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String sayHello(){
        System.out.println("I'm here");
        return "Hello Everyone!";
    }
}
