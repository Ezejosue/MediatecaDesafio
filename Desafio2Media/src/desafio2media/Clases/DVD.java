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
    public String toString() {
        return "DVD{" + "idDirector=" + idDirector + '}';
    }
    
    

}
