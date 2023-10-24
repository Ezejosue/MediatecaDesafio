/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2media.Clases;

/**
 *
 * @author Josue
 */
public abstract class Material {

    protected String id;
    protected String titulo;
    protected int idGenero;
    protected int stock;

    public Material(String id, String titulo, int idGenero, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.idGenero = idGenero;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
    @Override
    public String toString() {
        return "Material{"
                + "id='" + id + '\''
                + ", titulo='" + titulo + '\''
                + ", idGenero=" + idGenero
                + ", stock=" + stock
                + '}';
    }

}
