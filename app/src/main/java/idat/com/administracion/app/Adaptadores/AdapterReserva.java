package idat.com.administracion.app.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import idat.com.administracion.app.Entidades.ReservasZonas;
import idat.com.administracion.app.databinding.ItemReservaBinding;

public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.ViewHolder>{

    private ArrayList<ReservasZonas> datareservas;
    private Context context;

    public AdapterReserva(Context context){
        this.context = context;
        datareservas = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemReservaBinding recyclerBinding = ItemReservaBinding.inflate(layoutInflater, parent,
                        false);
        return new AdapterReserva.ViewHolder(recyclerBinding);
    }

    public void agregarLista(ArrayList<ReservasZonas> listaReservas){
        datareservas.addAll(listaReservas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReserva.ViewHolder holder, int position) {
        final ReservasZonas objReserva = datareservas.get(position);
        holder.recyclerBinding.nombresHabitante.setText(objReserva.getHabitanteReserva());
        holder.recyclerBinding.idcelularreserva.setText("Celular: "+objReserva.getCelularReserva());
        holder.recyclerBinding.idfechareserva.setText("Fecha: "+objReserva.getFechaReserva());
        holder.recyclerBinding.idhorareserva.setText("Hora: "+objReserva.getHoraReserva());
        holder.recyclerBinding.idmotivoreserva.setText("Motivo: "+objReserva.getMotivoReserva());
        Glide.with(context)
                .load(objReserva.getFotoReserva())
                .into(holder.recyclerBinding.imageninvitado);
    }

    @Override
    public int getItemCount() {
        return datareservas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemReservaBinding recyclerBinding;
        public ViewHolder(@NonNull ItemReservaBinding itemView) {
            super(itemView.getRoot());
            recyclerBinding = itemView;
        }
    }
}
