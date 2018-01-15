/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import java.io.Serializable;

/**
 *
 * @author marina
 */
public class MensagemFuncionario implements Serializable {

    private String proxsenha;
    private int guiche;
    private String departamento;
    private int operacao;

    public MensagemFuncionario(String senha, int guiche, String depto) {
        this.proxsenha = senha;
        this.guiche = guiche;
        this.departamento = depto;
        this.operacao = 0;
    }

    public MensagemFuncionario() {
        this.operacao = 1;
    }
    
    public String getproxsenha() {
        return proxsenha;
    }
    
    public int getOperacao() {
        return operacao;
    }

    public void setproxsenha(String senha) {
        proxsenha = senha;
    }

    public int getGuiche() {
        return guiche;
    }
 
    public String getDepartamento() {
        return departamento;
    }

}
