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
public class Revista extends MaterialEscrito {

    private String periodicidad;

    public Revista(String id, String titulo, int idGenero, int stock, int idAutor, int idEditorial, String periodicidad) {
        super(id, titulo, idGenero, stock, idAutor, idEditorial);
        this.periodicidad = periodicidad;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    @Override
    public int getIdAutor() {
        return idAutor;
    }

    @Override
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public int getIdEditorial() {
        return idEditorial;
    }

    @Override
    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
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
        return "Revista{" + "periodicidad=" + periodicidad + '}';
    }

}
