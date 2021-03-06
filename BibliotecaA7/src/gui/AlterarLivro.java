/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.JOptionPane;
import model.Classificacao;
import model.Livro;
import service.ClassificacaoService;
import service.LivroService;
import service.ServiceFactory;
import utils.validacao;

public class AlterarLivro extends javax.swing.JInternalFrame {

    public AlterarLivro() {
        ClassificacaoService entity = ServiceFactory.getClassificacaoService();
        List<Classificacao> nomes = entity.recuperaClassificacao();
        if (nomes.size() > 0) {
            initComponents();
        } else {
            JOptionPane.showMessageDialog(null, "Cadastre uma Categoria Primeiro");
            this.dispose();
        }
    }

    public final void categorias() {
        ClassificacaoService entity = ServiceFactory.getClassificacaoService();

        List<Classificacao> nomes = entity.recuperaClassificacao();

        Classificacao nome;

        String[] dados = new String[nomes.size()];

        for (int i = 0; i < nomes.size(); i++) {

            nome = nomes.get(i);

            dados[i] = nome.getNome();
        }

        classifica = new javax.swing.JComboBox<>();
        classifica.setModel(new javax.swing.DefaultComboBoxModel<>(dados));

    }

    private void updateLivro() {
        LivroService entity = ServiceFactory.getLivroService();

        Long idIsbn = entity.recuperaIsbn(isbn.getText());

        Livro livro;

        String autoresTemp = autores.getText().trim();
        String isbnTemp = isbn.getText().trim();
        String semelhanteTemp = semelhante.getText().trim();
        String dataTemp = dataPub.getText().trim();
        String tituloTemp = titulo.getText().trim();
        String classTemp = (classifica.getSelectedItem()).toString();

        livro = validacao.validaLivro(idIsbn, autoresTemp, isbnTemp,
                semelhanteTemp, dataTemp, tituloTemp, classTemp);

        if (livro != null) {
            long idPessoa = entity.verifica(Sistema.getUsuario());

            livro.setIdPessoa(idPessoa);

            if (entity.update(livro, idPessoa)) {
                JOptionPane.showMessageDialog(null, "Livro " + isbn.getText() + " ATUALIZADO");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "ERRO");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Dados INVALIDOS - Tente Novamente");
        }

    }

    public static void mudaSemelhante(String isbnS, String tituloS,
            String dataS, String autor) {
        semelhante.setText(isbnS);
        titulo.setText(tituloS);
        dataPub.setText(dataS);
        autores.setText(autor);
    }

    private void selecionarSemelhante() {
        SelecionarSemelhante selecionar = new SelecionarSemelhante(this);
        getParent().add(selecionar);
        selecionar.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label2 = new java.awt.Label();
        label1 = new java.awt.Label();
        isbn = new javax.swing.JLabel();
        nome = new java.awt.Label();
        label = new java.awt.Label();
        nome7 = new java.awt.Label();
        nome5 = new java.awt.Label();
        titulo = new java.awt.TextField();
        autores = new java.awt.TextField();
        dataPub = new javax.swing.JFormattedTextField();
        atualizarLivro = new java.awt.Button();
        semelhante = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        classifica = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        label2.setText("label2");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alterar Livro");

        label1.setText("ISBN");

        nome.setText("Título");

        label.setText("Autores");

        nome7.setText("Semelhantes");

        nome5.setText("Data da Publicação");

        dataPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataPubActionPerformed(evt);
            }
        });

        atualizarLivro.setActionCommand("AtualizarLivro");
        atualizarLivro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        atualizarLivro.setForeground(new java.awt.Color(0, 0, 255));
        atualizarLivro.setLabel("Atualizar");
        atualizarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarLivroActionPerformed(evt);
            }
        });

        semelhante.setEditable(false);
        semelhante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semelhanteActionPerformed(evt);
            }
        });

        jLabel1.setText("Classificação");

        categorias();

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(204, 0, 51));
        jButton2.setText("-");
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
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(isbn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dataPub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(autores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(classifica, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(atualizarLivro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(semelhante)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(3, 3, 3))))
                    .addComponent(jLabel1))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nome5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataPub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nome7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(semelhante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(classifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(atualizarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dataPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataPubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataPubActionPerformed

    private void atualizarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarLivroActionPerformed
        // TODO add your handling code here:
        updateLivro();

    }//GEN-LAST:event_atualizarLivroActionPerformed

    private void semelhanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semelhanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semelhanteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        selecionarSemelhante();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        semelhante.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button atualizarLivro;
    public static java.awt.TextField autores;
    public javax.swing.JComboBox<String> classifica;
    public static javax.swing.JFormattedTextField dataPub;
    public javax.swing.JLabel isbn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private java.awt.Label label;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label nome;
    private java.awt.Label nome5;
    private java.awt.Label nome7;
    public static javax.swing.JFormattedTextField semelhante;
    public static java.awt.TextField titulo;
    // End of variables declaration//GEN-END:variables
}
