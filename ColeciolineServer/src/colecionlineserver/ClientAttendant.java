/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecionlineserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class ClientAttendant extends Thread{
    
    private Socket socket;
    private DataInputStream dt_istream;
    private DataOutputStream dt_ostream;
    
    
    public ClientAttendant(Socket socket){
        this.socket = socket;
        try {
            dt_ostream = new DataOutputStream(this.socket.getOutputStream());
            dt_istream = new DataInputStream(this.socket.getInputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(ClientAttendant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendItens(){
        DAO.conectar();
        LinkedList<Item> list = DAO.getItens();
        try {
            dt_ostream.writeInt(list.size());
            for(Item i : list){
                //ob_ostream.writeObject(i);
                Item.writeItem(dt_ostream, i);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientAttendant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        boolean close = false;
        String cmd;
        boolean ok = false;
        sendItens();
        while(!close){
            try {
                cmd = dt_istream.readUTF();
                switch(cmd){
                    case "++":
                        //DAO.inserir((Item)ob_istream.readObject());
                        ok = DAO.inserir(Item.readItem(dt_istream));
                        break;
                    case "--":
                        ok = DAO.delete(dt_istream.readInt());
                        break;
                    case "+-":
                        //DAO.update((Item)ob_istream.readObject());
                        ok = DAO.update(Item.readItem(dt_istream));
                        break;
                }
                dt_ostream.writeBoolean(ok);
                sendItens();
            } catch (IOException ex) {
                close = true;
            }
        }
        
//        try {
//            ob_ostream.close();
//            ob_ostream.close();
//            ostream.close();
//            istream.close();
//            socket.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ClientAttendant.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
