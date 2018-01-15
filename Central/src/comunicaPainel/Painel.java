/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicaPainel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensagens.MensagemPainel;

/**
 *
 * @author schai
 */
public class Painel implements Runnable {
    Socket s;
    ObjectOutputStream outStream;
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(2900);
            while(true) {
                s = ss.accept();
                outStream = new ObjectOutputStream(s.getOutputStream());
            }
        } catch (IOException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enviaSenha(String senha, int guiche) {
        try {
            if (s == null) return; // se o socket não tiver sido instanciado
            if (s.isClosed() == true) return; // sai do método se o socket estiver fechado
            MensagemPainel mp = new MensagemPainel(senha, guiche);     
            
            outStream.writeObject(mp);
        } catch (IOException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
