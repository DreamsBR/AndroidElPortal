package idat.com.administracion.app.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import idat.com.administracion.app.Entidades.Disponible;
import idat.com.administracion.app.Entidades.Invitado;
import idat.com.administracion.app.Entidades.Pago;
import idat.com.administracion.app.R;
import idat.com.administracion.app.activities.HistorialActivity;
import idat.com.administracion.app.activities.ListaInvitadosActivity;
import idat.com.administracion.app.databinding.HistorialPagoBinding;
import idat.com.administracion.app.databinding.ItemInvitadoBinding;


public class AdapterPago extends RecyclerView.Adapter<AdapterPago.ViewHolder> {

    private ArrayList<Pago> pagos;
    private Context context;

    public AdapterPago(Context context) {
        this.context = context;
        pagos = new ArrayList<>();
    }

    @NotNull
    @Override
    public AdapterPago.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HistorialPagoBinding recyclerBinding =
                HistorialPagoBinding.inflate(layoutInflater, parent,
                        false);
        return new AdapterPago.ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPago.ViewHolder holder, int position) {
        final Pago pago = pagos.get(position);
        System.out.println(pago.getId_pago());
        HistorialActivity.idPago=null;
        holder.recyclerBinding.txtccv.setText(pago.getCcv());
        holder.recyclerBinding.txtfechapago.setText(pago.getFecha_v());
        holder.recyclerBinding.txttarjeta.setText(pago.getN_tarjeta());
        holder.recyclerBinding.txtmonto.setText(pago.getMonto_pagar().toString());

    }

    public void agregarLista(ArrayList<Pago> miListaPago){
        pagos.addAll(miListaPago);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HistorialPagoBinding recyclerBinding;
        public ViewHolder(@NonNull HistorialPagoBinding itemView) {
                super(itemView.getRoot());
            recyclerBinding = itemView;

        }
    }
}
