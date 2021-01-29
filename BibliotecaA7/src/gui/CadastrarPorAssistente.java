/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Livro;
import org.jsoup.Jsoup;
import service.LivroService;
import service.ServiceFactory;

/**
 *
 * @author PICHAU
 */
public class CadastrarPorAssistente extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastrarPorAssistente
     */
    public CadastrarPorAssistente() {
        initComponents();
    }

    public void consultar() {

    }

    private class Container {

        Details details;
    }

    private class autor {

        private String key;
        private String name;
    }

    private class Details {

        String title;
        String publish_date;
        private List<autor> authors;
    }

    private void JsonClass(String isbn) {

        String titulo = " ", autor = " ", dataPub = " ";

        org.jsoup.nodes.Document docKb = null;
        try {
            docKb = Jsoup
                    .connect("https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&jscmd=details&format=json")
                    .ignoreContentType(true).get();

        } catch (IOException ex) {
            Logger.getLogger(CadastrarPorAssistente.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (docKb != null) {
            String json;
            json = docKb.body().text();

            if (!json.isEmpty()) {

                java.lang.reflect.Type type
                        = new TypeToken<Map<String, Container>>() {
                        }.getType();

                Map<String, Container> fullJsonObject = new Gson().fromJson(json, type);

                for (Map.Entry<String, Container> entry : fullJsonObject.entrySet()) {
                    titulo = entry.getValue().details.title;
                    dataPub = entry.getValue().details.publish_date;

                    if (entry.getValue().details.authors != null) {
                        for (int i = 0; i < entry.getValue().details.authors.size(); i++) {
                            if (!(entry.getValue().details.authors.get(i).name).isEmpty()) {
                                autor = entry.getValue().details.authors.get(i).name + ", " + autor;
                            }
                        }
                    }
                }
                titulo1.setText(titulo);
                isbn1.setText(isbn);
                dataPub1.setText(dataPub);
                autores.setText(autor);

            } else {
                JOptionPane.showMessageDialog(null, "ISBN não Encontrado - Tente Novamente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ISBN não Encontrado - Tente Novamente");
        }
    }

    private static String formatString(String s) {
        String temp = Normalizer
                .normalize(s, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        return s;
    }

    private void cadastrarLivro(String titulo, String dataPub, String autor, String isbn,String semelhante ) {
        LivroService entity = ServiceFactory.getLivroService();

        Livro livro = null;

        long idPessoa = entity.verifica(Sistema.getUsuario());
        long idIsbn = entity.recuperaIsbn(isbn);

        if (idIsbn == -1) {
            idIsbn = entity.recuperaUltimoId() + 1;

            livro = new Livro(idIsbn);
            livro.setIdPessoa(idPessoa);
            livro.setAutores(autor);
            livro.setDataPublicacao(dataPub);
            livro.setIsbn(isbn);
            livro.setTitulo(titulo);
            livro.setSemelhantes(semelhante);

            livro.setIdPessoa(idPessoa);
            entity.save(livro);
            JOptionPane.showMessageDialog(null, "Livro " + isbn + " CADASTRADO");

        } else {
            livro = new Livro(idIsbn);
            livro.setIdPessoa(idPessoa);
            livro.setAutores(autor);
            livro.setDataPublicacao(dataPub);
            livro.setIsbn(isbn);
            livro.setTitulo(titulo);
            livro.setSemelhantes(semelhante);

            entity.update(livro, idPessoa);

            JOptionPane.showMessageDialog(null, "Livro " + isbn + " já "
                    + "estava cadastrado e foi ATUALIZADO");
        }
    }

    private void consultaGson() {

        String isbnT = isbn.getText().trim();
        if (isbnT.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Os campos com são OBRIGATÒRIOS");
        } else {
            JsonClass(formatString(isbnT));
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        isbn = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        isbn1 = new javax.swing.JTextField();
        autores = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dataPub1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        titulo1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        semelhante = new javax.swing.JTextArea();

        tabelaLivros.setEnabled(false);
        tabelaLivros.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tabelaLivros);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastrar Por Assistente");

        jLabel1.setText("ISBN");

        isbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnActionPerformed(evt);
            }
        });

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 255));
        jButton2.setText("Cadastrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        isbn1.setEditable(false);

        autores.setEditable(false);

        jLabel12.setText("Autores");

        dataPub1.setEditable(false);
        dataPub1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataPub1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Título");

        jLabel9.setText("ISBN");

        jLabel10.setText("Data de Publicação");

        jLabel11.setText("Semelhante");

        titulo1.setEditable(false);

        semelhante.setColumns(20);
        semelhante.setRows(5);
        jScrollPane3.setViewportView(semelhante);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(isbn1)
                            .addComponent(titulo1)
                            .addComponent(dataPub1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(autores))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isbn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataPub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        consultaGson();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void isbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        cadastrarLivro(titulo1.getText(), dataPub1.getText(), autores.getText(), isbn1.getText(), formatString(semelhante.getText().trim()));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dataPub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataPub1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataPub1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField autores;
    private javax.swing.JFormattedTextField dataPub1;
    private javax.swing.JTextField isbn;
    private javax.swing.JTextField isbn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea semelhante;
    private javax.swing.JTable tabelaLivros;
    private javax.swing.JTextField titulo1;
    // End of variables declaration//GEN-END:variables
}
