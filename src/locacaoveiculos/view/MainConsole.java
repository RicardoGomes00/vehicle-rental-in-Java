/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package locacaoveiculos.view;

import java.util.*;
import locacaoveiculos.dao.*;
import locacaoveiculos.model.*;
import locacaoveiculos.controller.*;

/**
 *
 * @author Ricardo Gomes
 */
public class MainConsole {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        LocacaoDAO locacaoDAO = new LocacaoDAO();

        ClienteController clienteController = new ClienteController(clienteDAO);
        VeiculoController veiculoController = new VeiculoController(veiculoDAO);
        LocacaoController locacaoController = new LocacaoController(locacaoDAO, veiculoDAO);

        int opcao;
        do {
            System.out.println("\n===== MENU LOCADORA =====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Cadastrar Veiculo");
            System.out.println("4. Listar Veiculos");
            System.out.println("5. Locar Veiculo");
            System.out.println("6. Devolver Veiculo");
            System.out.println("7. Vender Veiculo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = lerInt();

            try {
                switch (opcao) {
                    case 1 -> cadastrarCliente(clienteController);
                    case 2 -> listarClientes(clienteController);
                    case 3 -> cadastrarVeiculo(veiculoController);
                    case 4 -> listarVeiculos(veiculoController);
                    case 5 -> locarVeiculo(clienteController, veiculoController, locacaoController);
                    case 6 -> devolverVeiculo(veiculoController, locacaoController);
                    case 7 -> venderVeiculo(veiculoController, locacaoController);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opcao invalida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    // ====== METODOS AUXILIARES ======

    private static void cadastrarCliente(ClienteController controller) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = sc.nextLine();
        System.out.print("RG: ");
        String rg = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Endereco: ");
        String endereco = sc.nextLine();

        Cliente c = new Cliente(nome, sobrenome, rg, cpf, endereco);
        controller.adicionarCliente(c);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes(ClienteController controller) {
        List<Cliente> lista = controller.listarTodos();
        System.out.println("\n=== Lista de Clientes ===");
        for (Cliente c : lista) {
            System.out.println(c.getId() + " - " + c.getNome() + " " + c.getSobrenome() + " | CPF: " + c.getCpf());
        }
    }

    private static void cadastrarVeiculo(VeiculoController controller) {
        Estado estadoInicial = Estado.DISPONIVEL;

        System.out.println("\nTipo de veiculo: 1-Automovel | 2-Motocicleta | 3-Van");
        int tipo = lerInt();

        System.out.print("Placa (XXX-0000): ");
        String placa = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt();

        System.out.print("Valor de compra: ");
        double valor = lerDouble();

        System.out.println("Marca (VW, GM, FIAT, HONDA, MERCEDES): ");
        Marca marca = Marca.valueOf(sc.nextLine().toUpperCase());

        System.out.println("Categoria (POPULAR, INTERMEDIARIO, LUXO): ");
        Categoria cat = Categoria.valueOf(sc.nextLine().toUpperCase());

        Veiculo v = null;
        switch (tipo) {
            case 1 -> {
                System.out.println("Modelo (GOL, CELTA, PALIO): ");
                ModeloAutomovel mod = ModeloAutomovel.valueOf(sc.nextLine().toUpperCase());
                v = new Automovel(marca, estadoInicial, cat, valor, placa, ano, mod);
            }
            case 2 -> {
                System.out.println("Modelo (CG125, CBR500): ");
                ModeloMotocicleta mod = ModeloMotocicleta.valueOf(sc.nextLine().toUpperCase());
                v = new Motocicleta(marca, estadoInicial, cat, valor, placa, ano, mod);
            }
            case 3 -> {
                System.out.println("Modelo (KOMBI, SPRINTER): ");
                ModeloVan mod = ModeloVan.valueOf(sc.nextLine().toUpperCase());
                v = new Van(marca, estadoInicial, cat, valor, placa, ano, mod);
            }
            default -> System.out.println("Tipo invalido!");
        }

        if (v != null) {
            controller.adicionarVeiculo(v);
            System.out.println("Veiculo cadastrado com sucesso!");
        }
    }

    private static void listarVeiculos(VeiculoController controller) {
        List<Veiculo> lista = controller.listarTodos();
        System.out.println("\n=== Lista de Veiculos ===");
        for (Veiculo v : lista) {
            System.out.printf("%s | %s | %s | %d | R$ %.2f | Estado: %s%n",
                    v.getPlaca(), v.getMarca(), v.getCategoria(), v.getAno(),
                    v.getValorParaVenda(), v.getEstado());
        }
    }

    private static void locarVeiculo(ClienteController clienteController, VeiculoController veiculoController, LocacaoController locacaoController) {
        listarClientes(clienteController);
        System.out.print("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();
        Cliente cliente = clienteController.buscarPorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        listarVeiculos(veiculoController);
        System.out.print("Digite a placa do veiculo a locar: ");
        String placa = sc.nextLine();
        Veiculo v = veiculoController.buscarPorPlaca(placa);
        if (v == null || v.getEstado() != Estado.DISPONIVEL) {
            System.out.println("Veiculo nao disponivel!");
            return;
        }

        System.out.print("Quantidade de dias: ");
        int dias = lerInt();
        Calendar data = Calendar.getInstance();
        locacaoController.locarVeiculo(v, cliente, dias, data);
        System.out.println("Veiculo locado com sucesso!");
    }

    private static void devolverVeiculo(VeiculoController veiculoController, LocacaoController locacaoController) {
        List<Veiculo> locados = veiculoController.listarLocados();
        if (locados.isEmpty()) {
            System.out.println("Nenhum veiculo locado!");
            return;
        }

        System.out.println("\nVeiculos locados:");
        for (Veiculo v : locados) {
            System.out.println("Placa: " + v.getPlaca());
        }

        System.out.print("Placa do veiculo para devolver: ");
        String placa = sc.nextLine();
        Veiculo v = veiculoController.buscarPorPlaca(placa);
        if (v == null || v.getEstado() != Estado.LOCADO) {
            System.out.println("Veiculo nao encontrado ou nao esta locado!");
            return;
        }

        locacaoController.devolverVeiculo(v);
        System.out.println("Veiculo devolvido com sucesso!");
    }

    private static void venderVeiculo(VeiculoController veiculoController, LocacaoController locacaoController) {
        List<Veiculo> disponiveis = veiculoController.listarDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum veiculo disponivel para venda!");
            return;
        }

        System.out.println("\nVeiculos disponiveis para venda:");
        for (Veiculo v : disponiveis) {
            System.out.printf("%s - %s (%d) | Valor para venda: R$ %.2f%n",
                    v.getPlaca(), v.getMarca(), v.getAno(), v.getValorParaVenda());
        }

        System.out.print("Placa do veiculo a vender: ");
        String placa = sc.nextLine();
        Veiculo v = veiculoController.buscarPorPlaca(placa);
        if (v == null || v.getEstado() != Estado.DISPONIVEL) {
            System.out.println("Veiculo nao disponivel para venda!");
            return;
        }

        locacaoController.venderVeiculo(v);
        System.out.println("Veiculo vendido com sucesso!");
    }

    // ====== METODOS DE LEITURA SEGURA ======
    private static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido, digite novamente: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido, digite novamente: ");
            }
        }
    }
}
