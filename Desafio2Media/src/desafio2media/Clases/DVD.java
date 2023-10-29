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
public class DVD extends MaterialAudiovisual {

    private int idDirector;

    public DVD(String id, String titulo, int idGenero, int stock, int duracion, int idDirector) {
        super(id, titulo, idGenero, stock, duracion);
        this.idDirector = idDirector;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    @Override
    public int getDuracion() {
        return duracion;
    }

    @Override
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int getIdGenero() {
        return idGenero;
    }

    @Override
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "DVD{" + "idDirector=" + idDirector + '}';
    }

}
