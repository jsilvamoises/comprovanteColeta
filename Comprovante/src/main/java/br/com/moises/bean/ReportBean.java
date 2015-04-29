/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.util.connection.ConnMariaDB;

import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class ReportBean implements Serializable {

    // @Inject
    private Connection conn;
    // @Inject
    private HttpServletResponse response;
    private String caminhoRelatorio;
    private HashMap<String, Object> parametros = new HashMap<>();
    private String nomeSaida;

    public void gerar() {
        FacesContext context = FacesContext.getCurrentInstance();
        conn = ConnMariaDB.getInstance().getConnection();
        response = (HttpServletResponse) context.getExternalContext().getResponse();
        System.err.println("Passou Aqui 1");
        nomeSaida = "moises.pdf";
        caminhoRelatorio = "E:\\@Planilhas para Venda\\Java\\1.0\\comprovanteColeta\\Comprovante\\src\\main\\java\\br\\com\\moises\\report\\embarque.jasper";
        parametros.put("EMBARQUE_NUMERO", new Long("1"));
        parametros.put("IMAGEM", "C:\\Users\\MOISES\\Pictures\\canstock24644228.jpg");
        System.err.println("Passou Aqui 2");
        try {
            System.err.println("Passou Aqui 3");
           // String caminho = getClass().getResourceAsStream("../report/comprovante.jasper").toString();
            //  System.err.println("Caminho "+caminho);
            //  InputStream relatorioStream = this.getClass().getResourceAsStream(caminhoRelatorio);
            // System.err.println("Passou Aqui 4"+ relatorioStream.toString());

            String pathJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/br/com/moises/report/");

            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

            String a = getClass().getResource("/src/").toString();
            System.err.println(a);
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            String caminhoRelatorio = servletContext.getRealPath(pathJasper + "embarque.jasper");

            String reportDefinition = "/report/embarque.jasper";
            // System.err.println(System.getProperty("user.dir"));
            InputStream reportDefinitionStream = getClass().getResourceAsStream(reportDefinition);
            JasperPrint jp = JasperFillManager.fillReport(reportDefinitionStream, parametros, conn);
            System.err.println("Passou Aqui 5");
            JRExporter exportador = new JRPdfExporter();
            exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            exportador.setParameter(JRExporterParameter.JASPER_PRINT, jp);

            response.setContentType("application/pdf");
            exportador.exportReport();

        } catch (JRException | IOException e) {
            System.err.println(e);
        }

    }

    public void dois() {
        try {

            URL arquivo = getClass().getResource("/src/br/com/moises/report/embarque.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);

            Map map = null;
            Connection con = null;

            // Aqui eu estou passando um jasperReport. Mas eu testei com um inputStream como Cristiano.Zanata disse e funcionada tbm. :)  
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRDataSource() {

                private int max = 3;
                private int cont = 0;
                private String valor;

                public Object getFieldValue(JRField jrField) throws JRException {

                    return "main";
                }

                public boolean next() throws JRException {
                    // TODO Auto-generated method stub  
                    if (cont++ >= max) {
                        System.out.println("false");
                        return false;
                    } else {
                        System.out.println("true");
                        return true;
                    }
                }
            });

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

        } catch (JRException ex) {
            //Logger.getLogger(TentandoFazerUmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
