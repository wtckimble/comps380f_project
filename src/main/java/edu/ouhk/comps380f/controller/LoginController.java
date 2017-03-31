package edu.ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping(value = "")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }
}
