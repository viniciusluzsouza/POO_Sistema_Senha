/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import Funcionario.Interface;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensagens.InformaDepartamentos;

/**
 *
 * @author vini
 */
public class GerenciaDeptos implements Runnable {
    
    Socket s;
    MensagemFuncionario msg;
    Interface interfaceF;
    
    
    public GerenciaDeptos (Interface intraface){
        this.interfaceF = intraface;
    }
    
    @Override
    public void run() {
        
        msg = new MensagemFuncionario();
        
        while (true) {
            
            try{
                System.out.println("Enviando requisição");
                s = new Socket(Interface.IPCentral, 3000);
                ObjectOutputStream pedido = new ObjectOutputStream(s.getOutputStream());
                pedido.writeObject(msg);
                
                ObjectInputStream resposta = new ObjectInputStream(s.getInputStream());
                InformaDepartamentos departamentos = (InformaDepartamentos) resposta.readObject();
                
                System.out.println("Resposta" + departamentos);
                
                interfaceF.atualizaDeptos(departamentos.getDepartamentos());
                s.close();
                Thread.sleep(10000);
                
            } catch (ConnectException e){
                interfaceF.erroConectarServidor();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GerenciaDeptos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
    
}
