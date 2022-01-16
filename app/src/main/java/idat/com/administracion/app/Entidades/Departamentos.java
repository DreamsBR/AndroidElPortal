package idat.com.administracion.app.Entidades;

import java.io.Serializable;

public class Departamentos implements Serializable {

    private int id_departamento;
    private String descripcion;
    private Double precio;
    private String estado;
    private String foto;

    private Edificio edificio;


    public Departamentos(String descripcion, Double precio,
                         String estado, String foto, Edificio edificio) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.foto = foto;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Departamentos{" +
                "id_departamento=" + id_departamento +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

