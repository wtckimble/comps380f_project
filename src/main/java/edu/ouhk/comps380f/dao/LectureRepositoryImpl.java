/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Lecture;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    protected class ItemMapper implements RowMapper {  
  
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {  
            Lecture lecture = new Lecture();  
            lecture.setId(rs.getInt("topic_id"));    
            lecture.setSubject(rs.getString("topic_title"));    
            return lecture;  
        }
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
        List<Lecture> lectures = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT topic_id, topic_title FROM topic");
        //String sql = "SELECT  topic_id, topic_title, topic_content, topic_author FROM topic";  
        for (Map<String, Object> row : rows) {
            Lecture lecture = new Lecture();
            lecture.setId((int)row.get("topic_id"));
            lecture.setSubject((String) row.get("topic_title"));
            lectures.add(lecture);
        }
        return lectures;  
        
    }

    @Override
    public Lecture findByLectureId(int id) {
        Lecture lecture = new Lecture();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT * FROM topic where topic_id = ? ", id);
        for (Map<String, Object> row : rows) {
            lecture.setId((int)row.get("topic_id"));
            lecture.setSubject((String)row.get("topic_subject"));
            lecture.setCustomerName((String)row.get("topic_author"));
            lecture.setBody((String)row.get("topic_content"));         
        }
        System.out.println(lecture.getBody());
        System.out.println(lecture.getSubject());
        return lecture; 
    }

    @Override
    public void deleteByLectureId(int id) {
        jdbcOp.update("delete from reply where topic_id = ?", id);
        jdbcOp.update("delete from topic where topic_id = ?", id);
    }
}
