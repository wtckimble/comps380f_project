/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.User;
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
 * @author EricYan
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;
    private JdbcOperations jdbcOp;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    private static final class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    /* @Override
    public void create(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into users (username, password) values (?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
    @Override
    public void create(User user) {
        jdbcOp.update("insert into users (username, password) values (?, ?)", user.getUsername(), user.getPassword());
        jdbcOp.update("insert into user_roles (username, role) values (?, 'ROLE_USER')", user.getUsername());
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList("select username, password from users");

        for (Map<String, Object> row : rows) {
            User user = new User();
            String username = (String) row.get("username");
            user.setUsername(username);
            user.setPassword((String) row.get("password"));
            List<Map<String, Object>> roleRows = jdbcOp.queryForList("select username, role from user_roles where username = ?", username);
            for (Map<String, Object> roleRow : roleRows) {
                user.addRole((String) roleRow.get("role"));
            }
            users.add(user);
        }
        return users;
    }

    public boolean isUserExists(String username) {
        String sql = "SELECT count(*) FROM USERS WHERE username = ?";
        boolean result = false;
        
        int count = jdbcOp.queryForObject(
                sql, new Object[]{username}, Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    /*@Override
    public List<User> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs;
        ResultSet rs2;
        List<User> users = new ArrayList<>();
        String findUserQuery = "select username, password from users";
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findUserQuery);
            while (rs.next()) {
                User user = new User();
                String username = rs.getString("username");
                String password = rs.getString("password");
                user.setUsername(username);
                user.setPassword(password);
                PreparedStatement ps = conn.prepareStatement("select username, role from user_roles where username = ?");
                ps.setString(1, username);
                rs2 = ps.executeQuery();
                while (rs2.next()) {
                    user.addRole(rs2.getString("role"));
                }
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }*/
    @Override
    public User findByUsername(String username) {
        User user = jdbcOp.queryForObject("select username, password from users where username = ?", new UserRowMapper(), username);
        List<Map<String, Object>> rows = jdbcOp.queryForList("select username, role from user_roles where username = ?", username);
        for (Map<String, Object> row : rows) {
            user.addRole((String) row.get("role"));
        }
        return user;
    }

    @Override
    public void deleteByUsername(String username) {
        jdbcOp.update("delete from user_roles where username = ?", username);
        jdbcOp.update("delete from users where username = ?", username);
    }
    
    @Override
    public void UpdateByUser(User user) {  
        if(!user.hasRole("ROLE_ADMIN")){
            user.addRole("ROLE_ADMIN");
            jdbcOp.update("UPDATE user_roles SET role = ? WHERE username = ?" , user.getRoles(), user.getUsername());
        }
              
    }
    
    
    
    
}
