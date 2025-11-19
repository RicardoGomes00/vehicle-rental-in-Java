/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package locacaoveiculos.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import locacaoveiculos.controller.LocacaoController;
import locacaoveiculos.controller.ClienteController;
import locacaoveiculos.model.*;

/**
 *
 * @author Ricardo Gomes
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    private final LocacaoController controller;
    private VeiculoTableModel tableModel;
    private ClienteController clienteController;
    private Cliente clienteSelecionado = null;

    /**
     * Creates new form NewJFrame
     */
    public TelaPrincipal() {
        initComponents();
        controller = new LocacaoController();
        clienteController = new ClienteController();

        carregarVeiculosNaTabela();
        carregarCombosFiltro(); 
        adicionarListeners();
        aplicarValidadores();
    }
    

    private void carregarVeiculosNaTabela() {
        List<Veiculo> lista = controller.getTodosVeiculos();
        tableModel = new VeiculoTableModel(lista);
        jTable1.setModel(tableModel);
    }
    
    private void carregarCombosFiltro() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("TODOS"); 
        jComboBox1.addItem("AUTOMOVEL");
        jComboBox1.addItem("MOTOCICLETA");
        jComboBox1.addItem("VAN");

        // Marca
        jComboBox2.removeAllItems();
        jComboBox2.addItem("TODAS"); 
        for (Marca m : Marca.values()) {
            jComboBox2.addItem(m.name());
        }

        // Categoria
        jComboBox3.removeAllItems();
        jComboBox3.addItem("TODAS");
        for (Categoria c : Categoria.values()) {
            jComboBox3.addItem(c.name());
        }
    }
    
    private void adicionarListeners() {
        ButtonGroup grupoBusca = new ButtonGroup();
        grupoBusca.add(jRadioButton1); 
        grupoBusca.add(jRadioButton2); 
        grupoBusca.add(jRadioButton3); 
        jRadioButton3.setSelected(true); 

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String textoBusca = jTextField3.getText();
                clienteSelecionado = null; // Limpa o cliente anterior
                jLabel6.setText("Cliente selecionado: Nenhum");

                if (textoBusca.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um termo para a busca.");
                    return;
                }

                try {
                    List<Cliente> resultados = new ArrayList<>();

                    if (jRadioButton1.isSelected()) { 
                        resultados = clienteController.buscarPorNome(textoBusca);
                    } else if (jRadioButton2.isSelected()) { 
                        resultados = clienteController.buscarPorSobrenome(textoBusca);
                    } else if (jRadioButton3.isSelected()) { 
                        Cliente c = clienteController.buscarPorCpf(textoBusca);
                        if (c != null) {
                            resultados.add(c);
                        }
                    }

                    if (resultados.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado.");
                    } else if (resultados.size() > 1) {
                        JOptionPane.showMessageDialog(null, "Múltiplos clientes encontrados. Refine sua busca. (Ex: " + resultados.get(0).getNome() + ", " + resultados.get(1).getNome() + "...)");
                    } else {
                        clienteSelecionado = resultados.get(0);
                        jLabel6.setText("Cliente selecionado: " + clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome());
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
                }
            }        
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String tipo = jComboBox1.getSelectedItem().toString();
                String marcaStr = jComboBox2.getSelectedItem().toString();
                String catStr = jComboBox3.getSelectedItem().toString();

                List<Veiculo> listaBase = controller.getTodosVeiculos(); //

                List<Veiculo> listaFiltrada = listaBase.stream()
                        .filter(v -> {
                            if (tipo.equals("TODOS")) return true;
                            if (tipo.equals("AUTOMOVEL")) return v instanceof Automovel;
                            if (tipo.equals("MOTOCICLETA")) return v instanceof Motocicleta;
                            if (tipo.equals("VAN")) return v instanceof Van;
                            return false;
                        })
                        .filter(v -> {
                            if (marcaStr.equals("TODAS")) return true;
                            return v.getMarca().name().equals(marcaStr);
                        })
                        .filter(v -> {
                            if (catStr.equals("TODAS")) return true;
                            return v.getCategoria().name().equals(catStr);
                        })
                        .collect(java.util.stream.Collectors.toList());

                tableModel.setLista(listaFiltrada); //
            }
        });
    }
    
    private void aplicarValidadores() {

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        smClientes = new javax.swing.JMenuItem();
        smVeiculos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        smDevolucao = new javax.swing.JMenuItem();
        smVenda = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Telal principal");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Placa", "Marca", "Modelo", "Ano", "Valor Diario", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Locar Veiculo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Dias da Locação:");

        jLabel2.setText("Data da Locação:");

        jLabel3.setText("LOCAÇÃO DE VEICULO");

        jRadioButton3.setText("cpf");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Cliente:");

        jLabel6.setText("Cliente selecionado:");

        jLabel7.setText("Pesquisar por: ");

        jButton2.setText("Buscar");

        jLabel8.setText("Filtros de Veículos:");

        jLabel9.setText("Tipo: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Marca:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Categoria:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Filtrar");

        jRadioButton1.setText("nome");

        jRadioButton2.setText("sobrenome");

        jMenu2.setText("Gerenciar");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        smClientes.setText("Clientes");
        smClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smClientesActionPerformed(evt);
            }
        });
        jMenu2.add(smClientes);

        smVeiculos.setText("Veiculos");
        smVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smVeiculosActionPerformed(evt);
            }
        });
        jMenu2.add(smVeiculos);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Alterar Estado");

        smDevolucao.setText("Devolução");
        smDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smDevolucaoActionPerformed(evt);
            }
        });
        jMenu3.add(smDevolucao);

        smVenda.setText("Venda");
        smVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smVendaActionPerformed(evt);
            }
        });
        jMenu3.add(smVenda);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(73, 73, 73))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3))
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void smVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smVendaActionPerformed
        // TODO add your handling code here:
        Venda v = new Venda();
        v.setVisible(true);
    }//GEN-LAST:event_smVendaActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void smClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smClientesActionPerformed
        // TODO add your handling code here:
        Clientes c = new Clientes();
        c.setVisible(true);
    }//GEN-LAST:event_smClientesActionPerformed

    private void smVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smVeiculosActionPerformed
        // TODO add your handling code here:
        Veiculos vc = new Veiculos();
        vc.setVisible(true);
    }//GEN-LAST:event_smVeiculosActionPerformed

    private void smDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smDevolucaoActionPerformed
        // TODO add your handling code here:
        Devolucao d = new Devolucao();
        d.setVisible(true);
    }//GEN-LAST:event_smDevolucaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, busque e selecione um cliente primeiro.");
            return;
        }

        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um veículo na tabela.");
            return;
        }

        Veiculo veiculoSelecionado = tableModel.getVeiculoAt(linhaSelecionada);

        if (veiculoSelecionado.getEstado() != Estado.DISPONIVEL) {
            JOptionPane.showMessageDialog(this, "Este veículo não está disponível. Estado atual: " + veiculoSelecionado.getEstado());
            return;
        }

        try {

            int dias;
            if (jTextField1.getText().isEmpty() || jTextField1.getText().equals("jTextField1")) {
                JOptionPane.showMessageDialog(this, "Por favor, insira a quantidade de dias.");
                return;
            }
            dias = Integer.parseInt(jTextField1.getText());


            Calendar data = Calendar.getInstance(); 


            controller.locarVeiculo(veiculoSelecionado, clienteSelecionado, dias, data); 

            JOptionPane.showMessageDialog(this, "Veículo " + veiculoSelecionado.getPlaca() + " locado para " + clienteSelecionado.getNome() + " por " + dias + " dias.");

            carregarVeiculosNaTabela(); 
            jLabel6.setText("Cliente selecionado: Nenhum");
            clienteSelecionado = null;
            jTextField1.setText("");
            jTextField3.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de dias inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao locar veículo: " + e.getMessage());
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JMenuItem smClientes;
    private javax.swing.JMenuItem smDevolucao;
    private javax.swing.JMenuItem smVeiculos;
    private javax.swing.JMenuItem smVenda;
    // End of variables declaration//GEN-END:variables
}
