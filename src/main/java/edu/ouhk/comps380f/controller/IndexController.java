package edu.ouhk.comps380f.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndexPage() {
        return new ModelAndView("index");
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }
    
    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView showLecturePage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("username", req.getParameter("username"));
        return new ModelAndView("lecture");
    }*/
}
