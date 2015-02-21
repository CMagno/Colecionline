/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coleciolineserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    public static Connection conectar() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
            
    
    public void inserir(Item item){
        
        
        
    }
}
