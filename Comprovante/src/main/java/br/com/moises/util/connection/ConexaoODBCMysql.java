/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.moises.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MOISES
 */

public class ConexaoODBCMysql {
    private String driver;
    private String porta;
    private String url;
    private String usuario;
    private String password;
    private Connection conn;
    private Statement statement;
    private ResultSet resultset;
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    private static ConexaoODBCMysql instace;
    public static ConexaoODBCMysql getInstace(){
        
        if(instace==null){
            instace = new ConexaoODBCMysql();
        }
        return instace;
    }
    public void conectar(){
        config();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,usuario,password);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException | SQLException e) {
        }
        
    }
    public void config(){
        driver = "org.gjt.mm.mysql.Driver";
        url = "jdbc.mysql://127.0.0.1:3306/comprovante";
        porta = "3306";
        usuario="root";
        password = "1982";
    }
    public ResultSet executeSql(String sql){
        try {
            resultset = statement.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            //Logger.getLogger(ConexaoODBCMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultset;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ResultSet getResultset() {
        return resultset;
    }

    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }
}
