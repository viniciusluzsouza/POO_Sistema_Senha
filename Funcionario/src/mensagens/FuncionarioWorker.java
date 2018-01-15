/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import Funcionario.Interface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marina
 */
public class FuncionarioWorker implements Runnable {

    private Socket s;
    private Interface interfaceF;
    private String departamento;
    private int guiche;

    public FuncionarioWorker(Interface interfaceF, String departamento, int guiche) {
        this.interfaceF = interfaceF;
        this.departamento = departamento;
        this.guiche = guiche;
    }

    @Override
    public void run() {
        try {
            s = new Socket(Interface.IPCentral, 3000);
            MensagemFuncionario msg = new MensagemFuncionario("", guiche, departamento);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            
            out.writeObject(msg);
            ObjectInputStream resposta = new ObjectInputStream(s.getInputStream());
            msg = (MensagemFuncionario) resposta.readObject();
            
            interfaceF.recebeSenha(msg.getproxsenha());
        } catch (ConnectException e){
            interfaceF.erroConectarServidor();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GerenciaDeptos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(FuncionarioWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
