/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicaTerminal;

import central.GerenciaSenha;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import mensagens.InformaDepartamentos;
import mensagens.MensagemTerminal;
import mensagens.Senha;

/**
 *
 * @author vini
 */
public class Terminal implements Runnable {
    
    private static Socket s;
    private static ServerSocket ss;
    
    public void enviaSenha(String dpto, boolean pref){
        try {
            String senha = GerenciaSenha.pedeSenha(dpto, pref);
            System.out.println("senha: " + senha);
            Senha umaSenha = new Senha(senha);
            ObjectOutputStream resposta = new ObjectOutputStream(s.getOutputStream());
            resposta.writeObject(umaSenha);
            
        } catch (Exception e){
            
        }
    }
    
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
   
    @Override
    public void run() {
        try { 
            ss = new ServerSocket(2800);
        
            System.out.println("Aguardando conexões, (Porta 2800) ...");
        
            while (true){
            
                s = ss.accept();
                            
                ObjectInputStream pedido = new ObjectInputStream(s.getInputStream());
                MensagemTerminal msg = (MensagemTerminal) pedido.readObject();
            
                if ( msg.getOperacao() == 0 ) enviaSenha(msg.getDepartamento(), msg.getPreferencial());
                else enviaDeptos();
                     
            }
        } catch (Exception e){
            System.out.println("exceção: " + e.getMessage());
            
        }
        
    }
    
}
