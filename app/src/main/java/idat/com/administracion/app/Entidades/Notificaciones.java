package idat.com.administracion.app.Entidades;

public class Notificaciones {

    private String Grupo;
    private String Hora;
    private String imagenid;


    public Notificaciones() {
    }

    public Notificaciones(String grupo, String hora, String imagenid) {
        Grupo = grupo;
        Hora = hora;
        this.imagenid = imagenid;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getImagenid() {
        return imagenid;
    }

    public void setImagenid(String imagenid) {
        this.imagenid = imagenid;
    }
}
