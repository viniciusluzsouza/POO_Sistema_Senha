/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package central;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import mensagens.Senha;

/**
 *
 * @author vini
 */
public class GerenciaSenha {
    
    public static final HashMap<String, Departamento> dptos = new HashMap<String, Departamento>();
    public static final HashMap<String, ArrayList<Senha>> filas_comuns = new HashMap<String, ArrayList<Senha>>();
    public static final HashMap<String, ArrayList<Senha>> filas_preferenciais = new HashMap<String, ArrayList<Senha>>();
    
    public GerenciaSenha(){
    }
    
    //Cadastra departamento - Entrar com nome e sigla
    public static int cadastraDpto(String nome, String sigla) {
            
        Set<String> chaves = dptos.keySet(); 
        Iterator it = chaves.iterator();
        while (it.hasNext()){
            String chave = (String) it.next();
            if (chave.equals(nome)) return 1;                    //nome do departamento já existe
            Departamento dpt = dptos.get(chave);
            if (dpt.getSigla().equals(sigla)) return 2;          //sigla do departamento já existe
        }
        
        Departamento dpto = new Departamento(nome, sigla);  //cria departamento
        dptos.put(nome, dpto);                              //armazena na hash de departamentos
        
        ArrayList<Senha> q1 = new ArrayList<Senha>();   //cria fila para o departamento
        ArrayList<Senha> q2 = new ArrayList<Senha>();
        filas_comuns.put(sigla, q1);                        //guarda fila comum
        filas_preferenciais.put(sigla, q2);                 //guarda fila preferencial
        
        return 0;                                           //departamento criado com sucesso
    }
    
    //este metodo arquiva os dados para /tmp/relatorio.txt
    public static void arquiva(String departamento, Senha senha, boolean pref, int guiche){
        try {
            File dir = new File("/tmp/relatorio.txt");
            System.out.println("Log salvo em: "+dir.getAbsolutePath());
            if ( dir.exists() ){
                FileWriter file = new FileWriter("/tmp/relatorio.txt", true);
                BufferedWriter bw = new BufferedWriter(file);
                String gravar = null;
                Date d = new Date();
                StringBuffer data = new StringBuffer();
                SimpleDateFormat sdfData = new SimpleDateFormat("dd.MM.yyyy");
                data.append(sdfData.format(d));
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");   
                if ( pref ) {
                    gravar = senha.getData() + ',' + data.toString() + ',' + sdf.format(d) + ',' + departamento + ',' + guiche + ',' + senha.getSenha() + ',' + "Sim";
                } else {
                    gravar = senha.getData() + ',' + data.toString() + ',' + sdf.format(d) + ',' + departamento + ',' + guiche + ',' + senha.getSenha() + ',' + "Nao";
                }
                bw.write(gravar);
                bw.newLine();
                bw.flush();     
                file.close();
            } else {
                FileWriter file = new FileWriter("/tmp/relatorio.txt", false);
                BufferedWriter bw = new BufferedWriter(file);
                String gravar = null;
                Date d = new Date();
                StringBuffer data = new StringBuffer();
                SimpleDateFormat sdfData = new SimpleDateFormat("dd.MM.yyyy");
                data.append(sdfData.format(d));
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");               
                if ( pref ) {
                    gravar = senha.getData() + ',' + data.toString() + ',' + sdf.format(d) + ',' + departamento + ',' + guiche + ',' + senha.getSenha() + ',' + "Sim";
                } else {
                    gravar = senha.getData() + ',' + data.toString() + ',' + sdf.format(d) + ',' + departamento + ',' + guiche + ',' + senha.getSenha() + ',' + "Nao";
                }
                bw.write(gravar);
                bw.newLine();
                bw.flush();  
                file.close();
            }
            
        } catch (Exception e){
            
        }
    }
    
    //Pede proximo - Entrar com nome do departamento (**NAO ENTRAR COM SIGLA)
    public static String proximo(String departamento, int guiche){
        
        Departamento umDepto = dptos.get(departamento); //procura se o departamento existe
        String dpto = umDepto.getSigla();               //pega a sigla do departamento
        
        //este if verifica se a fila esta vazia, se estiver retorna string "erro"
        //verifica também se ja foram chamadas mais de 3 prefernciais, se for, chama uma comum
        if ( filas_preferenciais.get(dpto).isEmpty() ){
            if ( filas_comuns.get(dpto).isEmpty() ) return "erro";
            dptos.get(departamento).clearContPref();
            Senha senha = filas_comuns.get(dpto).remove(0);
            String proximo = senha.getSenha();
            //String senha = 'c' + dpto + filas_comuns.get(dpto).remove(0);
            arquiva(departamento, senha, false, guiche);
            return proximo;
        } else if ( umDepto.getContPref() == 3 ) {
            if ( filas_comuns.get(dpto).isEmpty() ) return "erro";
            dptos.get(departamento).clearContPref();
            Senha senha = filas_comuns.get(dpto).remove(0);
            String proximo = senha.getSenha();
            //String senha = 'c' + dpto + filas_comuns.get(dpto).remove(0);
            arquiva(departamento, senha, false, guiche);
            return proximo;
        } else {
            if ( filas_preferenciais.get(dpto).isEmpty() ) return "erro";
        dptos.get(departamento).addContPref();
            Senha senha = filas_preferenciais.get(dpto).remove(0);
            String proximo = senha.getSenha();
            //String senha = 'p' + dpto + filas_preferenciais.get(dpto).remove(0);
            arquiva(departamento, senha, true, guiche);
            return proximo;
        }
        
    }
       
    
    //este metodo gera senha para o terminal imprimir
    public static String pedeSenha(String departamento, boolean pref){
        
        Departamento umDepto = dptos.get(departamento);
        String dpto = umDepto.getSigla();
        
        if ( pref ) {
            dptos.get(departamento).incrementaPreferencial();
            String umaSenha = 'p' + dpto + dptos.get(departamento).getContadorPreferencial();
            Senha senha = new Senha(umaSenha);
            //int senha = dptos.get(departamento).getContadorPreferencial();
            filas_preferenciais.get(dpto).add(senha);
            return umaSenha;
        } else {
            dptos.get(departamento).incrementaComum();
            String umaSenha = 'c' + dpto + dptos.get(departamento).getContadorComum();
            Senha senha = new Senha(umaSenha);
            //int senha = dptos.get(departamento).getContadorComum();
            filas_comuns.get(dpto).add(senha);
            return umaSenha;
        }        
    }
    
    //retorna lista com o nome dos departamentos
    public static ArrayList<String> getDepartamentos(){
        ArrayList<String> lista = new ArrayList<String>();
        Set<String> chaves = dptos.keySet(); 
        Iterator it = chaves.iterator();
        while (it.hasNext()){
            String chave = (String) it.next();
            lista.add(chave);
        }
        return lista;
    }
    
    //exclui departamento por nome
    public static void excluiDpto(String nome){
        if ( dptos.containsKey(nome) ){
            Departamento dpto = dptos.remove(nome);
            String sigla = dpto.getSigla();
            filas_comuns.remove(sigla);
            filas_preferenciais.remove(sigla);
        }
    }
    
    //exclui departamento por sigla
    public static void excluiDptoSigla(String sigla){
        Set<String> chaves = dptos.keySet();
        Iterator it = chaves.iterator();
        while (it.hasNext()){
            Departamento dpto = dptos.get(it.next());
            if ( dpto.getSigla() == sigla ) dptos.remove(dpto.getNome());
        }
        filas_comuns.remove(sigla);
        filas_preferenciais.remove(sigla);
    }
    
    public static void reset(){
        try {
            Set<String> chaves = dptos.keySet(); 
            Iterator it = chaves.iterator();
            ArrayList<String> listaDpto = new ArrayList<>();
            while (it.hasNext()){
                String nomeDpto = (String) it.next();
                listaDpto.add(nomeDpto);

            }
            
            while (!listaDpto.isEmpty()){
                excluiDpto(listaDpto.remove(0));
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
