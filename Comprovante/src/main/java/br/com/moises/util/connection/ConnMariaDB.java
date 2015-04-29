/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.swing.JOptionPane;

/**
 *
 * @author MOISES
 */
public class ConnMariaDB {

    private final String usuario;
    private final String senha;
    private String url = null;
    private final String driver;
    private final String porta;
    private ResultSet resultSet;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private static ConnMariaDB instance;

    public static ConnMariaDB getInstance() {
        if (instance == null) {
            instance = new ConnMariaDB();
            instance.conectar();
        }
        return instance;
    }

    public ConnMariaDB() {
        

        this.usuario = "root";
        this.senha = "1982";
        this.url = "jdbc:mysql://localhost:3306/comprovante?createDatabaseIfNotExist=true";
        this.driver = "org.mariadb.jdbc.Driver";
        this.porta = "3306";
    }

    private boolean conectar() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, senha);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println( "Erro JDBC " + e);
            return false;
        }
    }

    public void executeQuery(String sql) {
        try {

            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro JDBC " + ex);
            //Logger.getLogger(ConnMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void executeUpdate(String sql) {
        try {
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro JDBC " + ex);
            //Logger.getLogger(ConnMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fechar() {
        try {
            statement.getConnection().close();
           // JOptionPane.showMessageDialog(null, "Fechou");
        } catch (SQLException ex) {
            Logger.getLogger(ConnMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
    @Produces
    public Connection getConnection() {
        System.err.println("Pegou a conexão");
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void prepareStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

}
