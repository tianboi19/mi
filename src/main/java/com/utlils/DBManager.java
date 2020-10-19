package com.utlils;

import com.entity.Phone;
import com.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {



    /**
     * 更新数据的封装
     * @param sql sql 语句
     * @param obj 占位符的赋值
     * @return  受到影响的行数
     */
    public static Integer CommonUpdate(String sql,Object ... obj){
        Connection connection=null;
        PreparedStatement ps=null;
        int rSet=0;
        try {
            connection = DBUtlils.getCon();
            ps = connection.prepareStatement(sql);//赋予语句
            //给占位符赋值
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            //返回受影响的行数
            rSet= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关流 从小到大关
            DBUtlils.CloseAll(ps,connection);
        }
        return rSet;
    }

    /**
     * 添加用户
     * @param sql
     * @param user
     * @return
     */
        public static Integer innserUser(String sql,User user){
            Connection connection=null;
            PreparedStatement ps=null;
            int rSet=0;
            try {
                connection = DBUtlils.getCon();
                ps = connection.prepareStatement(sql);
                ps.setString(1,user.getName());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getAddress());
                ps.setString(4,user.getPhone());
                ps.setInt(5,user.getIsDelete());
                rSet = ps.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return rSet;
        }


    /**
     * 查询所有用户
     * @return
     */
    public static List<User> findAll(String sql) throws SQLException {
        List<User> list=new ArrayList<>();
        Connection connection = DBUtlils.getCon();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String password = rs.getString(3);
            String address = rs.getString(4);
            String phone = rs.getString(5);
            Integer isdelete = rs.getInt(6);
            User user=new User(id,name,password,address,phone,isdelete);
            list.add(user);
        }
        DBUtlils.CloseAll(rs,ps,connection);
        return list;
    }

    /**
     *
     *根据ID查询用户
     * @return  返回用户
     * @throws SQLException
     */
    public static User findById(String sql) throws SQLException {
        Connection connection = DBUtlils.getCon();
        User user=null;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            int uid = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            Integer isdelete = rs.getInt("isdelete");
             user=new User(uid,name,password,address,phone,isdelete);
        }
        DBUtlils.CloseAll(rs,connection,statement);
        return user;
    }

    /**
     * 判断登陆
     * @param sql
     * @param name 账号
     * @param password 密码
     * @return 是否通过
     * @throws SQLException
     */

    public static boolean login(String sql,String name,String password) throws SQLException {
        Connection connection = DBUtlils.getCon();
        PreparedStatement ps=null;
        boolean is;
        ps = connection.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            is= true;
        }else {
            is= false;
        }
        DBUtlils.CloseAll(rs,ps,connection);
        return is;
    }

    /**
     * 改变用户状态
     * 修改用户状态 ，如果1激活状态就赋值为0 禁用 反之 激活
     * @param sql
     * @return
     */
    public static int UpDataState(String sql,int id) throws SQLException {
        User u = DBManager.findById(sql);
        if(u.getIsDelete()==1){
            sql="update user set isdelete=0 where id="+id;
        }else {
            sql="update user set isdelete=1 where id"+id;
        }

        Connection connection=null;
        PreparedStatement ps=null;
        int rSet=0;
        try {
            connection = DBUtlils.getCon();
            ps = connection.prepareStatement(sql);//赋予语句
            //返回受影响的行数
            rSet= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关流 从小到大关
            DBUtlils.CloseAll(ps,connection);
        }
        return rSet;
    }

    //添加手机
    public static Integer innserPhone(String sql, Phone phone){
        Connection connection = null;
        PreparedStatement ps = null;
        int rSet =0;
        try{
            connection = DBUtlils.getCon();
            ps = connection.prepareStatement(sql);
            ps.setString(1,phone.getName());
            ps.setString(2,phone.getColor());
            ps.setInt(3,phone.getPrice());
            ps.setString(4,phone.getImage());
            ps.setInt(5,phone.getClassify());
            ps.setInt(6,phone.getState());
            ps.setInt(7,phone.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSet;
    }
    //修改手机手机信息
    public static Integer UpPhoneData(String sql,Phone phone) {

        return null;
    }

    /*
     * 修改手机手机状态
     * 当手机状态为0时，代表用户为付款购买
     * 为1时，代表用户已付款
     * */
    public static Integer UpPhoneStata(String sql,int id) throws SQLException {
        Phone p = DBManager.findByIdPhone(sql);
        if(p.getState()==0){
            sql = "update phone set state=1 where id"+id;
        }
        return null;
    }
    //查询所有手机
    public static List<Phone> findAllPhone(String sql) throws SQLException {
        ArrayList<Phone> list = new ArrayList<>();
        Connection connection = DBUtlils.getCon();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String  name= rs.getString(2);
            String  color= rs.getString(3);
            int  price= rs.getInt(4);
            String  image= rs.getString(5);
            int  classify= rs.getInt(6);
            int state = rs.getInt(7);
            int value = rs.getInt(8);
            Phone phone = new Phone(id,name,color,price,image,classify,state,value);
            list.add(phone);
        }
            DBUtlils.CloseAll(rs,ps,connection);
            return list;
    }

    public static Phone findByIdPhone(String sql) throws SQLException {
        Connection connection = DBUtlils.getCon();
        Phone phone=null;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            int pid = rs.getInt("id");
            String name = rs.getString("name");
            String color = rs.getString("color");
            int price = rs.getInt("price");
            String image = rs.getString("image");
            int classify =rs.getInt("classify");
            int state =rs.getInt("state");
            int value =rs.getInt("value");
            phone = new Phone(pid,name,color,price,image,classify,state,value);
        }
        DBUtlils.CloseAll(rs,connection,statement);
        return phone;
    }

}
