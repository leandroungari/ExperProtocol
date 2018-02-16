/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import file.Arquivo;
import file.FileList;
import file.FilePath;
import file.InterfaceZIP;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leandroungari
 */
@WebServlet(name = "carregarArquivosPacote", urlPatterns = {"/carregarArquivosPacote"})
public class carregarArquivosPacote extends HttpServlet {

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
        
        
        Scanner servlet = new Scanner(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String json = "{\"filepath\": "+ servlet.nextLine() +"}";
        servlet.close();
        
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("filepath", FilePath.class);
        xstream.autodetectAnnotations(true);
        
        FilePath path = (FilePath) xstream.fromXML(json);
        ArrayList<File> listaArquivos = new ArrayList<>();
        
        FileList list = new FileList();
        
        ////
        Arquivo.getAllFiles(new File(path.getCaminho() + "/interpretacao_arquivos"), listaArquivos);
        
        for(File f: listaArquivos) {
            
            list.getListaInterpretacao().add(f.getName());
        }
        
        ////
        listaArquivos.clear();
        Arquivo.getAllFiles(new File(path.getCaminho() + "/artefato_arquivos"), listaArquivos);
        
        for(File f: listaArquivos) {
            
            list.getListaArtefato().add(f.getName());
        }
        
        //
        xstream.alias("filelist", FileList.class);
        xstream.autodetectAnnotations(true);
        
        PrintStream out = new PrintStream(response.getOutputStream(), true, "UTF-8");
        out.println(xstream.toXML(list));
        out.close();
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
