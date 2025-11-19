/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.model;

/**
 *
 * @author Ricardo Gomes
 */
public enum Marca {
    VW("VW"),
    GM("GM"),
    FIAT("FIAT"),
    HONDA("HONDA"),
    MERCEDES("MERCEDES");

    private final String descricao;

    Marca(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}