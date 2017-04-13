/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Lecture;
import edu.ouhk.comps380f.model.Reply;
import java.util.List;

/**
 *
 * @author German
 */
public interface ReplyRepository {
    public int createReply(Reply reply);
    public void createAttachment(Reply replay, int replyId);
    public List<Reply> findByTopicId(int id);
    public Reply findByReplyId(int id);
    public void deleteByReplyId(int id);
}
