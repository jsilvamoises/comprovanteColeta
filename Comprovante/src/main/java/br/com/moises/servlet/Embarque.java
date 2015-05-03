/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.servlet;

import br.com.moises.bean.RelatorioEmbarqueBean;
import br.com.moises.model.ItensEmbarque;
import br.com.moises.suport.EmbarqueSuport;
import br.com.moises.suport.ItensEmbarqueSuport;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MOISES
 */

@WebServlet(name = "Embarque", urlPatterns = "/embarque")
public class Embarque extends HttpServlet implements Serializable{

    private List<ItensEmbarque> itens = new ArrayList<>();

    @Inject
    private br.com.moises.model.Embarque embarque;
    @Inject
    private EmbarqueSuport embarqueSuport;
    @Inject
    private ItensEmbarqueSuport itSuport;
    @Inject
    private RelatorioEmbarqueBean reb;

    private String nomeDestinatario = "";

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        out.print("Pasou auqe");
        
        try {
            Document document = new Document(PageSize.A4, 30, 20, 20, 30);
            OutputStream outputStream = new FileOutputStream("embarque.pdf");
            //   response.setContentType("application/pdf");
            Long id = Long.parseLong(request.getParameter("num_embarque"));
            //recupera o embarque
            embarque = embarqueSuport.getEmbarqueById(id);
            itens = itSuport.itensEmbarquePorEmbarque(embarque);
            out.print(itens.size());

           PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            // Titulo

            document.addTitle("Comprovante de Coleta");

            Paragraph titulo = new Paragraph("Comprovante de Coleta");
//            //
           document.add(titulo);
           document.add(new Paragraph("\n\n"));
            PdfPTable topo = new PdfPTable(2);
            Image image = Image.getInstance("E:\\icons\\32\\cliente.png");
           
            topo.getDefaultCell().setBorder(0);
            topo.getDefaultCell().addElement(image);
            topo.addCell(image);
            topo.addCell("MOISES JUVENAL DA SILVA\n"
                    + "Rua.: Bertioga Nº 49"
                    + "Jardim América I"
                    + "Várzea Paulista SP");
           
            document.add(topo);
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("TRANSP.: " + embarque.getTransportadora().getId() + " - " + embarque.getTransportadora().getNome(), FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLUE)));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph(new Date().toString()));

            PdfPTable table = new PdfPTable(2);
            table.addCell("");

            for (ItensEmbarque l : itens) {
                document.add(new Paragraph(String.valueOf(l.getId())));
            }
            // step 5
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }

    }

}
