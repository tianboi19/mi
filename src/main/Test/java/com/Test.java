package com;

import com.dao.IUserdao;
import com.dao.impl.Userdaoimpl;
import com.utlils.DBUtlils;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {
        IUserdao dao=new Userdaoimpl();
        Connection con = DBUtlils.getCon();
        System.out.println(con);

//        User user= new User("张山","123","广东","123123");
//
//        dao.AddUser(user);

//        User user1=new User(2,"张三","123","广东","123123");
//
//
//        Integer integer = dao.UpUserData(user1);
//        System.out.println(integer);

//        List<User> list = dao.findAll();
//        for (User u:list) {
//            System.out.println(u);
//        }
//        User byId = dao.findById(2);
//        System.out.println(byId);

        boolean login = dao.login("123", "123");
        System.out.println(login);

    }
}
