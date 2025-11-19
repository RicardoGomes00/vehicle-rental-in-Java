/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.controller;

import java.util.List;
import locacaoveiculos.dao.VeiculoDAO;
import locacaoveiculos.model.*;

/**
 *
 * @author Ricardo Gomes
 */
public class VeiculoController {

    private final VeiculoDAO veiculoDAO;

    public VeiculoController() {
        this.veiculoDAO = new VeiculoDAO();
    }

    public VeiculoController(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    public void adicionarVeiculo(Veiculo v) {
        if (v != null)
            veiculoDAO.inserir(v);
    }

    public Veiculo buscarPorId(int id) {
        return veiculoDAO.buscarPorId(id);
    }
    
    public Veiculo buscarPorPlaca(String placa) {
        return veiculoDAO.buscarPorPlaca(placa);
    }
    
    public List<Veiculo> listarLocados() {
        return veiculoDAO.listarLocados();
    }
    
    public List<Veiculo> listarDisponiveis() {
        return veiculoDAO.listarDisponiveis();
    }

    public List<Veiculo> listarTodos() {
        return veiculoDAO.listarTodos();
    }

    public void excluir(String placa) {
        veiculoDAO.excluir(placa);
    }
    
    public List<Veiculo> listarDisponiveisParaVenda() {
        return veiculoDAO.listarDisponiveisParaVenda();
    }
}