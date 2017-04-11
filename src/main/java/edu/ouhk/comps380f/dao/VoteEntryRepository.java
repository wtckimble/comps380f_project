/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Poll;
import edu.ouhk.comps380f.model.Vote;
import java.util.List;


public interface VoteEntryRepository {
    public void createPoll(Poll aPoll);
    public void createVote(Vote aVote);
    public Poll findAny();
    public Vote findByUserName(String name);
    public List<Vote> findByPollId(int id);
    public void cleanVote();
    public void cleanPoll();
}
