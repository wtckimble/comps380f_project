/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Lecture;
import java.util.List;

/**
 *
 * @author German
 */
public interface LectureRepository {
    public void create(Lecture lecture);
    public List<Lecture> findAll();
    public Lecture findByLectureId(long id);
    public void deleteByLectureId(long id);
}
