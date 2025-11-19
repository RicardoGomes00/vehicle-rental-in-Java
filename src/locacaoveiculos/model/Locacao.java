/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.model;

import java.util.Calendar;


/**
 *
 * @author Ricardo Gomes
 */
public class Locacao {
    private int id;
    private final int dias;
    private final double valor;
    private final Calendar data;
    private final Cliente cliente;
    private final Veiculo veiculo;

    public Locacao(int dias, double valor, Calendar data, Cliente cliente, Veiculo veiculo) {
        this.id = 0;
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    // Getters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDias() { return dias; }
    public double getValor() { return valor; }
    public Calendar getData() { return data; }
    public Cliente getCliente() { return cliente; }
    public Veiculo getVeiculo() { return veiculo; }

    @Override
    public String toString() {
        return "Locacao de " + veiculo.getPlaca() + " para " +
               cliente.getNome() + " (" + dias + " dias, RS" + valor + ")";
    }
}