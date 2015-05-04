/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.servlet;

import br.com.moises.util.connection.ConnMariaDB;
import com.lowagie.text.pdf.PdfName;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
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
@WebServlet(name = "ComprovanteColeta",urlPatterns = "/relatorio_coleta")
public class ComprovanteColeta extends HttpServlet implements Serializable{
    private String relatorioPath;
    private String imagesPath;
    String numeroEmbarque="";

    

    

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String relatorio="";
       
        if(this.relatorioPath==null && this.imagesPath== null){
           relatorioPath = this.getServletContext().getRealPath("WEB-INF/classes/report/");
           imagesPath = this.getServletContext().getRealPath("WEB-INF/classes/images/");
       }
        System.out.println("Passou");
        try {
           relatorio = request.getParameter("relatorio");
           numeroEmbarque = request.getParameter("num");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       
       switch(relatorio){
           case "relatorio_coleta":
               gerarComprovante(request,response);
               break;
       }
        

    }

    private void gerarComprovante(HttpServletRequest request, HttpServletResponse response) {
        String arquivo = relatorioPath+"/"+"comprovante"+".jasper";
        System.out.println(arquivo);
        
        HashMap map = new HashMap();
        map.put("NUMERO_EMBARQUE", numeroEmbarque);
        map.put("LOGO_MARCA", imagesPath+"/semlogo.png");
        map.put("NOME_EMPRESA", "MINHA EMPRESA COMERCIO E EXPORTAÇÃO");
        try {
            byte[] pdf = JasperRunManager.runReportToPdf(arquivo, map,ConnMariaDB.getInstance().getConnection());
            mostrarRelatorio(pdf, response);
        } catch (JRException ex) {
            Logger.getLogger(ComprovanteColeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void mostrarRelatorio(byte[] pdf, HttpServletResponse response){
        try {
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf");
            out.write(pdf);
        } catch (Exception e) {
        }
        
       
    }

    
}
