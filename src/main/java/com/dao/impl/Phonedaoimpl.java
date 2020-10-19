package com.dao.impl;

import com.dao.Phonedao;
import com.entity.Phone;
import com.utlils.DBManager;

import java.sql.SQLException;
import java.util.List;

    public class Phonedaoimpl implements Phonedao {

    @Override
    public Integer AddPhone(Phone phone) {
        String sql ="insert into phone(id,name,color,price,image,classify,state,value) value(?,?,?,?,?,?,?,?)";
        return DBManager.innserPhone(sql,phone);
    }

    @Override
    public Integer UpPhoneData(Phone phone) {
        String sql ="update phone set name=?,color=?,price=?,image=?,classify=?,value=? where id=?";
        return DBManager.UpPhoneData(sql,phone);
    }

    @Override
    public Integer UpPhoneState(Integer id) throws SQLException {
        String sql ="update phone set state=? where id=?";
        return DBManager.UpPhoneStata(sql,id);
    }

    @Override
    public List<Phone> findAllPhone() throws SQLException {
        String sql ="select * from Phone";
        return DBManager.findAllPhone(sql);
    }

    @Override
    public Phone findByIdPhone(Integer id) throws SQLException {
        String sql="select * from user where id="+id;
        return DBManager.findByIdPhone(sql);

    }

    @Override
    public Phone findByNamePhone(String name) throws SQLException {
        String sql="select * from user where name="+name;
        return DBManager.findByIdPhone(sql);
    }
}
