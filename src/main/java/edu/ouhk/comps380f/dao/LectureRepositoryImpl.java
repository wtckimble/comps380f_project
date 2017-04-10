/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Lecture;
import edu.ouhk.comps380f.model.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */
@Repository
public class LectureRepositoryImpl implements LectureRepository{
    
    private DataSource dataSource;
    private JdbcOperations jdbcOp;
       
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    /**
     *
     * @param lecture
     */
    @Override
    public void create(Lecture lecture) {
        jdbcOp.update("insert into topic (topic_title, topic_content, topic_author, topic_category) "
                + "values (?, ?, ?, 'lecture')", lecture.getSubject(), lecture.getBody(), lecture.getCustomerName());
     
    }

    @Override
    public List<Lecture> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lecture findByLectureId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteByLectureId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
 
}
