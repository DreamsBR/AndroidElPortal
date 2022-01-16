package idat.com.administracion.app.Entidades;

import java.util.Date;

public class Invitado {

    private Integer id_invitados;
    private String nombre_invitado;
    private String apellidos_invitado;
    private String fecha_ingreso;
    private String autoriza;
    private Integer num_depa;
    private String num_edi;
    private String urlFoto;

    public Invitado(String nombre_invitado, String apellidos_invitado, String fecha_ingreso, String autoriza,
                     Integer num_depa, String num_edi, String urlFoto) {
        super();
        this.nombre_invitado = nombre_invitado;
        this.apellidos_invitado = apellidos_invitado;
        this.fecha_ingreso = fecha_ingreso;
        this.autoriza = autoriza;
        this.num_depa = num_depa;
        this.num_edi = num_edi;
        this.urlFoto = urlFoto;
    }



    public Integer getId_invitados() {
        return id_invitados;
    }

    public void setId_invitados(Integer id_invitados) {
        this.id_invitados = id_invitados;
    }

    public String getNombre_invitado() {
        return nombre_invitado;
    }

    public void setNombre_invitado(String nombre_invitado) {
        this.nombre_invitado = nombre_invitado;
    }

    public String getApellidos_invitado() {
        return apellidos_invitado;
    }

    public void setApellidos_invitado(String apellidos_invitado) {
        this.apellidos_invitado = apellidos_invitado;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(String autoriza) {
        this.autoriza = autoriza;
    }

    public Integer getNum_depa() {
        return num_depa;
    }

    public void setNum_depa(Integer num_depa) {
        this.num_depa = num_depa;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNum_edi() {
        return num_edi;
    }

    public void setNum_edi(String num_edi) {
        this.num_edi = num_edi;
    }
}
