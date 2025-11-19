/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.controller;

import java.util.Calendar;
import java.util.List;
import locacaoveiculos.dao.*;
import locacaoveiculos.model.*;

/**
 *
 * @author Ricardo Gomes
 */
public class LocacaoController {

    private final LocacaoDAO locacaoDAO;
    private final VeiculoDAO veiculoDAO;

    public LocacaoController() {
        this.locacaoDAO = new LocacaoDAO();
        this.veiculoDAO = new VeiculoDAO();
    }

    public LocacaoController(LocacaoDAO locacaoDAO, VeiculoDAO veiculoDAO) {
        this.locacaoDAO = locacaoDAO;
        this.veiculoDAO = veiculoDAO;
    }

    public void locarVeiculo(Veiculo veiculo, Cliente cliente, int dias, Calendar data) {
        if (veiculo == null || cliente == null) return;

        double valorLocacao = veiculo.getValorDiariaLocacao() * dias;
        Locacao locacao = new Locacao(dias, valorLocacao, data, cliente, veiculo);

        veiculo.locar(dias, data, cliente);
        veiculoDAO.atualizarEstado(veiculo.getPlaca(), Estado.LOCADO);
        locacaoDAO.registrar(locacao, buscarIdVeiculo(veiculo.getPlaca()), cliente.getId());
    }

    public void devolverVeiculo(Veiculo veiculo) {
        if (veiculo != null) {
            veiculo.devolver();
            veiculoDAO.atualizarEstado(veiculo.getPlaca(), Estado.DISPONIVEL);
            locacaoDAO.removerPorVeiculo(veiculo.getPlaca());
        }
    }

    public void venderVeiculo(Veiculo veiculo) {
        if (veiculo != null) {
            veiculo.vender();
            veiculoDAO.atualizarEstado(veiculo.getPlaca(), Estado.VENDIDO);
        }
    }

    public List<Locacao> listarLocacoes() {
        return locacaoDAO.listarTodas();
    }

    private int buscarIdVeiculo(String placa) {
        return veiculoDAO.buscarIdPorPlaca(placa);
    }
    
    public List<Veiculo> getTodosVeiculos() {
        return veiculoDAO.listarTodos();
    }
}