package idat.com.administracion.app.Adaptadores;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import idat.com.administracion.app.R;

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView nombreperfil;
    private TextView mensaje;
    private TextView hora;
    private CircleImageView fotomensajeperfil;
    private ImageView fotomensaje;

    public HolderMensaje(@NonNull View itemView) {
        super(itemView);
        nombreperfil = (TextView) itemView.findViewById(R.id.nombreperfilmensaje);
        mensaje = (TextView) itemView.findViewById(R.id.mensajemensaje);
        hora = (TextView) itemView.findViewById(R.id.horamensaje);
        fotomensajeperfil = (CircleImageView) itemView.findViewById(R.id.fotoperfilmensaje);
        fotomensaje = (ImageView) itemView.findViewById(R.id.mensajefoto);
    }

    public TextView getNombreperfil() {
        return nombreperfil;
    }

    public void setNombreperfil(TextView nombreperfil) {
        this.nombreperfil = nombreperfil;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public CircleImageView getFotomensajeperfil() {
        return fotomensajeperfil;
    }

    public void setFotomensajeperfil(CircleImageView fotomensajeperfil) { this.fotomensajeperfil = fotomensajeperfil; }

    public ImageView getFotomensaje() { return fotomensaje; }

    public void setFotomensaje(ImageView fotomensaje) { this.fotomensaje = fotomensaje; }
}
