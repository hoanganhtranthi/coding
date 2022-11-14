/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Hoang Anh
 */
public class AccountDAO {

    //ham nay lay all accounts trong DB
    public static ArrayList<Account> getAccount() throws Exception {
        //step 1: make connection
        ArrayList<Account> list = new ArrayList<>();
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select accID,email,password,fullname,phone,status,role\n" + "from dbo.Accounts";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                    list.add(acc);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
     public static ArrayList<Account> getAccounts(String keyword, String searchby) throws Exception {
        //step 1: make connection
        ArrayList<Account> list = new ArrayList<>();
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select accID,email,password,fullname,phone,status,role\n" + "from dbo.Accounts\n";
           if(searchby.equalsIgnoreCase("byname"))
                sql=sql+"where Accounts.fullname like ?";
            else sql=sql+"where Accounts.email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%"+keyword+"%");
            ResultSet rs=pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                    list.add(acc);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
    //ham nay  de lay 1 account dua vao accid
    //input:accid
    //out:return an account obj co accid=input

    public static Account getAccount(String email) throws Exception {
        Account acc = null;//chua dap an
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from dbo.Accounts\n"
                    + "where email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accid = table.getInt("accID");
                String Password = table.getString("password");
                String Fullname = table.getString("fullname");
                String Phone = table.getString("phone");
                int Status = table.getInt("status");
                int Role = table.getInt("role");
                acc = new Account(accid, email, Password, Fullname, Status, Phone, Role);
            }
            cn.close();
        }
        return acc;
    }
 public static Account getAccountToken(String token) throws Exception {
        Account acc = null;//chua dap an
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from dbo.Accounts\n"
                    + "where token=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String email=table.getString("email");
                int accid = table.getInt("accID");
                String Password = table.getString("password");
                String Fullname = table.getString("fullname");
                String Phone = table.getString("phone");
                int Status = table.getInt("status");
                int Role = table.getInt("role");
                acc = new Account(accid, email, Password, Fullname, Status, Phone, Role);
            }
            cn.close();
        }
        return acc;
    }
    //ham nay de return mot account khi biet email, password
    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;//chua dap an
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from dbo.Accounts\n"
                    + "where email=? and password=? COLLATE SQL_Latin1_General_CP1_CI_AS";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accid = table.getInt("accID");
                // String email=table.getString("email");
                //String password=table.getString("password");
                String Password = table.getString("password");
                String Fullname = table.getString("fullname");
                String Phone = table.getString("phone");
                int Status = table.getInt("status");
                int Role = table.getInt("role");
                acc = new Account(accid, email, password, Fullname, Status, Phone, Role);

            }
            cn.close();
        }
        return acc;
    }

    public static boolean updateAccountStatus(String email, int status) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts set status=? where email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setString(2, email);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts set email=?,password=?,fullname=?,phone=? where email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setString(5, email);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }
     public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus,int newRole) throws Exception{
         Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert dbo.Accounts(email,password,fullname,phone,status,role)"
                    +"values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newEmail);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setInt(5, newSatus);
            pst.setInt(6, newRole);
            pst.executeUpdate();
            cn.close();
     }
        return true;
}
      public static boolean updateToken(String email, String token) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts set email=?,token=? where email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2,token);
            pst.setString(3, email);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }
}
