package idat.com.administracion.app.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import idat.com.administracion.app.Entidades.Mensaje;
import idat.com.administracion.app.Entidades.MensajeRecibir;
import idat.com.administracion.app.R;

public class Adaptermensajes extends RecyclerView.Adapter<HolderMensaje>  {

    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context c;


    public Adaptermensajes(Context c) {
        this.c = c;
    }

    public void addMensaje(MensajeRecibir m){
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }

    @NonNull
    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==1){
            view = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes_emisor,parent,false);
        }else {
            view = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes_receptor,parent,false);
        }
        return new HolderMensaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        Mensaje o = listMensaje.get(position);
        holder.getNombreperfil().setText(o.getNombre());
        //o.setUrlFoto("https://firebasestorage.googleapis.com/v0/b/administraciondeedificiosapp.appspot.com/o/imagenes_chat%2FIMG-20210325-WA0042.jpeg?alt=media&token=21a9c688-bc85-46a6-80e9-6ae4ed4bc7e9");
        holder.getMensaje().setText(o.getMensaje());
        if(o.getType_mensaje().equals("2")){
            holder.getFotomensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(o.getUrlFoto()).into(holder.getFotomensaje());
        }else if(o.getType_mensaje().equals("1")){
            holder.getFotomensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        Long codigohora = listMensaje.get(position).getHora();
        //Date d = new Date(codigohora);
        //SimpleDateFormat adf = new SimpleDateFormat("hh:mm a");
        //holder.getHora().setText(adf.format(d));

        Date d = new Date(codigohora);
        PrettyTime pt = new PrettyTime(new Date(), Locale.getDefault());
        holder.getHora().setText(pt.format(d));

    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }

    /*
    @Override
    public int getItemViewType(int position) {
        if(listMensaje.get(position).getlUsuario()!=null){
            if(listMensaje.get(position).getlUsuario().getKey().equals(UsuarioDAO.getInstancia().getKeyUsuario())){
                return 1;
            }else{
                return -1;
            }
        }else{
            return -1;
        }

    }*/
}
