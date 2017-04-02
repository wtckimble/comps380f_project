package edu.ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("lecture")
public class LectureController {

    @RequestMapping(value = "")
    public ModelAndView showLecturePage() {
        return new ModelAndView("lecture");
    }
    
    @RequestMapping(value = "/download")
    public ModelAndView showDownloadPage() {
        return new ModelAndView("lab");
    }
}
