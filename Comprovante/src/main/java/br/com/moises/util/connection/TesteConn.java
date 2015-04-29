/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.moises.util.connection;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MOISES
 */
public class TesteConn {
    public static void main(String args[]) throws SQLException{
        new TesteConn().testar();
    }
    public void testar() throws SQLException{
        ConnMariaDB cjmd = ConnMariaDB.getInstance();
       
        cjmd.executeQuery("SELECT * FROM tbl_cliente");
        System.err.println(cjmd.getResultSet().getMetaData().getCatalogName(1));
        try {
            JOptionPane.showMessageDialog(null, cjmd.getStatement().getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(TesteConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
