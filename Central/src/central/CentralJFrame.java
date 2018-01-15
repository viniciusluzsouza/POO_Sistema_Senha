/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package central;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author schai
 */
public class CentralJFrame extends javax.swing.JFrame {
    Central central;
    DefaultListModel listModel;

    public CentralJFrame() {
        central = new Central();
        Thread threadCentral = new Thread(central);
        threadCentral.start();
        initComponents();
        listModel = new DefaultListModel();
        listDpto.setModel(listModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        edNomeDpto = new javax.swing.JTextField();
        edSiglaDpto = new javax.swing.JTextField();
        btnInserirDpto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDpto = new javax.swing.JList<>();
        btnExcluirDpto = new javax.swing.JButton();
        btnImprimirRelatorio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Central de Atendimento");
        setResizable(false);

        edNomeDpto.setNextFocusableComponent(edSiglaDpto);

        edSiglaDpto.setNextFocusableComponent(btnInserirDpto);

        btnInserirDpto.setText("Inserir");
        btnInserirDpto.setNextFocusableComponent(listDpto);
        btnInserirDpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirDptoActionPerformed(evt);
            }
        });

        listDpto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listDpto.setNextFocusableComponent(btnExcluirDpto);
        jScrollPane1.setViewportView(listDpto);

        btnExcluirDpto.setText("Excluir");
        btnExcluirDpto.setNextFocusableComponent(btnImprimirRelatorio);
        btnExcluirDpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirDptoActionPerformed(evt);
            }
        });

        btnImprimirRelatorio.setText("Gerar Relatório");
        btnImprimirRelatorio.setNextFocusableComponent(jButton1);
        btnImprimirRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirRelatorioActionPerformed(evt);
            }
        });

        jLabel1.setText("Departamentos cadastrados:");

        jLabel3.setText("Nome do departamento:");

        jLabel4.setText("Sigla do departamento:");

        jButton1.setText("Resetar Sistema");
        jButton1.setNextFocusableComponent(edNomeDpto);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edNomeDpto)
                                    .addComponent(jLabel4)
                                    .addComponent(edSiglaDpto))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnInserirDpto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluirDpto)
                        .addGap(108, 108, 108))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnImprimirRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(edNomeDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edSiglaDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInserirDpto)
                            .addComponent(btnExcluirDpto))))
                .addGap(19, 19, 19)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimirRelatorio)
                    .addComponent(jButton1))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirDptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirDptoActionPerformed
        String nomeDpto = edNomeDpto.getText();
        String siglaDpto = edSiglaDpto.getText();
        
        if (nomeDpto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, insira o nome do departamento!", "Central", JOptionPane.ERROR_MESSAGE);
            edNomeDpto.requestFocus();
            return;
        }
        
        if (siglaDpto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, insira a sigla do departamento!", "Central", JOptionPane.ERROR_MESSAGE);
            edSiglaDpto.requestFocus();
            return;
        }
        
        int retornoDpto = central.cadastroDpto(nomeDpto, siglaDpto);
        switch (retornoDpto) {
            case 0:
                //listModel.addElement(nomeDpto);
                atualizaDptos();
                JOptionPane.showMessageDialog(null, "Departamento " + nomeDpto + " adicionado com sucesso.", "Central", JOptionPane.INFORMATION_MESSAGE);
                edNomeDpto.setText("");
                edSiglaDpto.setText("");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Departamento " + nomeDpto + " já existe. Escolha outro nome.", "Central", JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Sigla " + siglaDpto + " já existe. Escolha outra sigla.", "Central", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Erro ao adicionar departamento.", "Central", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }//GEN-LAST:event_btnInserirDptoActionPerformed

    private void btnExcluirDptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirDptoActionPerformed
        // TODO add your handling code here:
        if (listDpto.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o departamento que deseja excluir!", "Central", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String dptoSelecionado = (String) listModel.get(listDpto.getSelectedIndex());
        GerenciaSenha.excluiDpto(dptoSelecionado);
        //listModel.remove(listDpto.getSelectedIndex());
        atualizaDptos();
        JOptionPane.showMessageDialog(null, "O departamento "+ dptoSelecionado + " foi excluído com sucesso.", "Central", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnExcluirDptoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GerenciaSenha.reset();        // TODO add your handling code here:
        atualizaDptos();
        central.limpaArquivoLog();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnImprimirRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirRelatorioActionPerformed
        central.gerarRelatorio();
    }//GEN-LAST:event_btnImprimirRelatorioActionPerformed

    public void atualizaDptos(){
        ArrayList<String> departamentos = GerenciaSenha.getDepartamentos();
        listModel.clear();
        while ( ! departamentos.isEmpty() ) {
            String umDepto = departamentos.remove(0);
            listModel.addElement(umDepto);
        }
    }
    
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CentralJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CentralJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CentralJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CentralJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CentralJFrame().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluirDpto;
    private javax.swing.JButton btnImprimirRelatorio;
    private javax.swing.JButton btnInserirDpto;
    private javax.swing.JTextField edNomeDpto;
    private javax.swing.JTextField edSiglaDpto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> listDpto;
    // End of variables declaration//GEN-END:variables
}
