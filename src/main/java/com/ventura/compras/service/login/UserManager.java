 package com.ventura.compras.service.login;

import java.io.Serializable;
import java.util.List;

import com.ventura.compras.domain.login.User;

public interface UserManager extends Serializable {

    public List<User> getUsers();
    public User val(String k, String p);
    public boolean addUser(User user);

}