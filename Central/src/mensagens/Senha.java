/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vini
 */
public class Senha implements Serializable {
    
    private String senha;
    private String data;
    
    public Senha(String senha){
        this.senha = senha;
        Date d = new Date();
        StringBuffer data = new StringBuffer();
        SimpleDateFormat sdfData = new SimpleDateFormat("dd.MM.yyyy");
        data.append(sdfData.format(d));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        this.data = data.toString() + ',' + sdf.format(d);
    }
    
    public String getSenha(){
        return senha;
    }
    
    public String getData(){
        return data;
}
    
}
