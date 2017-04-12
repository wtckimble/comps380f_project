package edu.ouhk.comps380f.controller;

import edu.ouhk.comps380f.dao.UserRepository;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Repository
public class IndexController {
    
    @Autowired
    public DataSource dataSource;
    public Statement stmt;
    
    @Autowired
    UserRepository userRepo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public View showIndexPage() {
        return new RedirectView("/index", true);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }
    
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView userlist() {   
        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("userlist", userRepo.findAll());
        return modelAndView;
    }
    
    @RequestMapping(value = "/promoteUser/{username}", method = RequestMethod.GET)
    public View promoteUser(@PathVariable("username") String username) {   
        userRepo.UpdateByUser(userRepo.findByUsername(username));
        return new RedirectView("/userList", true);
    }
    
    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.GET)
    public View deleteUser(@PathVariable("username") String username) {   
        userRepo.deleteByUsername(username);
        return new RedirectView("/userList", true);
    }
    
    
    
     
    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView showLecturePage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("username", req.getParameter("username"));
        return new ModelAndView("lecture");
    }*/
}
