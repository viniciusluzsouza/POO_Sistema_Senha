/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package central;

import ComunicaTerminal.Terminal;
import comunicaFuncionario.ComunicaFuncionario;
import comunicaPainel.Painel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author schai
 */
public class Central implements Runnable {
    Terminal terminal;
    public Painel painel;
    ComunicaFuncionario comunicaFuncionario;
    
    //retorna 0 se o dpto for criado, 1 se o nome já existe e 2 se a sigla já existe
    public int cadastroDpto(String dpto, String sigla) {
        return GerenciaSenha.cadastraDpto(dpto, sigla);        
    }

    public Central() {
        
        terminal = new Terminal();
        Thread term = new Thread(terminal);
        term.start();
        
        painel = new Painel();
        Thread pnl = new Thread(painel);
        pnl.start();
        
        comunicaFuncionario = new ComunicaFuncionario(this);
        Thread fn = new Thread(comunicaFuncionario);
        fn.start();
    }

    public void limpaArquivoLog(){
        FileWriter file;
        try {
            file = new FileWriter("/tmp/relatorio.txt", false);
            BufferedWriter bw = new BufferedWriter(file);
            bw.flush();
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Central.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
        
    
    }
    
    public void gerarRelatorio(){
        HashMap<String, ArrayList<Integer>> relDpto = new HashMap<>();
        
        
        try {
            FileReader arquivo = new FileReader("/tmp/relatorio.txt");
            BufferedReader buffer = new BufferedReader(arquivo);
            String line = buffer.readLine();
            while (line != null) {
                //System.out.println(line);
                 // 0           1          2        3       4    5   6   7
                //09.12.2016,00:23:52,09.12.2016,00:23:55,sdfsdf,1,pdf1,Sim
                if (line == null) continue;
                
                String[] valores = line.split(",");
                
                if (!relDpto.containsKey(valores[4])){
                    ArrayList<Integer> array = new ArrayList<>();
                    array.add(0, 99999999); // valor mínimo tempo segundos comum
                    array.add(1, 0); // valor máximo tempo segundos comum
                    array.add(2, 0); // somatório tempo segundos comum
                    array.add(3, 0); // número senhas comum
                    array.add(4, 0); // valor mínimo tempo segundos pref
                    array.add(5, 0); // valor máximo tempo segundos pref
                    array.add(6, 0); // somatório tempo segundos pref
                    array.add(7, 0); // número senhas pref 
                    relDpto.put(valores[4], array);
                }
                 
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                Date dataInicio = (Date)formatter.parse(valores[1]);
                
                Date dataFim = (Date)formatter.parse(valores[3]);
                int tempoEsperaSegundos = (int) (dataFim.getTime() - dataInicio.getTime())/1000;
                
                ArrayList<Integer> arrayTmp = relDpto.get(valores[4]);
                
                // se for preferencial
                if (valores[7].equals("Sim")) {
                    if (arrayTmp.get(4)>tempoEsperaSegundos) arrayTmp.set(4, tempoEsperaSegundos); // verifica se é tempo minimo
                    if (arrayTmp.get(5)<tempoEsperaSegundos) arrayTmp.set(5, tempoEsperaSegundos); // verifica se é tempo maximo
                    arrayTmp.set(6, arrayTmp.get(6)+tempoEsperaSegundos); // incrementa o somatorio de tempo
                    arrayTmp.set(7, arrayTmp.get(7)+1); // incrementa o somatorio de senhas
                } else {
                    if (arrayTmp.get(0)>tempoEsperaSegundos) arrayTmp.set(0, tempoEsperaSegundos); // verifica se é tempo minimo
                    if (arrayTmp.get(1)<tempoEsperaSegundos) arrayTmp.set(1, tempoEsperaSegundos); // verifica se é tempo maximo
                    arrayTmp.set(2, arrayTmp.get(2)+tempoEsperaSegundos); // incrementa o somatorio de tempo
                    arrayTmp.set(3, arrayTmp.get(3)+1); // incrementa o somatorio de senhas
                }
                
                
                //JOptionPane.showMessageDialog(null, "Hora 1: " + valores[1] +"\nHora 2: " + valores[3], "Central", JOptionPane.ERROR_MESSAGE);
                line = buffer.readLine();
            }
            buffer.close();
            arquivo.close();
            
            
            // salva do arquivo de texto
            FileWriter file = new FileWriter("/tmp/relatorio_geral.txt", false);
            BufferedWriter bw = new BufferedWriter(file);
            
            bw.write("Relatório de Atendimentos");
            bw.newLine(); bw.newLine(); bw.newLine();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm'm'ss's'");
            
            Set<String> chaves = relDpto.keySet(); 
            Iterator it = chaves.iterator();
            while (it.hasNext()){
                String nomeDpto = (String) it.next();
                ArrayList<Integer> valoresDpto = relDpto.get(nomeDpto);
                
                bw.write("Departamento: "+ nomeDpto);
                bw.newLine(); bw.newLine();
                
                Date d = new Date();
                
                bw.write("Senhas comuns");
                bw.newLine(); bw.newLine();

                d.setTime(valoresDpto.get(0)*1000);
                bw.write("Tempo mínimo de espera: "+ dateFormat.format(d)); bw.newLine();
                d.setTime(valoresDpto.get(1)*1000);
                bw.write("Tempo máximo de espera: " + dateFormat.format(d)); bw.newLine();
                
                if (valoresDpto.get(3)==0) {
                    d.setTime(0);    
                } else {
                    d.setTime((valoresDpto.get(2)/valoresDpto.get(3))*1000);
                }
                bw.write("Tempo médio de espera: " + dateFormat.format(d)); bw.newLine();
                bw.write("Total de atendimentos: " + valoresDpto.get(3)); bw.newLine();
                bw.newLine();
                
                bw.write("Senhas preferenciais");
                bw.newLine(); bw.newLine();

                d.setTime(valoresDpto.get(4)*1000);
                bw.write("Tempo mínimo de espera: "+ dateFormat.format(d)); bw.newLine();
                d.setTime(valoresDpto.get(5)*1000);
                bw.write("Tempo máximo de espera: " + dateFormat.format(d)); bw.newLine();
                
                if (valoresDpto.get(7)==0) {
                    d.setTime(0);    
                } else {
                    d.setTime((valoresDpto.get(6)/valoresDpto.get(7))*1000);
                }
                bw.write("Tempo médio de espera: " + dateFormat.format(d)); bw.newLine();
                bw.write("Total de atendimentos: " + valoresDpto.get(7)); bw.newLine();
                bw.newLine();
                
                bw.write("Total de atendimentos do departamento: " + (valoresDpto.get(7)+valoresDpto.get(3))); bw.newLine();
                
                bw.newLine();
                bw.newLine();
            }
            

            bw.flush(); 
            file.close();
            File dir = new File("/tmp/relatorio_geral.txt");
            JOptionPane.showMessageDialog(null, "Relatório salvo em: "+dir.getAbsolutePath(), "Central", JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch (FileNotFoundException f) {
            System.out.println("Arquivo Não Encontrado.");
        } catch (IOException i) {
            System.out.println("Erro de Entrada e Saída.");
        } catch (ParseException ex) {
            Logger.getLogger(Central.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
