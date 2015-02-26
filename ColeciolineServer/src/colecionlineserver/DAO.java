/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecionlineserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
    
    public static void conectar() {
        if(conn == null){
            try {
                //PASSO 2: Registrar JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                //PASSO 3: Abrir conexão
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
            }catch (ClassNotFoundException ex) {
                System.err.print(ex.getMessage());
            }catch (SQLException ex) {
                System.err.print(ex.getMessage());
            }
        }
    }
    
    public static synchronized boolean inserir(Item item){
        try {
            Statement stmt = conn.createStatement();
            boolean res = stmt.execute("INSERT INTO item (isbn, nome, autor, genero, paginas, descricao, qtde) "+
                                "VALUES ("+item.getIsbn()+",\'"+item.getNome()+"\',\'"+item.getAutor()+"\',\'"+item.getGenero()+"\',"
                                          +item.getPaginas()+",\'"+item.getDescricao()+"\'," +item.getQtde()+")");
            stmt.close();
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static synchronized boolean update(Item item){
        try {
            Statement stmt = conn.createStatement();
            boolean res = stmt.execute("UPDATE item SET "
                                              +"isbn="+item.getIsbn()+", "
                                              +"nome=\'"+item.getNome()+"\', "
                                              +"autor=\'"+item.getAutor()+"\', "
                                              +"genero=\'"+item.getGenero()+"\', "
                                              +"paginas="+item.getPaginas()+", "
                                              +"descricao=\'"+item.getDescricao()+"\', "
                                              +"qtde="+item.getQtde()+
                                              " WHERE id="+item.getId()+" ");
            stmt.close();
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static synchronized boolean delete(int id){
        try {
            Statement stmt = conn.createStatement();
            return stmt.execute("DELETE FROM item WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static synchronized LinkedList<Item> getItens(){
        LinkedList<Item> list = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM item");
            while(res.next()){
                list.add(new Item(res.getInt("id"),res.getInt("isbn"),res.getString("nome"), res.getString("autor"), res.getString("genero"), res.getString("descricao"), res.getInt("paginas"), res.getInt("qtde")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
