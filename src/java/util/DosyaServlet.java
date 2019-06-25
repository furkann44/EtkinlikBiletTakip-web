/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.DosyaController;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Furkan
 */
@WebServlet(name = "DosyaServlet", urlPatterns = {"/dosya/*"})
public class DosyaServlet extends HttpServlet {

    @Inject
    private DosyaController dc;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        String dosyaYolu = request.getPathInfo();
        File f = new File(dc.getUploadTo()+dosyaYolu);
        
        Files.copy(f.toPath(), response.getOutputStream());
    }
  
}
