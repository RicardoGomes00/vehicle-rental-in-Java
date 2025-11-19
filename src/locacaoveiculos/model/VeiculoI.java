/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locacaoveiculos.model;

import java.util.Calendar;


/**
 *
 * @author Ricardo Gomes
 */
public interface VeiculoI {

    // Muda estado para LOCADO. Cria uma instância de Locacao e armazena no atributo locacao.
    // Chama o método getValorDiariaLocacao para calcular o valor da locação.
    void locar(int dias, Calendar data, Cliente cliente);

    // Muda estado para VENDIDO e não pode mais ser alugado.
    void vender();

    // Muda estado para DISPONIVEL e finaliza a locação.
    void devolver();

    Estado getEstado();
    Marca getMarca();
    Categoria getCategoria();
    Locacao getLocacao();
    String getPlaca();
    int getAno();

    // Método que calcula um valor para venda.
    // valorParaVenda = valorDeCompra – idadeVeiculoEmAnos * 0.15 * valorDeCompra
    // Se o resultado for menor do que 10% do valorDeCompra ou negativo,
    // valorParaVenda = valorDeCompra * 0.1
    double getValorParaVenda();

    // Método que será abstrato na classe Veiculo
    double getValorDiariaLocacao();
}
