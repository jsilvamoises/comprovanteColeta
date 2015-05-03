/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.report;

import br.com.moises.util.connection.ConnMariaDB;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author MOISES
 */
public class GerarRelatorio implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String caminhoRelatorio;
    @Inject
    private  HttpServletResponse response;
    @Inject
    FacesContext context;
    private Map parametros;
    
    JRExporter exportador;

    public GerarRelatorio() {
      
    }

    

    public void gerar(Map map, String nomeArquivoSaida, String nomeRelatorio, TipoRelatorio tipo) {
       
     
        System.out.println("Passou aqui");
        try {
            InputStream relatorioStream = this.getClass().getResourceAsStream("/report/".concat(nomeRelatorio).concat(".jasper"));

            JasperPrint print = JasperFillManager.fillReport(relatorioStream, map, ConnMariaDB.getInstance().getConnection());
            gerar(TipoRelatorio.PDF);
            
            
            exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
            

        } catch (JRException e) {
            System.out.println(e);
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gerar(TipoRelatorio tipo) {
        switch (tipo) {
            case HTML:
                exportador = new JRPdfExporter();
                break;
            case PDF:
               exportador = new JRPdfExporter();         
                
                 
                break;
            case XML:
                exportador = new JRPdfExporter();
                break;
            default:
                break;
        }
         System.out.println("Findou");
    }

   

}
