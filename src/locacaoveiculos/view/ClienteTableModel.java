/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import locacaoveiculos.model.Cliente;

/**
 *
 * @author Ricardo Gomes
 */
public class ClienteTableModel extends AbstractTableModel {

    private final String[] colunas = {"ID","Nome", "Sobrenome", "RG", "CPF", "Endereço"};
    private List<Cliente> clientes;

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes == null ? 0 : clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = clientes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getId();
            case 1 -> c.getNome();
            case 2 -> c.getSobrenome();
            case 3 -> c.getRg();
            case 4 -> c.getCpf();
            case 5 -> c.getEndereco();
            default -> null;
        };
    }

    public Cliente getClienteAt(int row) {
        return clientes.get(row);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }
}