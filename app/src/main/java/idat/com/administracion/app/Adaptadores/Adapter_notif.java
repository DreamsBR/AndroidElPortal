package idat.com.administracion.app.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idat.com.administracion.app.Entidades.Documento;
import idat.com.administracion.app.Entidades.Notificaciones;
import idat.com.administracion.app.R;

public class Adapter_notif extends RecyclerView.Adapter<Adapter_notif.ViewHolder> {

    private LayoutInflater inflater;
    private List<Notificaciones> notificaciones;

    //private View.OnClickListener listener;

    public Adapter_notif(Context context, List<Notificaciones> itemsList){
        this.inflater = LayoutInflater.from(context);
        this.notificaciones=itemsList;
    }

    @NonNull
    @Override
    public Adapter_notif.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.notificaciones, null);
        //view.setOnClickListener(this);
        return new Adapter_notif.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_notif.ViewHolder holder, int position) {
        holder.bindData(notificaciones.get(position));
    }

    //public void setOnClickLister(View.OnClickListener listener){
    // this.listener=listener;
    //}


    @Override
    public int getItemCount() {
        return notificaciones.size();
    }



    public void setItems(List<Notificaciones> items){
        notificaciones = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Context context;
        TextView grupo, hora;
        ImageView imagenid;

        ViewHolder( View itemView) {
            super(itemView);
            context= itemView.getContext();
            grupo=itemView.findViewById(R.id.nombre_not);
            hora=itemView.findViewById(R.id.hora_not);
            imagenid=itemView.findViewById(R.id.imagen_not);
        }

        void bindData(final Notificaciones item){
            int resID = context.getResources().getIdentifier(item.getImagenid() , "drawable", context.getPackageName());
            if (resID!=-1)
                imagenid.setImageResource(resID);

            grupo.setText(item.getGrupo());
            hora.setText(item.getHora());
        }
    }
}
