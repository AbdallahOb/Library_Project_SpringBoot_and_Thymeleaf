package com.library.librarydemo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return "library/security/fancy-login";
    }

    @GetMapping("/access-denied")
    public String deniedPage(){
        return "library/security/access-denied";
    }
}
