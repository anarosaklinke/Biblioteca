/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.Login;
import model.Pessoa;
import service.LoginService;
import service.PessoaService;
import service.ServiceFactory;

import utils.validacao;

/**
 *
 * @author PICHAU
 */
public class AlteraUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlteraUsuario
     */
    public AlteraUsuario() {
        initComponents();
    }

    private void alteraUsuario() {
        PessoaService entity = ServiceFactory.getPessoaService();
        LoginService entity2 = ServiceFactory.getLoginService();
        
        String nomeTemp = nome.getText();
        String cpfTemp = cpf.getText();
        String dataTemp = dataNascimento.getText();
        String usuarioTemp = usuario.getText();
        String senhaTemp = senha.getText();

        nomeTemp = validacao.formatString_E(nomeTemp.trim());
        
        cpfTemp = validacao.formatString(cpfTemp.trim());
        dataTemp = validacao.formatString(dataTemp.trim());
        
        usuarioTemp = validacao.formatString_E(usuarioTemp.trim());
        senhaTemp = validacao.formatString_E(senhaTemp.trim());

        if (nomeTemp.isEmpty()
                || cpfTemp.isEmpty()
                || dataTemp.isEmpty()
                || usuarioTemp.isEmpty()
                || senhaTemp.isEmpty()
                || !validacao.isDateValid(dataTemp)) {
            JOptionPane.showMessageDialog(null, "Dados Inválidos - *dd/mm/AAAA");
        } else {

            boolean salvar = true;

            if (!cpfTemp.equals(cpfOld.getText())) {
                if (entity.verificaCPF(cpfTemp)) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado");
                    salvar = false;
                }
            }

            long idOldPessoa = entity.idCPF(cpfOld.getText());
            long idOld = entity2.consultaLongUsuario(idOldPessoa);

            String loginUsuario = entity2.consultaLoginUsuario(idOldPessoa);

            if (!loginUsuario.equals(usuarioTemp)) {
                if (entity2.verificaUsuario(usuarioTemp)) {
                    JOptionPane.showMessageDialog(null, "Usuario já cadastrado");
                    salvar = false;
                }
            }

            if (salvar) {

                long idPessoa;

                idPessoa = entity.recuperaUltimoId();
                if (idPessoa == -1) {
                    idPessoa = 1;
                } else {
                    idPessoa++;
                }
                Pessoa pessoa = new Pessoa(idPessoa);

                Date data;
                int idade;

                String d;

                d = dataTemp;
                String[] lista;
                lista = d.split("/");
                Calendar cal = Calendar.getInstance();
                int temp = Integer.parseInt(lista[0]);

                cal.set(Calendar.DAY_OF_MONTH, temp);
                temp = Integer.parseInt(lista[1]);
                temp--;
                cal.set(Calendar.MONTH, temp);
                temp = Integer.parseInt(lista[2]);
                cal.set(Calendar.YEAR, temp);

                data = new java.sql.Date(cal.getTimeInMillis());

                cal = Calendar.getInstance();
                java.sql.Date data2 = new java.sql.Date(cal.getTimeInMillis());

                LocalDate data3 = data2.toLocalDate();
                LocalDate data4 = data.toLocalDate();
                Period p = Period.between(data4, data3);
                idade = p.getYears();

                pessoa.setSexo((sexo.getSelectedItem()).toString());

                pessoa.setCpf(cpfTemp);
                pessoa.setDataNascimento(data);
                pessoa.setIdade(idade);
                pessoa.setNome(nomeTemp);

                /*recupera id de Login*/
                long idLogin = entity2.recuperaUltimoId();
                if (idLogin == -1) {
                    idLogin = 1;
                } else {
                    idLogin++;
                }

                Login login = new Login(idLogin, false);
                login.setSenha(senhaTemp);
                login.setUsuario(usuarioTemp);
                //pessoa.setLogin(login.getIdLogin());

                boolean b;
                b = entity2.update(login, idOld);
                if (b == false) {
                    JOptionPane.showMessageDialog(null, "Erro: Login não foi salvo no banco de dados ");
                }

                b = entity.update(pessoa, idOldPessoa);
                if (b == false) {
                    JOptionPane.showMessageDialog(null, "Erro: Pessoa não foi salvo no banco de dados ");
                }

                nome.setText("");
                nome.setText("");
                cpf.setText("");
                senha.setText("");
                usuario.setText("");
                dataNascimento.setText("");

                JOptionPane.showMessageDialog(null, "Usuário Atualizado!");

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

        jLabel1 = new javax.swing.JLabel();
        MaskFormatter mascaraCpf = null;
        try{
            mascaraCpf = new MaskFormatter("#########-##");
        }
        catch(ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }
        cpf = new javax.swing.JFormattedTextField(mascaraCpf);
        sexo = new javax.swing.JComboBox<>();
        MaskFormatter mascaraData = null;
        try{
            mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }
        dataNascimento = new javax.swing.JFormattedTextField(mascaraData);
        usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();
        nome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cpfOld = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Cadastro de Usuário");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Atualizar Usuário");

        cpf.setBounds(150,120,100,20);
        cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfActionPerformed(evt);
            }
        });

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feminino", "Masculino" }));
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });

        dataNascimento.setBounds(150,160,100,20);
        dataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNascimentoActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome*");

        senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaActionPerformed(evt);
            }
        });

        nome.setText(nome.getText());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 51));
        jButton1.setText("ATUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF*");

        jLabel4.setText("Sexo*");

        jLabel5.setText("Data de Nascimento*");

        jLabel6.setText("Usuário*");

        jLabel7.setText("Senha*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Atualizar Usuário");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(10, 10, 10)
                                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(21, 21, 21)
                                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(16, 16, 16)
                                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nome)
                                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cpfOld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(cpfOld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfActionPerformed

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexoActionPerformed

    private void dataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataNascimentoActionPerformed

    private void senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        alteraUsuario();

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JFormattedTextField cpf;
    public javax.swing.JLabel cpfOld;
    public javax.swing.JFormattedTextField dataNascimento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JTextField nome;
    public javax.swing.JPasswordField senha;
    public javax.swing.JComboBox<String> sexo;
    public javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
