/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.report.GerarRelatorio;
import br.com.moises.report.TipoRelatorio;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MOISES
 * Paramentros para geração do relatorio
 * NUMERO_EMBARQUE
 * LOGO_MARCA 
 * NOME_EMPRESA
 */
@ManagedBean
@ViewScoped
public class RelatorioEmbarqueBean implements Serializable{
    private static final Long serialVerionIUID=1L;
    @Inject
    private HttpServletResponse response;
    @Inject
    private GerarRelatorio gr;
    
    public void gerarRelatorioEmbarque(Long id) {
        System.err.println("Id Passado :"+id);
       
        Map map = new HashMap();
       
        map.put("NUMERO_EMBARQUE", String.valueOf(id));
        map.put("LOGO_MARCA", "C:\\Users\\MOISES\\Pictures\\canstock24644228.jpg");
        map.put("NOME_EMPRESA", "POLIMPORT COMÉRCIO E EXPORTAÇÃO");
        gr.gerar(map, "comprovante.pdf", "comprovante", TipoRelatorio.PDF);
        
        
        
    }
    
    public void gerar() {
     
        Map map = new HashMap();
        
        map.put("NUMERO_EMBARQUE", String.valueOf(52));
        map.put("LOGO_MARCA", "C:\\Users\\MOISES\\Pictures\\canstock24644228.jpg");
        map.put("NOME_EMPRESA", "POLIMPORT COMÉRCIO E EXPORTAÇÃO");
        gr.gerar(map, "comprovante.pdf", "comprovante", TipoRelatorio.PDF);
        
        
        
    }
    
  
   
    
}
