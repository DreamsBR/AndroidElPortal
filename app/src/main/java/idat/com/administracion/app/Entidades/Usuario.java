package idat.com.administracion.app.Entidades;


public class Usuario {
    private String nombre;
    private String correo;
    private String username;
    private String apellidos;
    private String edad;
    private String celular;
    private String dni;




    public Usuario() {}

    public Usuario(String nombre, String correo, String username, String apellidos, String edad, String celular, String dni) {
        this.nombre = nombre;
        this.correo = correo;
        this.username = username;
        this.apellidos = apellidos;
        this.edad = edad;
        this.celular = celular;
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}