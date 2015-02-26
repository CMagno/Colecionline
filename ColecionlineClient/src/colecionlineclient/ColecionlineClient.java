/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecionlineclient;

import colecionlineserver.Item;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosmagno
 */
public class ColecionlineClient {
    
    private Socket socket;
    private DataInputStream dt_istream;
    private DataOutputStream dt_ostream;
    
    public ColecionlineClient(){
        try {
            socket = new Socket("127.0.0.1",4444);
            dt_ostream = new DataOutputStream(this.socket.getOutputStream());
            dt_istream = new DataInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ColecionlineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LinkedList<Item> receiveItens(){
        LinkedList<Item> list = new LinkedList<>();
        try {
            int n_itens = dt_istream.readInt();
            for(int i = 0; i < n_itens; i++){
                list.add(Item.readItem(dt_istream));
            }
        } catch (IOException ex) {
            Logger.getLogger(ColecionlineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean cadastra(Item i){
        try {
            dt_ostream.writeUTF("++");
            Item.writeItem(dt_ostream, i);
            return dt_istream.readBoolean();
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean exclui(int id){
        try {
            dt_ostream.writeUTF("--");
            dt_ostream.writeInt(id);
            return dt_istream.readBoolean();
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean edita(Item i){
        try {
            dt_ostream.writeUTF("+-");
            Item.writeItem(dt_ostream, i);
            return dt_istream.readBoolean();
        } catch (IOException ex) {
            return false;
        }
    }
}
