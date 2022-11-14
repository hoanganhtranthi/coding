/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import DAO.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hoang Anh
 */
public class changeProfileServlet extends HttpServlet {

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
            String name=request.getParameter("txtname");
           String phone=request.getParameter("txtphone");
           if(phone.matches("[a-zA-Z]+")){
                request.setAttribute("txtname", name);
                request.setAttribute("txtphone", phone);
                request.setAttribute("ERROR", "The Phone Is Invalid");
                request.getRequestDispatcher("changProfile.jsp").forward(request, response);
        }
            else{
            int status=1;
            int role=0;
             HttpSession session=request.getSession();
                  if(session!= null){
                      String email=(String) session.getAttribute("email");
                      Account acc=AccountDAO.getAccount(email);
                      String password=acc.getPassword();
                      if(acc != null){
                         if(AccountDAO.updateAccount(email, password, name, phone)){
                             request.setAttribute("ERROR", "Save Sucessfully!");
                             session.setAttribute("name", name);
                             session.setAttribute("phone", phone);
                             request.getRequestDispatcher("changProfile.jsp").forward(request, response);
                         }
                         request.setAttribute("ERROR", "Save Fail");
                         request.getRequestDispatcher("changProfile.jsp").forward(request, response);
                      }
                      request.setAttribute("ERROR", "You Must Login To Change Profile");
                      request.getRequestDispatcher("changProfile.jsp").forward(request, response);
                  }
           }
            
    }
        catch(Exception e){
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
