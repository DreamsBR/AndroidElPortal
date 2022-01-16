package idat.com.administracion.app.Entidades;

import java.io.Serializable;

public class Disponible implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id_departamento;
    private String descripcion;
    private Double precio;
    private String foto;
    private Edificio edificio;

    public Disponible(String descripcion, Double precio, String foto, Edificio edificio) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Disponible{" +
                "id_departamento=" + id_departamento +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", foto='" + foto + '\'' +
                ", edificio=" + edificio.getNombre_edificios()+
                '}';
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
}
