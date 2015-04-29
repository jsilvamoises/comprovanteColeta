package br.com.moises.bean;

import br.com.moises.util.connection.ConnMariaDB;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import javax.faces.context.FacesContext;  
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletResponse;  
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import br.com.intracred.conexao.DB;  
  
@ManagedBean(name="relatorioExemplo")  
@ViewScoped  
  
public class RelatorioExemploBean implements Serializable{  
  
    private static final long serialVersionUID = -2215327084088480464L;  
    //DB db = new DB();
//    @Inject
//    Connection db;
//    @Inject
//    HttpServletResponse response;
//    @Inject
//    ExternalContext externalContext;
//    public void gerarRelatorio() {  
//       // ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
//        ServletContext context = (ServletContext) externalContext.getContext();  
//        String arquivo = "File\\C:\\jasper\\embarque.jasper";// context.getRealPath("File\\C:\\jasper\\embarque.jasper");   
//        System.err.println(arquivo);
//        Map<String, Object> parametros = new HashMap<String, Object>();  
//        parametros.put("EMBARQUE_NUMERO", 1);
//        ServletOutputStream servletOutputStream = null;  
//        FacesContext fc = FacesContext.getCurrentInstance();  
//      //  HttpServletResponse response=null;// = (HttpServletResponse) fc.getExternalContext().getResponse();  
//   //response.getOutputStream()
//        try {  
//            servletOutputStream = response.getOutputStream();  
//            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), (OutputStream) response, parametros, db);  
//            response.setContentType("application/pdf");  
//            servletOutputStream.flush();  
//            servletOutputStream.close();  
//            fc.renderResponse();  
//            fc.responseComplete();  
//            fc.renderResponse();  
//            response.flushBuffer();
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    } 
    
    public void teste(){
          try{
                    
                    FacesContext context = FacesContext.getCurrentInstance();    
                    ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();    
                    String caminhoRelatorio = servletContext.getRealPath("C:\\jasper\\embarque.jasper");    
                    System.out.println("caminhoRelatorio :"+caminhoRelatorio);
                    HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();    
                    response.setContentType("application/pdf");    
                    response.addHeader("Content-disposition", "attachment; filename=\"FolhaPonto.pdf\"");    
                    JasperPrint impressao = JasperFillManager.fillReport(caminhoRelatorio, null, new ConnMariaDB().getConnection());    
                    System.out.println("impressao :"+impressao);
                    JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());                        
                    
                    context.getApplication().getStateManager().saveView(context);    
                    context.renderResponse();
                    context.responseComplete();                                                            
                    
                }catch(Exception e){
                    System.err.println(e);
                }

    }
   
  
}  
