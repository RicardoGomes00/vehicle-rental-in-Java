/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import locacaoveiculos.model.*;

/**
 *
 * @author Ricardo Gomes
 */
public class LocacaoTableModel extends AbstractTableModel {

    private final String[] colunas = {
        "Nome Cliente", "Placa", "Marca", "Modelo", "Ano", "Data Locação", 
        "Valor Diario", "Dias Locados", "Valor Locação"
    };
    
    private List<Locacao> locacoes;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public LocacaoTableModel(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    @Override
    public int getRowCount() {
        return locacoes.size();
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
        Locacao loc = locacoes.get(rowIndex);
        Veiculo v = loc.getVeiculo();
        Cliente c = loc.getCliente();

        switch (columnIndex) {
            case 0: return c.getNome() + " " + c.getSobrenome();
            case 1: return v.getPlaca();
            case 2: return v.getMarca().toString();
            case 3: return getModeloNome(v);
            case 4: return v.getAno();
            case 5: return formatarData(loc.getData());
            case 6: return String.format("R$ %.2f", v.getValorDiariaLocacao());
            case 7: return loc.getDias();
            case 8: return String.format("R$ %.2f", loc.getValor());
            default:
                return null;
        }
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

    private String formatarData(Calendar data) {
        if (data == null) return "";
        return sdf.format(data.getTime());
    }
    
    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
        fireTableDataChanged();
    }

    public Locacao getLocacaoAt(int rowIndex) {
        return locacoes.get(rowIndex);
    }
}