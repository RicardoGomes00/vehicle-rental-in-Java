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
public abstract class Veiculo implements VeiculoI {
    protected Marca marca;
    protected Estado estado;
    protected Categoria categoria;
    protected Locacao locacao;
    protected double valorDeCompra;
    protected String placa;
    protected int ano;

    public Veiculo(Marca marca, Estado estado, Categoria categoria,
                   double valorDeCompra, String placa, int ano) {
        this.marca = marca;
        this.estado = estado;
        this.categoria = categoria;
        this.valorDeCompra = valorDeCompra;
        this.placa = placa;
        this.ano = ano;
        this.locacao = null;
    }

    @Override
    public void locar(int dias, Calendar data, Cliente cliente) {
        if (estado != Estado.DISPONIVEL) {
            System.out.println("Veiculo nao esta disponivel para locacao!");
            return;
        }

        double valorLocacao = dias * getValorDiariaLocacao();
        this.locacao = new Locacao(dias, valorLocacao, data, cliente, this);
        this.estado = Estado.LOCADO;
        System.out.println("Veiculo " + placa + " locado com sucesso!");
    }

    @Override
    public void devolver() {
        if (estado != Estado.LOCADO) {
            System.out.println("Veiculo nao esta locado!");
            return;
        }

        this.locacao = null;
        this.estado = Estado.DISPONIVEL;
        System.out.println("Veiculo " + placa + " devolvido com sucesso!");
    }

    @Override
    public void vender() {
        if (estado == Estado.LOCADO) {
            System.out.println("Veiculo nao pode ser vendido enquanto estiver locado!");
            return;
        }

        this.estado = Estado.VENDIDO;
        System.out.println("Veiculo " + placa + " vendido com sucesso!");
    }

    @Override
    public double getValorParaVenda() {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int idade = anoAtual - ano;

        double valorVenda = valorDeCompra - (idade * 0.15 * valorDeCompra);
        if (valorVenda < valorDeCompra * 0.1) {
            valorVenda = valorDeCompra * 0.1;
        }

        return valorVenda;
    }

    // Getters
    @Override
    public Estado getEstado() { return estado; }
    @Override
    public Marca getMarca() { return marca; }
    @Override
    public Categoria getCategoria() { return categoria; }
    @Override
    public Locacao getLocacao() { return locacao; }
    @Override
    public String getPlaca() { return placa; }
    @Override
    public int getAno() { return ano; }

    @Override
    public String toString() {
        return placa + " - " + marca + " (" + categoria + ") [" + estado + "]";
    }
}