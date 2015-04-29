/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.util.connection.ConnMariaDB;
import java.awt.HeadlessException;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MOISES
 */
public class GerarRelatorioSql {
    @Inject
    private ConnMariaDB con ;//= ConnectionJdbdMariaDb.getInstance();

    public static final String PACOTE_RELATORIO = "/br/com/mjs/report/";
    public static String CAMINHO;

    public GerarRelatorioSql() {
        CAMINHO = getClass().getResource(PACOTE_RELATORIO).toString();
    }

    public static void main(String[] args) {

      //  new GerarRelatorioSql().visualizar(new ClienteControl().listAll(), "relClientes");
    }

    @Deprecated
    private void visualizar(List lista, String nomeReal) {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File(nomeReal));

        jf.setMultiSelectionEnabled(false);

        try {

            List<?> dataSource = lista;
            HashMap params = new HashMap();

            URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataSource);
//            String report = 
//                    System.getProperty("user.dir")+"/src/br/com/mjs/report/"+nomeRel+".jasper";
//             JOptionPane.showConfirmDialog(null, report);
            JasperPrint impressao = JasperFillManager.fillReport(jr, params, ds);
            JasperViewer jv = new JasperViewer(impressao, false);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setTitle(nomeReal);
            jv.setVisible(true);

        } catch (HeadlessException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    @Deprecated
    public void visualizarPDF(String nomeReal, String sql) {

        con.executeQuery(sql);
        JasperPrint jp = null;
        try {

            // List<?> dataSource = lista;
            // URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
            // JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
            HashMap hashMap = new HashMap();
            JRResultSetDataSource jrrsds = new JRResultSetDataSource(con.getResultSet());
            try {
                jp = JasperFillManager.fillReport("src/br/com/mjs/report/" + nomeReal + ".jasper", hashMap, jrrsds);
            } catch (JRException e) {
                URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                jp = JasperFillManager.fillReport(jr, hashMap, jrrsds);
            }

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setVisible(true);

        } catch (HeadlessException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    @Deprecated
    public void exportToPDF(String nomeReal, String sql) {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File(nomeReal));

        jf.setMultiSelectionEnabled(false);
        int opcao = jf.showSaveDialog(null);
        if (opcao == JFileChooser.APPROVE_OPTION) {

            con.executeQuery(sql);
            JasperPrint jp = null;
            try {

                // List<?> dataSource = lista;
                // URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                // JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                HashMap hashMap = new HashMap();
                JRResultSetDataSource jrrsds = new JRResultSetDataSource(con.getResultSet());
                try {
                    jp = JasperFillManager.fillReport("src/br/com/mjs/report/" + nomeReal + ".jasper", hashMap, jrrsds);
                } catch (JRException e) {
                    URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                    JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                    jp = JasperFillManager.fillReport(jr, hashMap, jrrsds);
                }
                JasperExportManager.exportReportToPdfFile(jp, jf.getSelectedFile() + ".pdf");
                mensagem("Arquivo salvo com sucesso no diretorio" + jf.getSelectedFile());
                //JasperViewer jv = new JasperViewer(jp,false);
                //jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                // jv.setVisible(true);

            } catch (HeadlessException | JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }
    @Deprecated
    public void exportToHTML(String nomeReal, String sql) {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File(nomeReal));

        jf.setMultiSelectionEnabled(false);
        int opcao = jf.showSaveDialog(null);
        if (opcao == JFileChooser.APPROVE_OPTION) {

            con.executeQuery(sql);
            JasperPrint jp = null;
            try {

                // List<?> dataSource = lista;
                // URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                // JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                HashMap hashMap = new HashMap();
                JRResultSetDataSource jrrsds = new JRResultSetDataSource(con.getResultSet());
                try {
                    jp = JasperFillManager.fillReport("src/br/com/mjs/report/" + nomeReal + ".jasper", hashMap, jrrsds);
                } catch (JRException e) {
                    URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                    JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                    jp = JasperFillManager.fillReport(jr, hashMap, jrrsds);
                }
                JasperExportManager.exportReportToHtmlFile(jp, jf.getSelectedFile() + ".html");
                mensagem("Arquivo salvo com sucesso no diretorio" + jf.getSelectedFile());
                //JasperViewer jv = new JasperViewer(jp,false);
                //jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                // jv.setVisible(true);

            } catch (HeadlessException | JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }

    /**
     *
     * @param nomeReal
     * @param lista
     */
    @Deprecated
    public void exportToXML(String nomeReal, String sql) {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File(nomeReal));

        jf.setMultiSelectionEnabled(false);
        int opcao = jf.showSaveDialog(null);
        if (opcao == JFileChooser.APPROVE_OPTION) {

            con.executeQuery(sql);
            JasperPrint jp = null;
            try {

                // List<?> dataSource = lista;
                // URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                // JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                HashMap hashMap = new HashMap();
                JRResultSetDataSource jrrsds = new JRResultSetDataSource(con.getResultSet());
                try {
                    jp = JasperFillManager.fillReport("src/br/com/mjs/report/" + nomeReal + ".jasper", hashMap, jrrsds);
                } catch (JRException e) {
                    URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                    JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                    jp = JasperFillManager.fillReport(jr, hashMap, jrrsds);
                }
                JasperExportManager.exportReportToXmlFile(jp, jf.getSelectedFile() + ".xml", true);
                mensagem("Arquivo salvo com sucesso no diretorio" + jf.getSelectedFile());
                //JasperViewer jv = new JasperViewer(jp,false);
                //jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                // jv.setVisible(true);

            } catch (HeadlessException | JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }

    /**
     * Envia um documento para impressora e imprime toda a lista enviada.
     *
     * @param nomeReal
     * @param lista
     */
    @Deprecated
    public void imprimirRelatorio(String nomeReal, String sql) {

        con.executeQuery(sql);
        JasperPrint jp = null;
        try {

            // List<?> dataSource = lista;
            // URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
            // JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
            HashMap hashMap = new HashMap();
            JRResultSetDataSource jrrsds = new JRResultSetDataSource(con.getResultSet());
            try {
                jp = JasperFillManager.fillReport("src/br/com/mjs/report/" + nomeReal + ".jasper", hashMap, jrrsds);
            } catch (JRException e) {
                URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                jp = JasperFillManager.fillReport(jr, hashMap, jrrsds);
            }
            //int i = con.getStatement().getResultSet().last();
            JasperPrintManager.printPages(jp, 0, jp.getPages().size() - 1, true);
            //JasperViewer jv = new JasperViewer(jp,false);
            //jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            // jv.setVisible(true);

        } catch (HeadlessException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

   

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public enum opcaoRelatorio {

        VISUALIZAR_PDF, SALVAR_PDF, EXPORTAR_XML, EXPORTAR_HTML, IMPRIMIR
    }

    public void gerarRelatorio(String nomeReal, String sql, opcaoRelatorio oRelatorio, boolean mostrarPrintDialog) {

        con.executeQuery(sql);
        JasperPrint jp = null;
        try {

            HashMap hashMap = new HashMap();
            JRResultSetDataSource jrrsds = new JRResultSetDataSource(con.getResultSet());

            try {
                jp = JasperFillManager.fillReport("src/br/com/mjs/report/" + nomeReal + ".jasper", hashMap, jrrsds);
            } catch (JRException e) {
                URL arquivo = getClass().getResource("/br/com/mjs/report/" + nomeReal + ".jasper");
                JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
                jp = JasperFillManager.fillReport(jr, hashMap, jrrsds);
            }
            String caminho;
            switch (oRelatorio) {

                case EXPORTAR_HTML:
                    caminho = getCaminhoParaSalvar(nomeReal) + ".html";
                    JasperExportManager.exportReportToHtmlFile(jp, caminho);
                    mensagem("Arquivo salvo com sucesso no diretorio" + caminho);
                    break;
                case EXPORTAR_XML:
                    caminho = getCaminhoParaSalvar(nomeReal) + ".xml";
                    JasperExportManager.exportReportToXmlFile(jp, caminho, true);
                    mensagem("Arquivo salvo com sucesso no diretorio" + caminho);
                    break;
                case SALVAR_PDF:
                    caminho = getCaminhoParaSalvar(nomeReal) + ".pdf";
                    JasperExportManager.exportReportToPdfFile(jp, caminho);
                    mensagem("Arquivo salvo com sucesso no diretorio" + caminho);
                    break;
                case VISUALIZAR_PDF:
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    jv.setVisible(true);
                    break;
                case IMPRIMIR:
                    JasperPrintManager.printPages(jp, 0, jp.getPages().size() - 1, mostrarPrintDialog);

                    break;
            }

        } catch (HeadlessException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
   
    private String getCaminhoParaSalvar(String nomeRelatorio) {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File(nomeRelatorio));

        jf.setMultiSelectionEnabled(false);
        int opcao = jf.showSaveDialog(null);
        if (opcao == JFileChooser.APPROVE_OPTION) {
            return "" + jf.getSelectedFile().toString();
        } else {
            return "CANCELADO";
        }

    }

}
