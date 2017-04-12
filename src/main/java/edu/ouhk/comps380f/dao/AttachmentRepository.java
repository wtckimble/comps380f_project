/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Lecture;
import edu.ouhk.comps380f.model.Reply;

/**
 *
 * @author Kimble
 */
public interface AttachmentRepository {
    public void createLectureAttachment(Attachment attachment);
    public void createReplyAttachment(Reply reply, Attachment attachment);
}
