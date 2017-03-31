package edu.ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("other")
public class OtherController {

    @RequestMapping(value = "")
    public ModelAndView showOtherPage() {
        return new ModelAndView("other");
    }
}
