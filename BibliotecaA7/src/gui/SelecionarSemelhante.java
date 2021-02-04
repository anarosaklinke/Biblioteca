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
public class SelecionarSemelhante extends javax.swing.JInternalFrame {

    public int retorno;

    public SelecionarSemelhante(AlterarLivro aThis) {
        retorno = 0;
        initComponents();
        exibir();
    }

    public SelecionarSemelhante(CadastroLivroManualmente aThis) {
        retorno = 1;
        initComponents();
        exibir();
    }

    public SelecionarSemelhante(CadastrarPorAssistente aThis) {
        retorno = 2;
        initComponents();
        exibir();
    }

    SelecionarSemelhante(CadastrarPorAssistente_Google aThis) {
        retorno = 3;
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

    private void exibirPesquisa(String chave) {
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

    private void pesquisar() {

        String chave = pesquisa.getText().trim();

        if (chave.isEmpty()) {
            exibir();
        } else {
            exibirPesquisa(validacao.formatString(chave));
        }
    }

    private void selecionar() {
        if (tabelaLivros.getSelectedRow() != -1) {
            String isbnS = (String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 0);
            String tituloS = (String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 1);
            String autor = (String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 2);
            String dataS = (String) tabelaLivros.getModel().getValueAt(tabelaLivros.getSelectedRow(), 3);

            switch (retorno) {
                case 0:

                    AlterarLivro.mudaSemelhante(isbnS, tituloS, dataS, autor);
                    break;
                case 1:
                    CadastroLivroManualmente.mudaSemelhante(isbnS, tituloS, dataS, autor);
                    break;
                case 2:
                    CadastrarPorAssistente.mudaSemelhante(isbnS, tituloS, dataS, autor);
                    break;
                case 3:
                    CadastrarPorAssistente_Google.mudaSemelhante(isbnS, tituloS, dataS, autor);
                    break;
            }
            this.dispose();
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

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        label1 = new java.awt.Label();
        atualizar = new java.awt.Button();
        pesquisa = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelaLivros.setEnabled(false);
        tabelaLivros.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tabelaLivros);

        label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label1.setText("Lista de Livros:");

        atualizar.setLabel("Atualizar");
        atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarActionPerformed(evt);
            }
        });

        pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 0, 51));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jButton1)
                        .addGap(26, 26, 26)
                        .addComponent(jButton2)))
                .addContainerGap(327, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(76, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        selecionar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarActionPerformed
        // TODO add your handling code here:
        pesquisar();

    }//GEN-LAST:event_atualizarActionPerformed

    private void pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button atualizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JTextField pesquisa;
    private javax.swing.JTable tabelaLivros;
    // End of variables declaration//GEN-END:variables
}
