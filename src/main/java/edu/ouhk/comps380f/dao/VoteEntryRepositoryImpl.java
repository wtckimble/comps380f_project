/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

//import static edu.ouhk.comps380f.dao.ThreadEntryRepositoryImpl.toDate;
import edu.ouhk.comps380f.model.Poll;
import edu.ouhk.comps380f.model.Vote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class VoteEntryRepositoryImpl implements VoteEntryRepository{
    
    @Autowired
    DataSource dataSource;
    
    private static final String SQL_INSERT_POLLENTRY
            = "insert into POLL (topic, optionone, optiontwo, optionthree, optionfour) values (?, ?, ?, ?, ?)";

    @Override
    public void createPoll(Poll aPoll) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
        conn = dataSource.getConnection();
        stmt = conn.prepareStatement(SQL_INSERT_POLLENTRY);
        stmt.setString(1, aPoll.getTopic());
        stmt.setString(2, aPoll.getOptionone());
        stmt.setString(3, aPoll.getOptiontwo());
        stmt.setString(4, aPoll.getOptionthree());
        stmt.setString(5, aPoll.getOptionfour());
        stmt.execute();
        
        }catch (SQLException e) {
            e.printStackTrace();  
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  
            }
        }
    }
    
    private static final String SQL_INSERT_VOTEENTRY
            = "insert into Vote (pollid, username, choice) values (?, ?, ?)";

    @Override
    public void createVote(Vote aVote) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
        conn = dataSource.getConnection();
        stmt = conn.prepareStatement(SQL_INSERT_VOTEENTRY);
        stmt.setInt(1,aVote.getPollid());
        stmt.setString(2,aVote.getUsername());
        stmt.setInt(3, aVote.getChoice());
        stmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();  
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  
            }
        } 
    }
    
    private static final String SQL_SELECT_TYPE_ANY
            = "select * from POLL";

    @Override
    public Poll findAny() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_TYPE_ANY);
            rs = stmt.executeQuery();
            Poll entry = null;
            if (rs.next()) {
                entry = new Poll();
                entry.setId(rs.getInt("pollid"));
                entry.setTopic(rs.getString("topic"));
                entry.setOptionone(rs.getString("optionone"));
                entry.setOptiontwo(rs.getString("optiontwo"));
                entry.setOptionthree(rs.getString("optionthree"));
                entry.setOptionfour(rs.getString("optionfour"));
            }
            return entry;
        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    private static final String SQL_INSERT_USERVOTE
            = "select * from Vote where username = ? ";

    @Override
    public Vote findByUserName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_USERVOTE);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            Vote entry = null;
            if (rs.next()) {
                entry = new Vote();
                entry.setVoteid(rs.getInt("voteid"));
                entry.setUsername(rs.getString("username"));
                entry.setPollid(rs.getInt("pollid"));
                entry.setChoice(rs.getInt("choice"));
            }
            return entry;
        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    private static final String SQL_FIND_USERVOTE
            = "select * from Vote where pollid = ? ";

    @Override
    public List<Vote> findByPollId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_USERVOTE);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List<Vote> entries = new ArrayList<>();
            
            while (rs.next()) {
                Vote entry = new Vote();
                entry.setVoteid(rs.getInt("voteid"));
                entry.setUsername(rs.getString("username"));
                entry.setPollid(rs.getInt("pollid"));
                entry.setChoice(rs.getInt("choice"));
                entries.add(entry);
            }
            return entries;
        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    private static final String SQL_CLEAN_VOTE
            = "TRUNCATE TABLE vote";

    @Override
    public void cleanVote() {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
        conn = dataSource.getConnection();
        stmt = conn.prepareStatement(SQL_CLEAN_VOTE);
        stmt.execute();
        
        }catch (SQLException e) {
            e.printStackTrace();  
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  
            }
        }
    }
    
    private static final String SQL_CLEAN_POLL
            = "DELETE FROM poll";

    @Override
    public void cleanPoll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
        conn = dataSource.getConnection();
        stmt = conn.prepareStatement(SQL_CLEAN_POLL);
        stmt.execute();
        
        }catch (SQLException e) {
            e.printStackTrace();  
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  
            }
        }
    }

    
    
    
}
