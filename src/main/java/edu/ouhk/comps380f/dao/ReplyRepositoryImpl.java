/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Reply;
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
public class ReplyRepositoryImpl implements ReplyRepository{
    
    private DataSource dataSource;
    private JdbcOperations jdbcOp;
       
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }
    
    
    @Override
    public void createReply(Reply reply) {
        jdbcOp.update("insert into reply (reply_content, reply_author, topic_id) "
                + "values (?, ?, ?)", reply.getBody(), reply.getCustomerName(), reply.getTopicId());
     
    }

    
    
}
