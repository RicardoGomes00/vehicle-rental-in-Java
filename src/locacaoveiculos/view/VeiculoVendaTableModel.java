/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.view;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import locacaoveiculos.model.Veiculo;
import locacaoveiculos.model.Automovel;
import locacaoveiculos.model.Motocicleta;
import locacaoveiculos.model.Van;


/**
 *
 * @author Ricardo Gomes
 */
public class VeiculoVendaTableModel extends AbstractTableModel {

    private final String[] colunas = {"Placa", "Marca", "Modelo", "Ano", "Valor Venda"};
    private List<Veiculo> lista;

    public VeiculoVendaTableModel(List<Veiculo> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista != null ? lista.size() : 0;
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
            case 0 -> v.getPlaca();
            case 1 -> v.getMarca().name();
            case 2 -> getModeloNome(v);        
            case 3 -> v.getAno();
            case 4 -> String.format("R$ %.2f", v.getValorParaVenda());
            default -> null;
        };
    }


    private String getModeloNome(Veiculo v) {

        if (v instanceof Automovel a)
            return a.getModelo().toString();

        if (v instanceof Motocicleta m)
            return m.getModelo().toString();

        if (v instanceof Van vn)
            return vn.getModelo().toString();

        return "—";
    }

    public void setLista(List<Veiculo> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }

    public Veiculo getVeiculoAt(int rowIndex) {
        return lista.get(rowIndex);
    }
}