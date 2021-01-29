/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.Normalizer;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Livro;
import service.LivroService;
import service.ServiceFactory;

/**
 *
 * @author PICHAU
 */
public class EnviarArquivoLivro extends javax.swing.JInternalFrame {

    /**
     * Creates new form EnviarArquivoLivro
     */
    public EnviarArquivoLivro() {
        initComponents();
    }

    private static String formatString(String s) {
        String temp = Normalizer
                .normalize(s, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        return s;
    }

    private static Livro valida(long idIsbn, String autores, String isbn,
            String semelhante, String data, String titulo) {

        Livro livro = null;

        if (autores.isEmpty()
                || isbn.isEmpty()
                || semelhante.isEmpty()
                || data.isEmpty()
                || titulo.isEmpty()) {
            return livro;
        } else {
            livro = new Livro(idIsbn);
            livro.setAutores(autores);
            livro.setSemelhantes(semelhante);
            livro.setDataPublicacao(data);
            livro.setTitulo(titulo);
            livro.setIsbn(isbn);

        }

        return livro;
    }

    private void importarDados(String diretorio) throws FileNotFoundException {
        File arquivo = new File(diretorio);
        try {
            if (arquivo.exists()) {
                if (arquivo.canRead()) {
                    Scanner scanner = new Scanner(new FileReader(arquivo)).useDelimiter(";");

                    LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(diretorio)));
                    String nextLine = null;
                    int i = 0;
                    try {
                        while ((nextLine = lineCounter.readLine()) != null) {
                            if (nextLine == null) {
                                break;
                            }
                        }
                        i = lineCounter.getLineNumber();

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
                    }
                    if (i > 0) {
                        String[][] dados = new String[i][6];
                        i = 0;
                        while (scanner.hasNext()) {
                            dados[i][0] = formatString(scanner.next().trim());
                            dados[i][1] = formatString(scanner.next().trim());
                            dados[i][2] = formatString(scanner.next().trim());
                            dados[i][3] = formatString(scanner.next().trim());
                            dados[i][4] = formatString(scanner.next().trim());
                            i++;
                        }

                        tabelaLivros = new javax.swing.JTable();

                        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
                                dados,
                                new String[]{
                                    "ISBNC", "Título", "Autores", "Data da Publicação", "Semelhantes",}
                        ));
                        jScrollPane2.setViewportView(tabelaLivros);
                    } else {
                        JOptionPane.showMessageDialog(null, "Conteúdo inválido!");
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "O arquivo \"" + arquivo + "\" não foi encontrado.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "O arquivo \"" + arquivo + "\" não foi encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro na conversão dos dados: " + e.getMessage());
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "Conteúdo inválido!\n"
                    + " Verifique o conteúdo e o layout e tente novamente. "
                    + " " + e.getMessage());
        }

    }

    private void enviarArquivo() {
        JFileChooser arquivo = new JFileChooser();

        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("arquivo .livro", "livro");
        arquivo.setFileFilter(filter);

        int retorno = arquivo.showOpenDialog(null);

        File f = arquivo.getSelectedFile();

        if (retorno == JFileChooser.APPROVE_OPTION) {
            try {

                importarDados(f.getPath());

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Erro : ");
            }
        }
    }

    private void cadastrar() {
        if (tabelaLivros.getRowCount() > 0) {

            boolean cadastrou = false;

            LivroService entity = ServiceFactory.getLivroService();
            long idPessoa = entity.verifica(Sistema.getUsuario());

            Livro livro = null;
            for (int i = 0; i < tabelaLivros.getRowCount(); i++) {
                Long idIsbn = entity.recuperaIsbn((String) tabelaLivros.getModel().getValueAt(i, 1));

                if (idIsbn == -1) {
                    idIsbn = entity.recuperaUltimoId() + 1;
                    livro = valida(idIsbn,
                            (String) tabelaLivros.getModel().getValueAt(i, 0),
                            (String) tabelaLivros.getModel().getValueAt(i, 1),
                            (String) tabelaLivros.getModel().getValueAt(i, 4),
                            (String) tabelaLivros.getModel().getValueAt(i, 2),
                            (String) tabelaLivros.getModel().getValueAt(i, 3));

                    if (livro != null) {
                        livro.setIdPessoa(idPessoa);
                        cadastrou = entity.save(livro);
                    } else {
                        cadastrou = false;
                        break;
                    }
                } else {
                    livro = valida(idIsbn,
                            (String) tabelaLivros.getModel().getValueAt(i, 0),
                            (String) tabelaLivros.getModel().getValueAt(i, 1),
                            (String) tabelaLivros.getModel().getValueAt(i, 4),
                            (String) tabelaLivros.getModel().getValueAt(i, 2),
                            (String) tabelaLivros.getModel().getValueAt(i, 3));
                    if (livro != null) {
                        livro.setIdPessoa(idPessoa);
                        cadastrou = entity.update(livro, idPessoa);
                    } else {
                        cadastrou = false;
                        break;
                    }
                }

            }
            if (cadastrou) {
                JOptionPane.showMessageDialog(null, "Livros Incluidos ");
            } else {
                JOptionPane.showMessageDialog(null, "Verifique os dados do arquivo");
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
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Enviar Arquivo");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("Procurar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelaLivros.setEnabled(false);
        tabelaLivros.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tabelaLivros);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 204));
        jButton2.setText("Cadastrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        enviarArquivo();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaLivros;
    // End of variables declaration//GEN-END:variables
}
