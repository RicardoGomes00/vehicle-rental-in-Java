/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.model;

/**
 *
 * @author Ricardo Gomes
 */
public class Van extends Veiculo {
    private ModeloVan modelo;

    public Van(Marca marca, Estado estado, Categoria categoria,
               double valorDeCompra, String placa, int ano, ModeloVan modelo) {
        super(marca, estado, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    public ModeloVan getModelo() { return modelo; }

    @Override
    public double getValorDiariaLocacao() {
        return switch (categoria) {
            case POPULAR -> 200.0;
            case INTERMEDIARIO -> 400.0;
            case LUXO -> 600.0;
        };
    }

    @Override
    public String toString() {
        return "Van " + modelo + " - " + super.toString();
    }
}