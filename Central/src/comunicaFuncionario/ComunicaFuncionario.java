/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicaFuncionario;

import central.Central;
import central.GerenciaSenha;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import mensagens.InformaDepartamentos;
import mensagens.MensagemFuncionario;
import mensagens.MensagemTerminal;

/**
 *
 * @author schai
 */
public class ComunicaFuncionario implements Runnable {
    
    private static Socket s;
    private static ServerSocket ss;
    private Central central;
    
    public void enviaDeptos(){
        try {
            ArrayList listaDptos = GerenciaSenha.getDepartamentos();
            InformaDepartamentos informa = new InformaDepartamentos(listaDptos);
            ObjectOutputStream resposta = new ObjectOutputStream(s.getOutputStream());
            resposta.writeObject(informa);
        } catch (Exception e){
            System.out.println("exceção");    
        }
    }
    
    public ComunicaFuncionario(Central central){
        this.central = central;
    }
    
    @Override
    public void run() {
        try { 
            ss = new ServerSocket(3000);
        
            System.out.println("Aguardando conexões, (Porta 3000) ...");
        
            while (true){
            
                s = ss.accept();
                            
                ObjectInputStream pedido = new ObjectInputStream(s.getInputStream());
                MensagemFuncionario msg = (MensagemFuncionario) pedido.readObject();
                
                if (msg.getOperacao()==0){
                    String proximaSenha = GerenciaSenha.proximo(msg.getDepartamento(), msg.getGuiche());
                    
                    msg.setproxsenha(proximaSenha);
                    if (!proximaSenha.equals("erro"))
                        central.painel.enviaSenha(proximaSenha,  msg.getGuiche());
                    
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(msg);
                } else {
                    enviaDeptos();
                }
                     
            }
        } catch (Exception e){
            System.out.println("exceção: " + e.getMessage());
            
        }
        
    }
    
}
