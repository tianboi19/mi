package com.dao;

import com.entity.Phone;

import java.sql.SQLException;
import java.util.List;

public interface Phonedao {
    //添加手机
    Integer AddPhone(Phone phone);
    //修改手机信息
    Integer UpPhoneData(Phone phone);
    //修改手机状态
    Integer UpPhoneState(Integer id) throws SQLException;
    //查找所有手机
    List<Phone> findAllPhone() throws SQLException;
    //通过id查找手机
    Phone findByIdPhone(Integer id) throws SQLException;
    //通过name查找手机
    Phone findByNamePhone(String name) throws SQLException;
}
