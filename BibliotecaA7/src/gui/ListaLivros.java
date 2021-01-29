/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.Normalizer;
import java.util.List;
import model.Livro;
import model.Pessoa;
import service.LivroService;
import service.LoginService;
import service.PessoaService;
import service.ServiceFactory;

/**
 *
 * @author PICHAU
 */
public class ListaLivros extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaClientes
     */
    public ListaLivros() {
        initComponents();

        exibir();
    }
    
    

    public final void exibir() {
        LivroService entity = ServiceFactory.getLivroService();
        PessoaService entity2 = ServiceFactory.getPessoaService();
        LoginService entity3 = ServiceFactory.getLoginService();

        List<Livro> livros = entity.recuperaLivro();

        Livro l;
        Pessoa p;
        String us;
        String[][] dados = new String[livros.size()][6];

        for (int i = 0; i < livros.size(); i++) {

            l = livros.get(i);

            p = entity2.recuperaPessoaId(l.getIdPessoa());

            us = entity3.consultaLoginUsuario(p.getIdPessoa());

            dados[i][2] = l.getAutores();
            dados[i][0] = l.getIsbn();
            dados[i][4] = l.getSemelhantes();
            dados[i][3] = l.getDataPublicacao();
            dados[i][1] = l.getTitulo();
            dados[i][5] = us;
        }
        tabelaLivros = new javax.swing.JTable();

        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{
                    "ISBNC", "Título", "Autores", "Data da Publicação", "Semelhantes", "Usuario"
                }
        ));

        jScrollPane2.setViewportView(tabelaLivros);

    }
    
    private static String formatString(String s) {
        String temp = Normalizer
                .normalize(s, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        return s;
    }
    
    private void exibirPesquisa(String chave){
        LivroService entity = ServiceFactory.getLivroService();
        PessoaService entity2 = ServiceFactory.getPessoaService();
        LoginService entity3 = ServiceFactory.getLoginService();

        List<Livro> livros = entity.recuperaLivro(chave);

        Livro l;
        Pessoa p;
        String us;
        String[][] dados = new String[livros.size()][6];

        for (int i = 0; i < livros.size(); i++) {

            l = livros.get(i);

            p = entity2.recuperaPessoaId(l.getIdPessoa());

            us = entity3.consultaLoginUsuario(p.getIdPessoa());

            dados[i][2] = l.getAutores();
            dados[i][0] = l.getIsbn();
            dados[i][4] = l.getSemelhantes();
            dados[i][3] = l.getDataPublicacao();
            dados[i][1] = l.getTitulo();
            dados[i][5] = us;
        }
        tabelaLivros = new javax.swing.JTable();

        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{
                    "ISBNC", "Título", "Autores", "Data da Publicação", "Semelhantes", "Usuario"
                }
        ));

        jScrollPane2.setViewportView(tabelaLivros);
    }
    
    private void pesquisar(){
        String chave = pesquisa.getText().trim();
        
        if (chave.isEmpty()){
            exibir();
        }else{
            exibirPesquisa(formatString(chave));
        }
    }

    private void cadastrar() {
        CadastroLivroManualmente cadManual = new CadastroLivroManualmente();
        getParent().add(cadManual);
        cadManual.setVisible(true);
    }

    private void alterar() {
        if (tabelaLivros.getSelectedRow() != -1) {
            AlterarLivro alterar = new AlterarLivro();
            alterar.isbn.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 0));
            alterar.titulo.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 1));
            alterar.autores.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 2));
            alterar.dataPub.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 3));
            alterar.semelhantes.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 4));
            
            getParent().add(alterar);
            alterar.setVisible(true);
        }
    }

    private void enviar() {
        EnviarArquivoLivro enviar = new EnviarArquivoLivro();
        getParent().add(enviar);
        enviar.setVisible(true);
    }

    private void excluir() {
        if (tabelaLivros.getSelectedRow() != -1) {
            ExcluirLivro excluir = new ExcluirLivro();

            excluir.isbn.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 0));
            excluir.titulo.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 1));
            excluir.autores.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 2));
            excluir.dataPub.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 3));
            excluir.semelhantes.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 4));

            getParent().add(excluir);
            excluir.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Excluir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        CadAssistente = new javax.swing.JButton();
        CadastrarManual = new javax.swing.JButton();
        alterarLivro = new javax.swing.JButton();
        label1 = new java.awt.Label();
        atualizar = new java.awt.Button();
        CadastrarManual1 = new javax.swing.JButton();
        pesquisa = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista de Livros");

        Excluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Excluir.setForeground(new java.awt.Color(204, 0, 51));
        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        tabelaLivros.setEnabled(false);
        tabelaLivros.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tabelaLivros);

        CadAssistente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadAssistente.setForeground(new java.awt.Color(0, 0, 204));
        CadAssistente.setText("Cadastrar por Assistente");
        CadAssistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadAssistenteActionPerformed(evt);
            }
        });

        CadastrarManual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadastrarManual.setForeground(new java.awt.Color(0, 0, 204));
        CadastrarManual.setText("Cadastrar");
        CadastrarManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarManualActionPerformed(evt);
            }
        });

        alterarLivro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarLivro.setForeground(new java.awt.Color(0, 0, 204));
        alterarLivro.setText("Atualizar ");
        alterarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarLivroActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label1.setText("Lista de Livros:");

        atualizar.setLabel("Atualizar");
        atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarActionPerformed(evt);
            }
        });

        CadastrarManual1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadastrarManual1.setForeground(new java.awt.Color(0, 0, 204));
        CadastrarManual1.setText("Enviar Arquivo");
        CadastrarManual1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarManual1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CadAssistente, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alterarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CadastrarManual, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CadastrarManual1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pesquisa, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(atualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CadAssistente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alterarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadastrarManual, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadastrarManual1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed

        excluir();

    }//GEN-LAST:event_ExcluirActionPerformed

    private void CadAssistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadAssistenteActionPerformed
        // TODO add your handling code here:

        CadastrarPorAssistente cad = new CadastrarPorAssistente();
        getParent().add(cad);
        cad.setVisible(true);
    }//GEN-LAST:event_CadAssistenteActionPerformed

    private void CadastrarManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarManualActionPerformed
        // TODO add your handling code here:

        cadastrar();
    }//GEN-LAST:event_CadastrarManualActionPerformed

    private void alterarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarLivroActionPerformed
        // TODO add your handling code here:
        alterar();


    }//GEN-LAST:event_alterarLivroActionPerformed

    private void atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarActionPerformed
        // TODO add your handling code here:
        pesquisar();
        

    }//GEN-LAST:event_atualizarActionPerformed

    private void CadastrarManual1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarManual1ActionPerformed
        // TODO add your handling code here:

        enviar();
    }//GEN-LAST:event_CadastrarManual1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CadAssistente;
    private javax.swing.JButton CadastrarManual;
    private javax.swing.JButton CadastrarManual1;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton alterarLivro;
    private java.awt.Button atualizar;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JTextField pesquisa;
    private javax.swing.JTable tabelaLivros;
    // End of variables declaration//GEN-END:variables

}
