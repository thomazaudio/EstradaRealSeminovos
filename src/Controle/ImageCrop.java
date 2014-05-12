/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletOutputStream;



/**
 *
 * @author Nalaka
 */

public class ImageCrop extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        try{
            
            //Valores de alteração da composição da imagem
            int t=Integer.parseInt(req.getParameter("t"));
            int l=Integer.parseInt(req.getParameter("l"));
            int w=Integer.parseInt(req.getParameter("w"));
            int h=Integer.parseInt(req.getParameter("h"));
            
            //image path
            String imagePath=getServletContext().getRealPath("/")+req.getParameter("i");
            
            //cropping the image
            BufferedImage outImage=ImageIO.read(new File(imagePath));
            BufferedImage cropped=outImage.getSubimage(l, t, w, h);
            
            //extention(jpg)
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            ImageIO.write(cropped,req.getParameter("f"), out);

            
            
            
            //getting the itemName
            String itemName=req.getParameter("itemName");
            
            
            
            //------------------------------------writing the cropped image to the 'cropped' folder (inside WEB)------------KNS---------------------
            String sorcePart1= getServletContext().getRealPath("/");
            int newLetCount = sorcePart1.length()-10;
            String newPath=  sorcePart1.substring(0, newLetCount);
            String sorcePart2 = newPath+"web/pic/cropped/";

            //Profile image of the member
            //String image = itemName+".jpg";
            String image = itemName;
            
            String FULLsourceParth=sorcePart2+image;
            System.out.println("Image path--------------" + FULLsourceParth);
            
            // save the file wit crop dimensions to the WEB folder........
            ImageIO.write(cropped,req.getParameter("f"), new File(FULLsourceParth)); 
            //--------------------------------------------------------------------------------------------------------------
            
            
            
            
            
            
            
            //------------------------------------writing the cropped image to the 'cropped' folder (inside BUILD)------------------KNS---------------
            
            String pathBuild=getServletContext().getRealPath("/pic/cropped/");
            String itemNameNewBuild = "/"+itemName+".jpg";
            String fullpathtoBuild=pathBuild+itemNameNewBuild;
            System.out.println("path to built------------------="+pathBuild+itemNameNewBuild );

            // save the file with crop dimensions to the BUILD folder........
            ImageIO.write(cropped,req.getParameter("f"), new File(fullpathtoBuild)); 
            //---------------------------------------------------------------------------------------------------------------
            
            
            //redirecting to the final.jsp page after cropping
            RequestDispatcher rd;
            rd= req.getRequestDispatcher("Final.jsp");
            rd.forward(req, res);
            
            
            
            
        }
        catch(Exception ex){
            System.out.println("exception...! " + ex);
        }
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
