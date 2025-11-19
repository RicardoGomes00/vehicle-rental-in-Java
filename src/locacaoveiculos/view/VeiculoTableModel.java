/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import locacaoveiculos.model.*;

/**
 *
 * @author Ricardo Gomes
 */
public class VeiculoTableModel extends AbstractTableModel {

    private final String[] colunas = {
        "Tipo", "Marca", "Modelo", "Placa", "Ano", "Estado"
    };

    private List<Veiculo> lista;

    public VeiculoTableModel(List<Veiculo> lista) {
        this.lista = lista;
    }

    public void setLista(List<Veiculo> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
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
        Veiculo v = lista.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> {
                if (v instanceof Automovel) yield "Automóvel";
                if (v instanceof Motocicleta) yield "Motocicleta";
                if (v instanceof Van) yield "Van";
                yield "Desconhecido";
            }

            case 1 -> v.getMarca().toString();

            case 2 -> {
                if (v instanceof Automovel a) yield a.getModelo().toString();
                if (v instanceof Motocicleta m) yield m.getModelo().toString();
                if (v instanceof Van n) yield n.getModelo().toString();
                yield "";
            }

            case 3 -> v.getPlaca();

            case 4 -> v.getAno();

            case 5 -> v.getEstado().toString();

            default -> "";
        };
    }

    public Veiculo getVeiculoAt(int rowIndex) {
        return lista.get(rowIndex);
    }
}