/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import model.Livro;
import model.Pessoa;
import service.ClassificacaoService;
import service.LivroService;
import service.LoginService;
import service.PessoaService;
import service.ServiceFactory;
import utils.validacao;

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
        ClassificacaoService entity4 = ServiceFactory.getClassificacaoService();

        List<Livro> livros = entity.recuperaLivro();

        Livro l;
        Pessoa p;
        String us;
        String[][] dados = new String[livros.size()][7];

        for (int i = 0; i < livros.size(); i++) {

            l = livros.get(i);

            p = entity2.recuperaPessoaId(l.getIdPessoa());

            us = entity3.consultaLoginUsuario(p.getIdPessoa());

            dados[i][0] = l.getIsbn();
            dados[i][1] = l.getTitulo();
            dados[i][2] = l.getAutores();
            dados[i][3] = l.getDataPublicacao();
            dados[i][4] = l.getSemelhantes();   
            dados[i][5] = entity4.verificaNome(l.getIdClassificacao());
            dados[i][6] = us;
        }
        tabelaLivros = new javax.swing.JTable();

        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{
                    "ISBNC", "Título", "Autores", "Data da Publicação", "Semelhantes", "Classificação", "Usuario"
                }
        ));

        jScrollPane2.setViewportView(tabelaLivros);

    }
    

    
    private void exibirPesquisa(String chave){
        LivroService entity = ServiceFactory.getLivroService();
        PessoaService entity2 = ServiceFactory.getPessoaService();
        LoginService entity3 = ServiceFactory.getLoginService();
        ClassificacaoService entity4 = ServiceFactory.getClassificacaoService();
        
        List<Livro> livros = entity.recuperaLivro(chave);

        Livro l;
        Pessoa p;
        String us;
        String[][] dados = new String[livros.size()][7];

        for (int i = 0; i < livros.size(); i++) {

            l = livros.get(i);

            p = entity2.recuperaPessoaId(l.getIdPessoa());

            us = entity3.consultaLoginUsuario(p.getIdPessoa());

            
            dados[i][0] = l.getIsbn();
            dados[i][1] = l.getTitulo();
            dados[i][2] = l.getAutores();
            dados[i][3] = l.getDataPublicacao();
            dados[i][4] = l.getSemelhantes();   
            dados[i][5] = entity4.verificaNome(l.getIdClassificacao());
            dados[i][6] = us;
        }
        tabelaLivros = new javax.swing.JTable();

        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{
                    "ISBNC", "Título", "Autores", "Data da Publicação", "Semelhantes", "Classificação", "Usuario"
                }
        ));

        jScrollPane2.setViewportView(tabelaLivros);
    }
    
    private void pesquisar(){
        String chave = pesquisa.getText().trim();
        
        if (chave.isEmpty()){
            exibir();
        }else{
            exibirPesquisa(validacao.formatString(chave));
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
            alterar.semelhante.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 4));
            alterar.classifica.setSelectedItem((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 5));
            
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
            excluir.classifica.setSelectedItem((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 5));

            getParent().add(excluir);
            excluir.setVisible(true);
        }
    }
    

    
    private void cadastrarClass(){
        CadastrarClassificacao cad = new CadastrarClassificacao();
        getParent().add(cad);
        cad.setVisible(true);
    }
    
    private void cadastrarSemelhante(){
            if (tabelaLivros.getSelectedRow() != -1) {
            CadastroLivroManualmente cad = new CadastroLivroManualmente();
            cad.semelhante.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 0));
            cad.titulo.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 1));
            cad.autores.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 2));
            cad.dataPub.setText((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 3));
            cad.classifica.setSelectedItem((String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 5));
            
            getParent().add(cad);
            cad.setVisible(true);
        }
    }
    
    private void cadGoogle(){
        CadastrarPorAssistente_Google cad = new CadastrarPorAssistente_Google();
        getParent().add(cad);
        cad.setVisible(true);
    }
    
    private void cadAssistente(){
        CadastrarPorAssistente cad = new CadastrarPorAssistente();
        getParent().add(cad);
        cad.setVisible(true);
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
        alterarLivro1 = new javax.swing.JButton();
        alterarLivro2 = new javax.swing.JButton();
        CadAssistente1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista de Livros");

        Excluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Excluir.setForeground(new java.awt.Color(204, 0, 51));
        Excluir.setText("Excluir Livro");
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
        CadAssistente.setText("Assistente Json");
        CadAssistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadAssistenteActionPerformed(evt);
            }
        });

        CadastrarManual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadastrarManual.setForeground(new java.awt.Color(0, 0, 204));
        CadastrarManual.setText("Cadastrar Livro");
        CadastrarManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarManualActionPerformed(evt);
            }
        });

        alterarLivro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarLivro.setForeground(new java.awt.Color(0, 0, 204));
        alterarLivro.setText("Atualizar Livro");
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

        pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaActionPerformed(evt);
            }
        });

        alterarLivro1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarLivro1.setForeground(new java.awt.Color(0, 0, 204));
        alterarLivro1.setText("Cadastrar Semelhante");
        alterarLivro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarLivro1ActionPerformed(evt);
            }
        });

        alterarLivro2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarLivro2.setForeground(new java.awt.Color(0, 0, 204));
        alterarLivro2.setText("Cadastrar Classificação");
        alterarLivro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarLivro2ActionPerformed(evt);
            }
        });

        CadAssistente1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadAssistente1.setForeground(new java.awt.Color(0, 0, 204));
        CadAssistente1.setText("Assistente Google");
        CadAssistente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadAssistente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CadastrarManual, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(CadAssistente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CadastrarManual1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(CadAssistente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alterarLivro1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alterarLivro2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(alterarLivro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
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
                    .addComponent(alterarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alterarLivro1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadAssistente1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadastrarManual, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadastrarManual1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alterarLivro2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed

        excluir();

    }//GEN-LAST:event_ExcluirActionPerformed

    private void CadAssistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadAssistenteActionPerformed
        // TODO add your handling code here:

        cadAssistente();
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

    private void alterarLivro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarLivro1ActionPerformed
        // TODO add your handling code here:
        cadastrarSemelhante();
    }//GEN-LAST:event_alterarLivro1ActionPerformed

    private void pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisaActionPerformed

    private void alterarLivro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarLivro2ActionPerformed
        // TODO add your handling code here:
        
        cadastrarClass();
    }//GEN-LAST:event_alterarLivro2ActionPerformed

    private void CadAssistente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadAssistente1ActionPerformed
        // TODO add your handling code here:
        cadGoogle();
        
    }//GEN-LAST:event_CadAssistente1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CadAssistente;
    private javax.swing.JButton CadAssistente1;
    private javax.swing.JButton CadastrarManual;
    private javax.swing.JButton CadastrarManual1;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton alterarLivro;
    private javax.swing.JButton alterarLivro1;
    private javax.swing.JButton alterarLivro2;
    private java.awt.Button atualizar;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JTextField pesquisa;
    private javax.swing.JTable tabelaLivros;
    // End of variables declaration//GEN-END:variables

}
