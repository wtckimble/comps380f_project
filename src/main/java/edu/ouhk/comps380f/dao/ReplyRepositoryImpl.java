/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Lecture;
import edu.ouhk.comps380f.model.Reply;
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
public class ReplyRepositoryImpl implements ReplyRepository{
    
    private DataSource dataSource;
    private JdbcOperations jdbcOp;
       
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }
    
    protected class ItemMapper implements RowMapper {  
  
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {  
            Reply reply = new Reply();  
            reply.setCustomerName(rs.getString("reply_author"));    
            reply.setBody(rs.getString("reply_content"));    
            return reply;  
        }
    } 
    
    
    @Override
    public void createReply(Reply reply) {
        jdbcOp.update("insert into reply (reply_content, reply_author, topic_id) "
                + "values (?, ?, ?)", reply.getBody(), reply.getCustomerName(), reply.getTopicId());
     
    }

    @Override
    public List<Reply> findByTopicId(int id) {
        List<Reply> replys = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT * FROM reply WHERE topic_id = ? ", id);
        System.out.println(rows);
        for (Map<String, Object> row : rows) { 
            Reply reply = new Reply();
            reply.setCustomerName((String)row.get("reply_author"));
            reply.setBody((String)row.get("reply_content"));
            reply.setId((int)row.get("reply_id"));
            replys.add(reply);
        }
        return replys; 
    }

    @Override
    public void deleteByReplyId(int id) {
        jdbcOp.update("delete from reply where reply_id = ?", id);
    }

    
    
}
