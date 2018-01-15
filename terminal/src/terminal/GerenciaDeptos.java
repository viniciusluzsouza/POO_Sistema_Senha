/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensagens.InformaDepartamentos;
import mensagens.MensagemTerminal;

/**
 *
 * @author vini
 */
public class GerenciaDeptos implements Runnable {
    
    Socket s;
    MensagemTerminal msg;
    
    @Override
    public void run() {
        
        msg = new MensagemTerminal();
        
        while (true) {
            
            try{
                s = new Socket(Terminal.IPCentral, 2800);
                ObjectOutputStream pedido = new ObjectOutputStream(s.getOutputStream());
                pedido.writeObject(msg);
                
                ObjectInputStream resposta = new ObjectInputStream(s.getInputStream());
                InformaDepartamentos departamentos = (InformaDepartamentos) resposta.readObject();
                
                Terminal.atualizaDeptos(departamentos.getDepartamentos());
                Thread.sleep(60000);
                
            }catch (ConnectException e){
                Terminal.erroConectarServidor();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GerenciaDeptos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (Exception e){
                
            }
            
        }
    }
    
    
}
