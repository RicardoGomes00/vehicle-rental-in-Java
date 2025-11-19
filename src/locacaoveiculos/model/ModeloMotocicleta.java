/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaoveiculos.model;

/**
 *
 * @author Ricardo Gomes
 */
public enum ModeloMotocicleta {
    CG125("CG 125"),
    CBR500("CBR 500");

    private final String descricao;

    ModeloMotocicleta(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}