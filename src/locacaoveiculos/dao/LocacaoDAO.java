/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.dao;

import locacaoveiculos.model.*;
import locacaoveiculos.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Ricardo Gomes
 */
public class LocacaoDAO {

    public void registrar(Locacao locacao, int veiculoId, int clienteId) {
        String sql = """
            INSERT INTO locacao (veiculo_id, cliente_id, dias, valor, data_locacao)
            VALUES (?, ?, ?, ?, ?)
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, veiculoId);
            stmt.setInt(2, clienteId);
            stmt.setInt(3, locacao.getDias());
            stmt.setDouble(4, locacao.getValor());
            stmt.setDate(5, new java.sql.Date(locacao.getData().getTimeInMillis()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locacao> listarTodas() {
        List<Locacao> lista = new ArrayList<>();
        String sql = """
            SELECT l.*, c.*, v.*
            FROM locacao l
            JOIN cliente c ON c.id = l.cliente_id
            JOIN veiculo v ON v.id = l.veiculo_id
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                );

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data_locacao"));

                Veiculo veiculo = new VeiculoDAO().buscarPorId(rs.getInt("veiculo_id"));

                Locacao loc = new Locacao(
                        rs.getInt("dias"),
                        rs.getDouble("valor"),
                        data,
                        cliente,
                        veiculo
                );
                lista.add(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void removerPorVeiculo(String placa) {
        String sql = """
            DELETE FROM locacao 
            WHERE veiculo_id = (SELECT id FROM veiculo WHERE placa = ?)
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
            System.out.println("Locação removida para o veículo com placa " + placa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
