package edu.ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("register")
public class RegisterController {

    @RequestMapping(value = "")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register");
    }
}
