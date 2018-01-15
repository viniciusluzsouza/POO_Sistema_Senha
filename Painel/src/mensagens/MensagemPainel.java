/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import java.io.Serializable;

/**
 *
 * @author schai
 */
public class MensagemPainel implements Serializable {
    private String senha;
    private int guiche;
    
    public MensagemPainel(String senha, int guiche) {
        this.senha = senha;
        this.guiche = guiche;
    }
    
    public String getSenha() {
        return senha;
    }
    public int getGuiche() {
        return guiche;
    }
    
}
