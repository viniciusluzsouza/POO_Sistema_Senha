/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author vini
 */
public class MensagemTerminal implements Serializable {
    
    private String departamento;
    private boolean preferencial;
    private int operacao;
    
    //cria mensagem do tipo 0 - pedido de senha
    public MensagemTerminal(String departamento, boolean preferencial){
        this.departamento = departamento;
        this.preferencial = preferencial;
        this.operacao = 0;
    }
    
    //cria mensagem do tipo 1 - pedido de departamentos
    public MensagemTerminal(){
        this.operacao = 1;        
    }
    
    public int getOperacao(){
        return operacao;
    }
    
    public String getDepartamento(){
        return departamento;
    }
    
    public boolean getPreferencial(){
        return preferencial;
    }
    
}
