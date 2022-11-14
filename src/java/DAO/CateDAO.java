/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Category;
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
public class CateDAO {
    public static ArrayList<Category> getCategories(String keyword, String searchby) throws Exception{
        ArrayList<Category> list=new ArrayList<>();
        Category category = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null && searchby!=null) {
            //step 2: viet query sql & excute
            String sql = "select CateID,CateName\n" 
                    + "from Categories\n";
            if(searchby.equalsIgnoreCase("byname"))
                sql=sql+"where CateName like ?";
            else sql=sql+"where CateID like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%"+keyword+"%");
            ResultSet rs=pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("CateID");
                    String name = rs.getString("CateName");
                    category= new Category(id, name);
                    list.add(category);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
      public static ArrayList<Category> getCategories() throws Exception{
        ArrayList<Category> list=new ArrayList<>();
        Category category = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null ) {
            //step 2: viet query sql & excute
            String sql = "select CateID,CateName\n" 
                    + "from Categories\n";
             Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("CateID");
                    String name = rs.getString("CateName");
                    category= new Category(id, name);
                    list.add(category);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
       public static boolean insertCate( String name) throws Exception{
         Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert dbo.Categories(CateName)"
                    +"values(?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.executeUpdate();
            cn.close();
     }
        return true;
}
}
