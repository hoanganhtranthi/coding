/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import mylib.DBUtils;

/**
 *
 * @author Hoang Anh
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) throws Exception {
        //step 1: make connection
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select OrderID,OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email from Orders join Accounts on Orders.AccID=Accounts.accID and Accounts.email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    order = new Order(OrderID, date, shipdate, Status, accID);
                    list.add(order);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }

    public static Order getOrder(int ordid) throws Exception {
        //step 1: make connection
        Order order = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select OrderID,OrdDate,shipdate,Orders.status,AccID from Orders where OrderID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, ordid);
            ResultSet rs = pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    order = new Order(ordid, date, shipdate, Status, accID);
                }
            }
            //step 4: close
            cn.close();
        }
        return order;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws Exception {
        ArrayList<OrderDetail> list = new ArrayList<>();
        OrderDetail orderdetail = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select [DetailID],[OrderID],[PID],PName,price,imgPath,quantity\n"
                    + "from [dbo].[OrderDetails],Plants\n" + "where OrderID=? and OrderDetails.FID=Plants.PID";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, orderID);
            ResultSet rs = pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int detailID = rs.getInt("DetailID");
                    String PlanName = rs.getString("PName");
                    String imgPath = rs.getString("imgPath");
                    int price = rs.getInt("price");
                    int PlantID = rs.getInt("PID");
                    int quantity = rs.getInt("quantity");
                    orderdetail = new OrderDetail(detailID, orderID, PlantID, PlanName, price, imgPath, quantity);
                    list.add(orderdetail);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }


    public static boolean insertOrder(String newEmail, HashMap<String, Integer> cart) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean result = false;
        if (cn != null) {
            int accid = 0, orderid = 0;
            cn.setAutoCommit(false);
            String sql = "select accID from Accounts where Accounts.email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newEmail);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    accid = rs.getInt("accID");
                }
            }
            System.out.println("Account ID:" + accid);
            Date d = new Date(System.currentTimeMillis());
            System.out.println("Order Date: " + d);
            sql = "insert Orders(OrdDate,status,AccID) values(?,?,?)";
            pst = cn.prepareStatement(sql);
            pst.setDate(1, d);
            pst.setInt(2, 1);
            pst.setInt(3, accid);
            pst.executeUpdate();
            sql = "select top 1 orderID from Orders order by orderId desc";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    orderid = rs.getInt("orderID");
                }
            }
            System.out.println("Order ID: " + orderid);
            Set<String> pids = cart.keySet();
            for (String pid : pids) {
                sql = "insert OrderDetails values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                pst.setInt(2, Integer.parseInt(pid.trim()));
                pst.setInt(3, cart.get(pid));
                pst.executeUpdate();
                cn.commit();
                cn.setAutoCommit(true);

            }
            cn.close();
            return true;
        }
        return result;

    }

    public static boolean updateOrder(int ordid, int status, Date date) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Orders set status=?,OrdDate=? where OrderID =?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setDate(2, date);
            pst.setInt(3, ordid);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }
public static boolean updateCancel(int ordid, int status) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Orders set status=? where OrderID =?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, ordid);
            pst.executeUpdate();
            cn.close();
        }
        return true;
    }
    public static ArrayList<Order> getOrderPriceLowToHigh(String email) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select sum(e.price*e.quantity)as'Total',e.OrderID as 'OrderID',OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email from (select [DetailID],[OrderID],[PID],PName,price,imgPath,quantity from [dbo].[OrderDetails],Plants where  OrderDetails.FID=Plants.PID ) as e join Orders join Accounts on Orders.AccID=Accounts.accID on e.OrderID=Orders.OrderID and Accounts.email=? group by e.OrderID,OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email order by Total asc";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    order = new Order(OrderID, date, shipdate, Status, accID);
                    list.add(order);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrderPriceLowToHigh() throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select sum(e.price*e.quantity)as'Total',e.OrderID as 'OrderID',OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email from (select [DetailID],[OrderID],[PID],PName,price,imgPath,quantity from [dbo].[OrderDetails],Plants where  OrderDetails.FID=Plants.PID ) as e join Orders join Accounts on Orders.AccID=Accounts.accID on e.OrderID=Orders.OrderID group by e.OrderID,OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email order by Total asc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    order = new Order(OrderID, date, shipdate, Status, accID);
                    list.add(order);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrderPriceHighToLow(String email) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select sum(e.price*e.quantity)as'Total',e.OrderID as 'OrderID',OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email from (select [DetailID],[OrderID],[PID],PName,price,imgPath,quantity from [dbo].[OrderDetails],Plants where  OrderDetails.FID=Plants.PID ) as e join Orders join Accounts on Orders.AccID=Accounts.accID on e.OrderID=Orders.OrderID and Accounts.email=? group by e.OrderID,OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email order by Total desc";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    order = new Order(OrderID, date, shipdate, Status, accID);
                    list.add(order);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrderPriceHighToLow() throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select sum(e.price*e.quantity)as'Total',e.OrderID as 'OrderID',OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email from (select [DetailID],[OrderID],[PID],PName,price,imgPath,quantity from [dbo].[OrderDetails],Plants where  OrderDetails.FID=Plants.PID ) as e join Orders join Accounts on Orders.AccID=Accounts.accID on e.OrderID=Orders.OrderID group by e.OrderID,OrdDate,shipdate,Orders.status,Orders.AccID, Accounts.email order by Total desc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    order = new Order(OrderID, date, shipdate, Status, accID);
                    list.add(order);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrders() throws Exception {
        //step 1: make connection
        ArrayList<Order> list = new ArrayList<>();
        Order ord = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet query sql & excute
            String sql = "select OrderID,OrdDate,shipdate,Orders.status,AccID from Orders";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int ordID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    ord = new Order(ordID, date, shipdate, Status, accID);
                    list.add(ord);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String keyword, String searchby) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Order ord = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null && searchby != null) {
            //step 2: viet query sql & excute
            String sql = "select OrderID,OrdDate,shipdate,Orders.status,AccID from Orders\n";
            if (searchby.equalsIgnoreCase("byorderID")) {
                sql = sql + "where OrderID =?";
            } else {
                sql = sql + "where AccID =?";
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            int num = Integer.parseInt(keyword);
            pst.setInt(1, num);
            ResultSet rs = pst.executeQuery();
            //step 3: xu ly ket qua cua step 2
            if (rs != null) {
                while (rs.next()) {
                    int ordID = rs.getInt("OrderID");
                    String date = rs.getString("OrdDate");
                    String shipdate = rs.getString("shipdate");
                    int accID = rs.getInt("AccID");
                    int Status = rs.getInt("status");
                    ord = new Order(ordID, date, shipdate, Status, accID);
                    list.add(ord);
                }
            }
            //step 4: close
            cn.close();
        }
        return list;
    }
}
