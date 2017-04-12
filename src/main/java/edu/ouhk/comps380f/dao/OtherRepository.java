/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;


import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Other;
import java.util.List;

/**
 *
 * @author German
 */
public interface OtherRepository {
    public int createOther(Other other);
    public void createAttachment(Other other, int topicId);
    public List<Other> findAll();
    public Other findByOtherId(int id);
    public void deleteByOtherId(int id);
}
