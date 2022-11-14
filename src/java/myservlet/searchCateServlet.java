/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import DAO.CateDAO;
import dto.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang Anh
 */
public class searchCateServlet extends HttpServlet {

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
            String action=request.getParameter("action");
            if(action.equals("searchCate")){
           String keyword = request.getParameter("txtsearch");
            String searchby = request.getParameter("txtsearchby");
            if(keyword==null){
                    ArrayList<Category> list1=CateDAO.getCategories("","");
                    request.setAttribute("categoriesList", list1);
            request.getRequestDispatcher("ManageCategories.jsp").forward(request, response);
             }else{
                ArrayList<Category> list = CateDAO.getCategories(keyword, searchby);
                 request.setAttribute("categoriesList",list );
            request.getRequestDispatcher("ManageCategories.jsp").forward(request, response);
        }
            }
            else if(action.equals("AddCategories")){
            String name=request.getParameter("txtName");
            if(name==null){
                request.getRequestDispatcher("addCate.jsp").forward(request, response);
            }
        }
        } catch (Exception e) {
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
