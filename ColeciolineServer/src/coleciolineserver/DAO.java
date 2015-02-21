/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coleciolineserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author carlosmagno
 */
public class DAO {
    static final String DB_URL = "jdbc:mysql://localhost/colecionline";
    static final String USER = "root";
    static final String PASS = "123456";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
}
