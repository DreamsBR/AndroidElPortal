package idat.com.administracion.app.Entidades;

public class ReservasZonas {
    private Integer idReserva;

    private String habitanteReserva;

    private String celularReserva;

    private String motivoReserva;

    private String zona_comunReserva;

    private String fechaReserva;

    private String horaReserva;

    private String fotoReserva;

    public ReservasZonas(String habitanteReserva, String celularReserva, String motivoReserva, String zona_comunReserva, String fechaReserva, String horaReserva, String fotoReserva) {
        this.habitanteReserva = habitanteReserva;
        this.celularReserva = celularReserva;
        this.motivoReserva = motivoReserva;
        this.zona_comunReserva = zona_comunReserva;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.fotoReserva = fotoReserva;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getHabitanteReserva() {
        return habitanteReserva;
    }

    public void setHabitanteReserva(String habitanteReserva) {
        this.habitanteReserva = habitanteReserva;
    }

    public String getCelularReserva() {
        return celularReserva;
    }

    public void setCelularReserva(String celularReserva) {
        this.celularReserva = celularReserva;
    }

    public String getMotivoReserva() {
        return motivoReserva;
    }

    public void setMotivoReserva(String motivoReserva) {
        this.motivoReserva = motivoReserva;
    }

    public String getZona_comunReserva() {
        return zona_comunReserva;
    }

    public void setZona_comunReserva(String zona_comunReserva) {
        this.zona_comunReserva = zona_comunReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public String getFotoReserva() {
        return fotoReserva;
    }

    public void setFotoReserva(String fotoReserva) {
        this.fotoReserva = fotoReserva;
    }
}
