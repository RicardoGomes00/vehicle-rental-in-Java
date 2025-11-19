/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.dao;

import locacaoveiculos.model.*;
import locacaoveiculos.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Gomes
 */
public class VeiculoDAO {

    public void inserir(Veiculo v) {
        String sql = """
            INSERT INTO veiculo (tipo, marca, estado, categoria, modelo, valor_compra, placa, ano)
            VALUES (?::tipo_t, ?::marca_t, ?::estado_t, ?::categoria_t, ?, ?, ?, ?)
            RETURNING id
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getClass().getSimpleName().toUpperCase());
            stmt.setString(2, v.getMarca().name());
            stmt.setString(3, v.getEstado().name());
            stmt.setString(4, v.getCategoria().name());
            stmt.setString(5, getModeloNome(v));
            stmt.setDouble(6, v.getValorParaVenda());
            stmt.setString(7, v.getPlaca());
            stmt.setInt(8, v.getAno());

            stmt.executeQuery(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapVeiculo(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Veiculo> listarTodos() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo ORDER BY id";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) lista.add(mapVeiculo(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void excluir(String placa) {
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
            System.out.println("Veículo com placa " + placa + " removido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getModeloNome(Veiculo v) {
        if (v instanceof Automovel a) return a.getModelo().name();
        if (v instanceof Motocicleta m) return m.getModelo().name();
        if (v instanceof Van van) return van.getModelo().name();
        return "";
    }

    private Veiculo mapVeiculo(ResultSet rs) throws SQLException {
        Marca marca = Marca.valueOf(rs.getString("marca").toUpperCase());
        Estado estado = Estado.valueOf(rs.getString("estado").toUpperCase());
        Categoria categoria = Categoria.valueOf(rs.getString("categoria").toUpperCase());
        double valorCompra = rs.getDouble("valor_compra");
        String placa = rs.getString("placa");
        int ano = rs.getInt("ano");
        String tipo = rs.getString("tipo");
        String modelo = rs.getString("modelo").toUpperCase();

        return switch (tipo.toUpperCase()) {
            case "AUTOMOVEL" ->
                new Automovel(marca, estado, categoria, valorCompra, placa, ano, ModeloAutomovel.valueOf(modelo));
            case "MOTOCICLETA" ->
                new Motocicleta(marca, estado, categoria, valorCompra, placa, ano, ModeloMotocicleta.valueOf(modelo));
            case "VAN" ->
                new Van(marca, estado, categoria, valorCompra, placa, ano, ModeloVan.valueOf(modelo));
            default -> null;
        };
    }
    
    public void atualizarEstado(String placa, Estado novoEstado) {
        String sql = "UPDATE veiculo SET estado = ?::estado_t WHERE placa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoEstado.name());
            stmt.setString(2, placa);
            stmt.executeUpdate();
            System.out.println("Estado do veículo " + placa + " atualizado para " + novoEstado);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM veiculo WHERE placa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapVeiculo(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int buscarIdPorPlaca(String placa) {
        String sql = "SELECT id FROM veiculo WHERE placa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Veiculo> listarLocados() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE estado = 'LOCADO'::estado_t ORDER BY id";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapVeiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE estado = 'DISPONIVEL'::estado_t ORDER BY id";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapVeiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Veiculo> listarDisponiveisParaVenda() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = """
            SELECT * FROM veiculo 
            WHERE estado = 'DISPONIVEL'::estado_t 
            ORDER BY id
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veiculo v = mapVeiculo(rs);
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}