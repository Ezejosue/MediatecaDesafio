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
public abstract class MaterialEscrito extends Material {

    protected int idAutor;
    protected int idEditorial;
    
    public MaterialEscrito(String id, String titulo, int idGenero, int stock, int idAutor, int idEditorial) {
        super(id, titulo, idGenero, stock);
        this.idAutor = idAutor;
        this.idEditorial = idEditorial;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }


    @Override
    public String toString() {
        return "MaterialEscrito{" + "idAutor=" + idAutor + ", idEditorial=" + idEditorial + '}';
    }
    
    

}
