/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.controller;

import java.util.List;
import locacaoveiculos.dao.ClienteDAO;
import locacaoveiculos.model.Cliente;

/**
 *
 * @author Ricardo Gomes
 */
public class ClienteController {
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void adicionarCliente(Cliente cliente) {
        if (cliente != null)
            clienteDAO.inserir(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        if (cliente != null)
            clienteDAO.atualizar(cliente);
    }

    public void excluirCliente(int id) {
        clienteDAO.excluir(id);
    }

    public Cliente buscarPorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteDAO.buscarPorCpf(cpf);
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        return clienteDAO.buscarPorNome(nome);
    }
    
    public List<Cliente> buscarPorSobrenome(String sobrenome) {
        return clienteDAO.buscarPorSobrenome(sobrenome);
    }
}