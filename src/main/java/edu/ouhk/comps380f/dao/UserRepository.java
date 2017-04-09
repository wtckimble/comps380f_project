/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.User;
import java.util.List;

/**
 *
 * @author EricYan
 */
public interface UserRepository {
    public void create(User user);
    public List<User> findAll();
    public User findByUsername(String username);
    public void deleteByUsername(String username);
}
