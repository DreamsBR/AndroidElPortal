package idat.com.administracion.app.Entidades;

public class Documento {
    private String imagenid;
    private String Tipo;
    private String Fecha;


    public Documento() {
    }

    public Documento(String tipo, String fecha, String imagenid) {
        Tipo = tipo;
        Fecha = fecha;
        this.imagenid = imagenid;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getImagenid() {
        return imagenid;
    }

    public void setImagenid(String imagenid) {
        this.imagenid = imagenid;
    }
}
