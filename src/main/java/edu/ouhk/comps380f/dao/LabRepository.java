/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Lab;
import java.util.List;

/**
 *
 * @author German
 */
public interface LabRepository {
    public int createLab(Lab lab);
    public void createAttachment(Lab lab, int topicId);
    public List<Lab> findAll();
    public Lab findByLabId(int id);
    public void deleteByLabId(int id);
}
