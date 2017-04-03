package edu.ouhk.comps380f.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("register")
public class RegisterController {

    public Connection conn;
    public Statement stmt;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register", "registerForm", new RegisterForm());
    }

 /*   @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView showRegisterResult(RegisterForm form) {
        ModelAndView mav = new ModelAndView();
        String username, password, password2;
        username = form.getUsername();
        password = form.getPassword();
        password2 = form.getPassword2();

        if (!password.equals(password2)) {
            mav.addObject("pmNotMatched", "Password is not match.");
        }

    } */

    public static class RegisterForm {

        private String username;
        private String password;
        private String password2;

        // Getters and Setters of customerName, subject, body, attachments
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword2() {
            return password2;
        }

        public void setPassword2(String password2) {
            this.password2 = password2;
        }
    }

    public void dbConnection() {
        try {
            String dbURL = "jdbc:derby://localhost:1527/Account";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected to Account");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
