/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Date;
import java.util.List;
import model.Contato;
import model.Endereco;
import model.Pessoa;
import service.ContatoService;
import service.EnderecoService;
import service.LoginService;
import service.PessoaService;
import service.ServiceFactory;

/**
 *
 * @author PICHAU
 */
public class ListaUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaClientes
     */
    public ListaUsuario() {
        initComponents();

        exibir();
    }

    public final void exibir() {
        PessoaService entity = ServiceFactory.getPessoaService();
        ContatoService entity2 = ServiceFactory.getContatoService();
        LoginService entity3 = ServiceFactory.getLoginService();
        EnderecoService entity4 = ServiceFactory.getEnderecoService();

        List<Pessoa> pessoa = entity.recuperaPessoa();

        Pessoa p;
        String[][] dados = new String[pessoa.size()][17];

        for (int i = 0; i < pessoa.size(); i++) {

            p = pessoa.get(i);
            dados[i][3] = p.getDataNascimento().toString();
            dados[i][0] = p.getNome();
            dados[i][5] = p.getSexo();
            dados[i][4] = " " + p.getIdade();
            dados[i][1] = p.getCpf();

            Contato c = entity2.recuperaContatoPessoa(p.getIdPessoa());

            if (c != null) {
                dados[i][6] = c.getCelular();
                dados[i][7] = c.getEmail();
                dados[i][8] = c.getTelCom();
                dados[i][9] = c.getTelRes();
            }

            Endereco e = entity4.recuperaEnderecoPessoa(p.getIdPessoa());

            if (e != null) {
                dados[i][10] = e.getRua();
                dados[i][11] = " " + e.getNumero();
                dados[i][12] = e.getBairro();
                dados[i][13] = e.getCep();
                dados[i][14] = e.getCidade();
                dados[i][15] = e.getEstado();
                dados[i][16] = e.getPais();
            }

            String us = entity3.consultaLoginUsuario(p.getIdPessoa());

            dados[i][2] = us;

        }
        tabelaUsuario = new javax.swing.JTable();

        tabelaUsuario.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{
                    "Nome", "CPF", "Usuario", "Data de Nascimento", "Idade", "Sexo", "Celular", "Email",
                    "Tel Comercial", "Tel Res", "Rua", "Numero", "Bairro", "CEP",
                    "Cidade", "Estado", "Pais"

                }
        ));

        jScrollPane2.setViewportView(tabelaUsuario);

    }

    private void alteraContato() {
        if (tabelaUsuario.getSelectedRow() != -1) {
            CadastrarContato alterar = new CadastrarContato();
            alterar.cpf.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 1));

            alterar.residencial.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 9));
            alterar.comercial.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 8));
            alterar.email.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 7));
            alterar.celular.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 6));

            getParent().add(alterar);
            alterar.setVisible(true);
        }
    }

    private void alteraEndereco() {
        if (tabelaUsuario.getSelectedRow() != -1) {
            CadastrarEndereco alterar = new CadastrarEndereco();
            alterar.cpf.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 1));

            alterar.rua.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 10));
            alterar.numero.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 11));
            alterar.bairro.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 12));
            alterar.cep.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 13));
            alterar.cidade.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 14));
            alterar.estado.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 15));
            alterar.pais.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 16));

            getParent().add(alterar);
            alterar.setVisible(true);
        }
    }

    private void cadastrar() {

        CadastrarUsuario cad = new CadastrarUsuario();
        getParent().add(cad);
        cad.setVisible(true);
    }

    private void alteraUsuario() {
        if (tabelaUsuario.getSelectedRow() != -1) {
            AlteraUsuario alterar = new AlteraUsuario();
            alterar.cpfOld.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 1));
            alterar.cpf.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 1));
            alterar.nome.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 0));

            if (((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 5)).equals("Feminino")) {
                alterar.sexo.setSelectedIndex(0);
            } else {
                alterar.sexo.setSelectedIndex(2);
            }
            alterar.dataNascimento.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 3));

            Date data;
            String d = (String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 3);
            String[] lista;
            lista = d.split("-");

            alterar.dataNascimento.setText(lista[2] + "/"
                    + lista[1] + "/" + lista[0]);

            PessoaService entity = ServiceFactory.getPessoaService();
            LoginService entity2 = ServiceFactory.getLoginService();

            long idPessoa = entity.idCPF((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 1));
            alterar.usuario.setText(entity2.consultaLoginUsuario(idPessoa));

            getParent().add(alterar);
            alterar.setVisible(true);
        }
    }

    private void excluirUsuario() {
        if (tabelaUsuario.getSelectedRow() != -1) {
            ExcluirUsuario exclui = new ExcluirUsuario();
            exclui.cpf.setText((String) tabelaUsuario.getModel().getValueAt(tabelaUsuario.getSelectedRow(), 1));
            getParent().add(exclui);
            exclui.setVisible(true);
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
        tabelaUsuario = new javax.swing.JTable();
        Contato = new javax.swing.JButton();
        Endereco = new javax.swing.JButton();
        Atualizar = new javax.swing.JButton();
        label1 = new java.awt.Label();
        atualizar = new java.awt.Button();
        usuario = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista de Usuário");

        Excluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Excluir.setForeground(new java.awt.Color(204, 0, 51));
        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        tabelaUsuario.setEnabled(false);
        tabelaUsuario.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tabelaUsuario);

        Contato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Contato.setForeground(new java.awt.Color(0, 0, 204));
        Contato.setText("Cadastrar/Alterar - Contato");
        Contato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContatoActionPerformed(evt);
            }
        });

        Endereco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Endereco.setForeground(new java.awt.Color(0, 0, 204));
        Endereco.setText("Cadastrar/Alterar -  Endereço");
        Endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnderecoActionPerformed(evt);
            }
        });

        Atualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Atualizar.setForeground(new java.awt.Color(0, 0, 204));
        Atualizar.setText("Atualizar Usuario");
        Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label1.setText("Lista de Usuarios:");

        atualizar.setLabel("Atualizar");
        atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarActionPerformed(evt);
            }
        });

        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setForeground(new java.awt.Color(0, 0, 204));
        usuario.setText("Cadastrar Usuario");
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Contato, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(325, 325, 325))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Contato, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        excluirUsuario();
    }//GEN-LAST:event_ExcluirActionPerformed

    private void ContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContatoActionPerformed
        // TODO add your handling code here:

        alteraContato();
    }//GEN-LAST:event_ContatoActionPerformed

    private void EnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnderecoActionPerformed
        // TODO add your handling code here:
        alteraEndereco();
    }//GEN-LAST:event_EnderecoActionPerformed

    private void AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarActionPerformed
        // TODO add your handling code here:
        alteraUsuario();
    }//GEN-LAST:event_AtualizarActionPerformed

    private void atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarActionPerformed
        // TODO add your handling code here:
        exibir();
    }//GEN-LAST:event_atualizarActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_usuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atualizar;
    private javax.swing.JButton Contato;
    private javax.swing.JButton Endereco;
    private javax.swing.JButton Excluir;
    private java.awt.Button atualizar;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JTable tabelaUsuario;
    private javax.swing.JButton usuario;
    // End of variables declaration//GEN-END:variables

}
