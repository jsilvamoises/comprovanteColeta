/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.servlet;

import br.com.moises.util.connection.ConnMariaDB;
import static java.lang.System.out;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;



/**
 *
 * @author MOISES
 */
@WebServlet(name = "RelatorioPessoa",urlPatterns = "/relatoriopessoa")
public class RelatorioPessoa extends HttpServlet{
    
    private static String javaPath;

    public RelatorioPessoa() {
        
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response){
        out.print(this.javaPath = this.getServletContext().getRealPath("WEB-INF/classes/"));
        if(this.javaPath==null){
             this.javaPath = this.getServletContext().getRealPath("WEB-INF/classes/");
        }
       
        
        String relatorio = request.getParameter("relatorio");
        
        if(relatorio.equals("embarque")){
            gerarRelatorio(request,response);
            
        }
    }

    private void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) {
       String relatorio = javaPath+"/embarque.jasper";
       
       HashMap parametros = new HashMap();
       
       parametros.put("EMBARQUE_NUMERO", new Long("1"));
       parametros.put("IMAGEM", "C:\\Users\\MOISES\\Pictures\\canstock24644228.jpg");
       
       Connection connection = ConnMariaDB.getInstance().getConnection();
        try {
            byte[] arquivo = JasperRunManager.runReportToPdf(relatorio, parametros,connection);
            
            mostrarRelatorio(arquivo,response);
            
        } catch (JRException ex) {
            Logger.getLogger(RelatorioPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarRelatorio(byte[] arquivo, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
