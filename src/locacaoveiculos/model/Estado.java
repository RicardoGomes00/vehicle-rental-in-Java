/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.model;

/**
 *
 * @author Ricardo Gomes
 */
public enum Estado {
    NOVO("Novo"),
    LOCADO("Locado"),
    DISPONIVEL("Disponivel"),
    VENDIDO("Vendido");

    private final String descricao;

    Estado(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}