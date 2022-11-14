/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import DAO.AccountDAO;
import DAO.OrderDAO;
import dto.Account;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hoang Anh
 */
public class dangnhapServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           String email=request.getParameter("txtemail");
           String password=request.getParameter("txtpassword");
           String save=request.getParameter("savelogin");
           try{
               if(email == null || email.equals("") || password==null || password.equals("")){
                   Cookie[] c=request.getCookies();
                   String token="";
                   if(c!=null){
                       for (Cookie cookie : c) {
                           if(cookie.getName().equals("selector")){
                               token=cookie.getValue();
                       }
                   }
                   }
                   if(!token.equals(""))
                       response.sendRedirect("personalPage");
                   else response.sendRedirect("errorpage.html");
               }
           }catch(Exception e){
               e.printStackTrace();
           }
           Account acc=AccountDAO.getAccount(email, password);
           if(acc!=null){
              if(acc.getRole()==1){
                  HttpSession session=request.getSession();
                  if(session!=null){
                      session.setAttribute("name",acc.getFullname());
                      session.setAttribute("email",email);
                      if(save!=null){
                          String token="ABC123";
                          AccountDAO.updateToken(email, token);
                          Cookie cookie=new Cookie("selector",token);
                          cookie.setMaxAge(60*2);
                          response.addCookie(cookie);
                      }
                      response.sendRedirect("AdminIndex.jsp");
                  }
              } 
              else{
                  HttpSession session=request.getSession(true);
                  if(session!= null){
                      session.setAttribute("name", acc.getFullname());
                      session.setAttribute("email", email);
                       ArrayList<Order> list=OrderDAO.getOrders(email);
                       request.setAttribute("orderList", list);
                     if(save !=null){
                         String token="ABC123";
                         AccountDAO.updateToken(email, token);
                         Cookie cookie=new Cookie("selector", token);
                         cookie.setMaxAge(60*3);
                         response.addCookie(cookie);
                     }
                     response.sendRedirect("personalPage");
                  }
              }
           }
           else response.sendRedirect("errorpage.html");
        }
        catch (Exception e){
           e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
