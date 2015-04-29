/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.util.report;

import br.com.moises.util.connection.ConnMariaDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
// * @author MOISES
 */
public class GeraPDF extends HttpServlet{
    HashMap parametros;
    private String nome;
    PrintWriter out;

    public GeraPDF(String nome, HashMap parametros) {
        this.nome = nome;
        this.parametros = parametros;
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
       response.setContentType("text/html;charset=UTF-8");
       response.setLocale(new Locale("pt_BR"));
        out = response.getWriter();
        
       
    }
    
    
    public void gerar(){
         try {
            Connection conn = ConnMariaDB.getInstance().getConnection();
            
            
            ServletContext contexto = getServletContext();
            
            JasperPrint jp = JasperFillManager.fillReport("/report/comprovante.jasper", parametros,conn);
            
            JasperViewer jrv = new JasperViewer(jp);
            jrv.setVisible(true);
            
        } catch (Exception e) {
            out.close();
        }
    }
    
    public static void main(String[] args) {
        new GeraPDF(null, null).gerar();
    }
}
