/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecionlineserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosmagno
 */
public class ColeciolineServer {
    
    ServerSocket s_socket;
    Executor attendants_pool;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ColeciolineServer server = new ColeciolineServer();
        server.init();
    }
    
    public ColeciolineServer(){
        try {
            s_socket =  new ServerSocket(4444);
            attendants_pool = Executors.newCachedThreadPool();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void init(){
        while(true){
            try {
                ClientAttendant attendant = new ClientAttendant(s_socket.accept());
                attendants_pool.execute(attendant);
                System.out.println("Aceitei uma conexão! \\o/");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
}
