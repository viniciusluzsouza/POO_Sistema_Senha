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
public class InformaDepartamentos implements Serializable {
    
    private ArrayList<String> departamentos;

    public InformaDepartamentos(ArrayList<String> departamentos) {
        this.departamentos = departamentos;
    }
    
    public ArrayList<String> getDepartamentos(){
        return departamentos;
    }
    
    
}
