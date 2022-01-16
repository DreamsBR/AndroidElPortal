package idat.com.administracion.app.Entidades;

import java.io.Serializable;
import java.util.List;

public class Edificio implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id_edificio;
    private String nombre_edificios;
    private String direccion;
    private List<Departamentos> listaDepartamentos;

    public Edificio(String nombre_edificios, String direccion, List<Departamentos> listaDepartamentos) {
        this.nombre_edificios = nombre_edificios;
        this.direccion = direccion;
        this.listaDepartamentos = listaDepartamentos;
    }

    public String getNombre_edificios() {
        return nombre_edificios;
    }

    public void setNombre_edificios(String nombre_edificios) {
        this.nombre_edificios = nombre_edificios;
    }

    public int getId_edificio() {
        return id_edificio;
    }

    public void setId_edificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Departamentos> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Departamentos> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }


}
