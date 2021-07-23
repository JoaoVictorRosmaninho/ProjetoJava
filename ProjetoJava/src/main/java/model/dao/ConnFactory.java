/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jv
 */
public class ConnFactory {
    public static Connection getConnection() throws SQLException {
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GenSenha", "jv", "123");
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/senhas", "root", "1234");
        return conn;
    }
}
