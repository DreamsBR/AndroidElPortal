package idat.com.administracion.app.Entidades;

import java.io.Serializable;

public class Habitante {

    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDoc;
    private String numDoc;
    private String numEdificio;
    private String numDepartamento;
    private String provincia;
    private String distrito;
    private String telefono;
    private String direccion;
    private String ccv;
    private String fecha_v;
    private String n_tarjeta;
    private DocumentoAlmacenado foto;

    public Habitante (int id){
        this.id=id;
    }

    public Habitante (){
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getTipoDoc() {
        return tipoDoc;
    }
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    public String getNumDoc() {
        return numDoc;
    }
    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
    public String getNumEdificio() {
        return numEdificio;
    }
    public void setNumEdificio(String numEdificio) {
        this.numEdificio = numEdificio;
    }
    public String getNumDepartamento() {
        return numDepartamento;
    }
    public void setNumDepartamento(String numDepartamento) {
        this.numDepartamento = numDepartamento;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getDistrito() {
        return distrito;
    }
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public DocumentoAlmacenado getFoto() {
        return foto;
    }
    public void setFoto(DocumentoAlmacenado foto) {
        this.foto = foto;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getFecha_v() {
        return fecha_v;
    }

    public void setFecha_v(String fecha_v) {
        this.fecha_v = fecha_v;
    }

    public String getN_tarjeta() {
        return n_tarjeta;
    }

    public void setN_tarjeta(String n_tarjeta) {
        this.n_tarjeta = n_tarjeta;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
