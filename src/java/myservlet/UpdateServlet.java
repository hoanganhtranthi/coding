/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import DAO.PlanDAO;
import dto.Plant;
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
public class UpdateServlet extends HttpServlet {

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
            String searchValue = request.getParameter("txtSearch");
             String searchbyValue = request.getParameter("txtSearchBy");
              request.setAttribute("searchbyValue", searchbyValue);
            request.setAttribute("searchValue", searchValue);
                String pid = request.getParameter("txtId");
                request.setAttribute("txtID", pid);
                int id=Integer.parseInt(pid.trim());
                
                 String name = request.getParameter("txtname");
                 if(name==null){
                 request.getRequestDispatcher("updatePlant.jsp").forward(request, response);
                 }
                 else{
                 String image=request.getParameter("txtimg");
                 String description=request.getParameter("txtdescription");
                 String p=request.getParameter("txtprice");
                 int price=Integer.parseInt(p.trim());
                 String cid=request.getParameter("txtcateID");
                 int cateID=Integer.parseInt(cid.trim());
                 if(PlanDAO.updatePlant(id, name, image, description, cateID, price)){
                     request.setAttribute("ERROR", "Save Sucessfully!");
                    if(searchValue.isEmpty() && searchbyValue.isEmpty()){

                    ArrayList<Plant> list1=PlanDAO.getPlants("", "");
                    request.setAttribute("plantList", list1);
                    request.getRequestDispatcher("ManagePlants.jsp").forward(request, response);
                    }else{
                   ArrayList<Plant> list=PlanDAO.getPlants(searchValue, searchbyValue);
                   request.setAttribute("planList", list);
                   request.getRequestDispatcher("ManagePlants.jsp").forward(request, response);
               } 
                 }
                 }
               
        }catch(Exception e){
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
