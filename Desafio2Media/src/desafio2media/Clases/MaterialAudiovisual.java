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
public abstract class MaterialAudiovisual extends Material {

    protected int duracion; // en minutos

    public MaterialAudiovisual(String id, String titulo, int idGenero, int stock, int duracion) {
        super(id, titulo, idGenero, stock);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "MaterialAudiovisual{" + "duracion=" + duracion + '}';
    }
    
    
    

}
