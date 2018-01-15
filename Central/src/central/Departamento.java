/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package central;

/**
 *
 * @author vini
 */
public class Departamento {
    
    private int contador_comum;
    private int contador_preferencial;
    private int cont_pref;
    private final String nome;
    private final String sigla;
    
    public Departamento(String nome, String sigla){
        this.nome = nome;
        this.sigla = sigla;
        contador_comum = 0;
        contador_preferencial = 0;
        cont_pref = 0;
    }
    
    public Departamento(){
        nome = null;
        sigla = null;
        contador_comum = 0;
        contador_preferencial = 0;        
    }
    
    public int getContadorPreferencial(){
        return contador_preferencial;
    }
    
    public int getContadorComum(){
        return contador_comum;
    }
    
    public int getContPref(){
        return cont_pref;
    }
    
    public void addContPref(){
        cont_pref++;
    }
        
    public void clearContPref(){
        cont_pref = 0;
    }
    
    public void reset(){
        contador_comum = 0;
        contador_preferencial = 0;
        cont_pref = 0;
    }
    
    public void incrementaPreferencial(){
        if ( contador_preferencial < 1000) contador_preferencial++;
        else contador_preferencial = 0;
    }
    
    public void incrementaComum(){
        if ( contador_comum < 1000) contador_comum++;
        else contador_comum = 0;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getSigla(){
        return sigla;
    }
    
}
