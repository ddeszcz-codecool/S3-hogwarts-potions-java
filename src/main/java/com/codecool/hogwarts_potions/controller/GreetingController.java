package com.codecool.hogwarts_potions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {
    @RequestMapping({"", "/", "/index", "/blabla","blabla3"})
//    @GetMapping("/blabla2")
    public String greeting() {
        return "index2";
    }
}
