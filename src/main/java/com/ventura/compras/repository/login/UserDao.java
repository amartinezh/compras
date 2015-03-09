package com.ventura.compras.repository.login;

import java.util.List;

import com.ventura.compras.domain.login.User;

public interface UserDao {

    public List<User> getUserList();
    public User val(String k, String p);
    public boolean addUser(User user);
    public void deleteUser(String id);

}