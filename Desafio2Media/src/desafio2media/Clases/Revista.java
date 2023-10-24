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
    public String toString() {
        return "Revista{" + "periodicidad=" + periodicidad + '}';
    }

    
}
