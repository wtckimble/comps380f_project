/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Attachment;
import edu.ouhk.comps380f.model.Lecture;
import edu.ouhk.comps380f.model.Reply;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kimble
 */
@Repository
public class AttachmentRepositoryImpl implements AttachmentRepository {

    @Autowired
    public DataSource dataSource;

    private static final String SQL_INSERT_TENTRY
            = "insert into threadattachment (name, content, mime) values (?, ?, ?)";

    @Override
    public void createLectureAttachment(Attachment attachment) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_TENTRY);
            stmt.setString(1, attachment.getName());
            stmt.setBytes(2, attachment.getContents());
            stmt.setString(3, attachment.getMimeContentType());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttachmentRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //jdbcOp.update("insert into attachment (name, content, mime, topic_id) values ('1', '1', '1', 1)");
    @Override
    public void createReplyAttachment(Reply reply, Attachment attachment
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
