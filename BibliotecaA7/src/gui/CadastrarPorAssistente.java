/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

import model.Classificacao;
import model.Livro;
import service.ClassificacaoService;
import service.LivroService;
import service.ServiceFactory;
import utils.validacao;

import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class CadastrarPorAssistente extends javax.swing.JInternalFrame {

    public CadastrarPorAssistente() {
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

    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    private void JsonClass(String isbn) throws IOException {

        JSONObject obj;
        String urlAutor = null;
        String pubData = " ";
        String titulo = " ";
        String autores = " ";
        String json = getJSON("https://openlibrary.org/isbn/" + isbn + ".json");
        JSONArray results_arr = null;
        try {
            obj = new JSONObject(json);

            if (obj.has("authors")) {
                results_arr = obj.getJSONArray("authors");
            }

            if (obj.has("publish_date")) {
                pubData = obj.getString("publish_date");
            }
            if (obj.has("title")) {
                titulo = obj.getString("title");
            }

            if (results_arr != null) {
                int n = results_arr.length();
                for (int i = 0; i < n; ++i) {
                    // get the place id of each object in JSON (Google Search API)
                    urlAutor = results_arr.getJSONObject(i).getString("key");
                    json = getJSON("https://openlibrary.org/" + urlAutor + ".json");
                    obj = new JSONObject(json);

                    autores = obj.getString("name") + "/ " + autores;
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        titulo1.setText(titulo);
        isbn1.setText(isbn);
        dataPub1.setText(pubData);
        autores1.setText(autores);

    }

    private void cadastrarLivro(String titulo, String dataPub, String autor, String isbn, String semelhante) {
        LivroService entity = ServiceFactory.getLivroService();

        Livro livro = null;

        long idPessoa = entity.verifica(Sistema.getUsuario());
        long idIsbn = entity.recuperaIsbn(isbn);

        String classTemp = (classifica.getSelectedItem()).toString();
        ClassificacaoService entity2 = ServiceFactory.getClassificacaoService();
        long idClass = entity2.verifica(classTemp);

        if (idIsbn == -1) {
            idIsbn = entity.recuperaUltimoId() + 1;

            livro = new Livro(idIsbn);
            livro.setIdPessoa(idPessoa);
            livro.setAutores(autor);
            livro.setDataPublicacao(dataPub);
            livro.setIsbn(isbn);
            livro.setTitulo(titulo);
            livro.setSemelhantes(semelhante);
            livro.setIdClassificacao(idClass);

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
            livro.setIdClassificacao(idClass);

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
            try {
                JsonClass(validacao.formatString(isbnT));
            } catch (IOException ex) {
                Logger.getLogger(CadastrarPorAssistente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void mudaSemelhante(String isbnS, String tituloS,
            String dataS, String autor) {
        semelhante.setText(isbnS);
        titulo1.setText(tituloS);
        dataPub1.setText(dataS);
        autores1.setText(autor);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        isbn = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        isbn1 = new javax.swing.JTextField();
        autores1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dataPub1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        titulo1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        classifica = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        semelhante = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();

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

        autores1.setEditable(false);

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

        jLabel2.setText("Classificação");

        categorias();

        jButton4.setForeground(new java.awt.Color(204, 0, 51));
        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        semelhante.setEditable(false);
        semelhante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semelhanteActionPerformed(evt);
            }
        });

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel2))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(isbn1)
                        .addComponent(titulo1)
                        .addComponent(dataPub1)
                        .addComponent(autores1)
                        .addComponent(classifica, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(semelhante, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(0, 80, Short.MAX_VALUE))
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
                    .addComponent(autores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(semelhante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5)
                        .addComponent(jButton4))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(classifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        cadastrarLivro(validacao.formatString(titulo1.getText()), validacao.formatString(dataPub1.getText()),
                validacao.formatString(autores1.getText()), validacao.formatString_E(isbn1.getText()),
                validacao.formatString(semelhante.getText().trim()));

    }//GEN-LAST:event_jButton2ActionPerformed

    private void dataPub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataPub1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataPub1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        semelhante.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void semelhanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semelhanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semelhanteActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        selecionarSemelhante();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextField autores1;
    private javax.swing.JComboBox<String> classifica;
    private static javax.swing.JFormattedTextField dataPub1;
    private javax.swing.JTextField isbn;
    private javax.swing.JTextField isbn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JFormattedTextField semelhante;
    private javax.swing.JTable tabelaLivros;
    private static javax.swing.JTextField titulo1;
    // End of variables declaration//GEN-END:variables
}
