/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Plant;
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
public class PlanDAO {
    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws Exception{
        ArrayList<Plant> list=new ArrayList<>();
        Plant plant = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null && searchby!=null) {
            //step 2: viet query sql & excute
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n" 
                    + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
            if(searchby.equalsIgnoreCase("byname"))
                sql=sql+"where Plants.PName like ?";
            else sql=sql+"where CateName like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%"+keyword+"%");
            ResultSet rs=pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    String catename = rs.getString("CateName");
                    int Status = rs.getInt("status");
                    int price = rs.getInt("price");
                    int cateid = rs.getInt("CateID");
                    plant=new Plant(id, name, price, imgpath, description, Status, cateid, catename);
                    list.add(plant);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
     public static Plant getPlant(int pid) throws Exception{
        Plant plant = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n" 
                    + "from Plants join Categories on Plants.CateID=Categories.CateID where Plants.PID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
            ResultSet rs=pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    String name = rs.getString("PName");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    String catename = rs.getString("CateName");
                    int Status = rs.getInt("status");
                    int price = rs.getInt("price");
                    int cateid = rs.getInt("CateID");
                    plant=new Plant(pid, name, price, imgpath, description, Status, cateid, catename);
                }
            }
            //step 4: close
            cn.close();
        }
        return plant;
    }
      public static ArrayList<Plant> getPlans() throws Exception {
        //step 1: make connection
        ArrayList<Plant> list = new ArrayList<>();
        Plant plant = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n" 
                    + "from Plants join Categories on Plants.CateID=Categories.CateID";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                   int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    String catename = rs.getString("CateName");
                    int Status = rs.getInt("status");
                    int price = rs.getInt("price");
                    int cateid = rs.getInt("CateID");
                    plant=new Plant(id, name, price, imgpath, description, Status, cateid, catename);
                    list.add(plant);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
       public static boolean updatePlantStatus(int pid, int status) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Plants set status=? where PID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, pid);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }
        public static boolean updatePlant(int pid,String name,String imgPath,String description,int cateID,int price) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Plants set PName=? , price=? , imgPath= ? ,description= ? , CateID= ? where PID= ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, price);
            pst.setString(3, imgPath);
            pst.setString(4, description);
            pst.setInt(5, cateID);
            pst.setInt(6, pid);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }
}
