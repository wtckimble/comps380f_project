/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.controller;

import edu.ouhk.comps380f.dao.VoteEntryRepository;
import edu.ouhk.comps380f.model.Poll;
import edu.ouhk.comps380f.model.Vote;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class PollController {
    
    @Autowired
    VoteEntryRepository voteEntryRepository;
    
    
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView Home() {
        ModelAndView modelAndView = new ModelAndView("index");
        Poll aPoll = voteEntryRepository.findAny();
        if (aPoll != null) {
        List<Vote> entries = voteEntryRepository.findByPollId(aPoll.getId());
        modelAndView.addObject("voteset", entries);
        }
        modelAndView.addObject("poll", aPoll);
        modelAndView.addObject("comment", new Vote());
        return modelAndView;
    }
    
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public View addVoteHandle(Principal principal, Vote entry) throws IOException {
        Vote temp = voteEntryRepository.findByUserName(principal.getName());
        System.out.println(temp);
        if (temp == null) {
            entry.setUsername(principal.getName());
            voteEntryRepository.createVote(entry);
        }
        String redirect = "/index";
        return new RedirectView(redirect, true);
    }
    
    @RequestMapping(value = "addpoll", method = RequestMethod.GET)
    public ModelAndView addCommentForm() throws IOException {
        voteEntryRepository.cleanVote();
        voteEntryRepository.cleanPoll();
        ModelAndView modelAndView = new ModelAndView("addPoll");
        modelAndView.addObject("comment", new Poll());
        return modelAndView;
    }

    @RequestMapping(value = "addpoll", method = RequestMethod.POST)
    public View addCommentHandle(Poll entry) throws IOException {
        voteEntryRepository.createPoll(entry);
        String redirect = "/index";
        return new RedirectView(redirect, true);
    }
    
}
