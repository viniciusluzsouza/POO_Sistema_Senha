/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import painel.Interface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensagens.MensagemPainel;

/**
 *
 * @author schai
 */
public class PainelWorker implements Runnable {
    Socket s;
    Interface interfaceP;
    
    public PainelWorker(Interface interfaceP) {
        this.interfaceP = interfaceP;    
    }

    @Override
    public void run() {
        try {
            s = new Socket(Interface.IPCentral, 2900);
            ObjectInputStream resposta = new ObjectInputStream(s.getInputStream());
            while (true) {
                try {
                    MensagemPainel mp = (MensagemPainel) resposta.readObject();
                    interfaceP.senhas.add(0, mp.getSenha());
                    interfaceP.guiches.add(0, ""+mp.getGuiche());
                    interfaceP.MostrarPainel();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PainelWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
                           
            }
        } catch (ConnectException e){
            interfaceP.erroConectarServidor();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PainelWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(PainelWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
