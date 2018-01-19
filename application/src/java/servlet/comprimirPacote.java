/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bpmn.factory.json.Protocol;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import file.Arquivo;
import file.InterfaceZIP;
import file.LabPackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leandroungari
 */
@WebServlet(name = "comprimirPacote", urlPatterns = {"/comprimirPacote"})
public class comprimirPacote extends HttpServlet {

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

        Scanner servlet = new Scanner(new InputStreamReader(request.getInputStream()));

        String json = "{\"interface\": " + servlet.nextLine() + "}";
        servlet.close();

        System.out.println(json);

        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("interface", InterfaceZIP.class);
        xstream.autodetectAnnotations(true);

        InterfaceZIP i = (InterfaceZIP) xstream.fromXML(json);

        //System.out.println(i);
        //return (Protocol) xstream.fromXML(json);
        LabPackage pacote = LabPackage.getInstance(i);

        ArrayList<File> listaArquivos = new ArrayList<>();

        Arquivo.getAllFiles(new File(pacote.getCaminho()), listaArquivos);
        /*
        for(File f: listaArquivos){
            
            System.out.println(f.getName());
        }*/

        String caminhoPasta = pacote.getCaminho() + "/" + pacote.getNome();
        File pasta = new File(caminhoPasta);
        if (pasta.mkdirs()) {

            //adicionado o arquivo principal do xml
            File principal = null;

            for (File f : listaArquivos) {

                if (f.getName().equals(i.getNome())) {

                    principal = f;
                }
            }

            if (principal != null) {
                Arquivo.upload(caminhoPasta, i.getNome(), new FileInputStream(principal));
                principal.delete();
            }

            //arquivos interpretacao
            String interpretacaoPasta = caminhoPasta + "/interpretacao_arquivos";
            File pastaInterpretacao = new File(interpretacaoPasta);
            if (pastaInterpretacao.mkdirs()) {

                for (File f : listaArquivos) {

                    if (pacote.getInterpretacoes() != null && pacote.getInterpretacoes().indexOf(f.getName()) != -1) {

                        Arquivo.upload(interpretacaoPasta, f.getName(), new FileInputStream(f));
                        f.delete();
                    }
                }

            }

            //arquivos interpretacao
            String artefatoPasta = caminhoPasta + "/artefato_arquivos";
            File pastaArtefato = new File(artefatoPasta);
            if (pastaArtefato.mkdirs()) {

                for (File f : listaArquivos) {

                    if (pacote.getArtefatos() != null && pacote.getArtefatos().indexOf(f.getName()) != -1) {

                        Arquivo.upload(artefatoPasta, f.getName(), new FileInputStream(f));
                        f.delete();
                    }
                }

            }

            /*try {
                
                
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pacote.getCaminho() + "/" + pacote.getNome() + ".zip"));
                
                Arquivo.toZip(caminhoPasta, zos); 
    
                zos.close();
            } catch (Exception e) {
                
                System.out.println(e);
            }*/
            
            
            String zipFile = pacote.getCaminho() + "/" + pacote.getNome() + ".zip";
            String base = pacote.getCaminho() + "/";
            String srcDir = caminhoPasta;

            try {

                FileOutputStream fos = new FileOutputStream(zipFile);

                ZipOutputStream zos = new ZipOutputStream(fos);

                File srcFile = new File(srcDir);

                Arquivo.addDirToArchive(zos, srcFile, base);

                // close the ZipOutputStream
                zos.close();
                
                Arquivo.delete(new File(caminhoPasta));

            } catch (IOException ioe) {
                System.out.println("Error creating zip file: " + ioe);
            }
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