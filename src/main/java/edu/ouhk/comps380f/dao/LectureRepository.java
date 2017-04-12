/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Lecture;
import java.util.List;

/**
 *
 * @author German
 */
public interface LectureRepository {
    public int createLecture(Lecture lecture);
    public void createAttachment(Lecture lecture, int topicId);
    public List<Lecture> findAll();
    public Lecture findByLectureId(int id);
    public void deleteByLectureId(int id);
}
