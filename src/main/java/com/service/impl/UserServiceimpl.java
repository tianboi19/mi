package com.service.impl;

import com.dao.IUserdao;
import com.dao.impl.Userdaoimpl;
import com.entity.User;
import com.service.IUserService;

import java.sql.SQLException;

public class UserServiceimpl implements IUserService {
    IUserdao dao=new Userdaoimpl();

    @Override
    public boolean login(String name, String passwrod) {
        boolean result = false;
        try {
            result = dao.login(name, passwrod);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer register(User user) {
        return null;
    }

    @Override
    public User findByName(String name) {
        User user = null;
        try {
            user = dao.findByName(name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }


}
