package idat.com.administracion.app.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import idat.com.administracion.app.Entidades.Invitado;
import idat.com.administracion.app.activities.ListaInvitadosActivity;
import idat.com.administracion.app.databinding.ItemInvitadoBinding;

public class AdapterInvitados extends RecyclerView.Adapter<AdapterInvitados.ViewHolder> {

    private ArrayList<Invitado> dataInvitados;
    private Context context;

    public AdapterInvitados(Context context){
        this.context = context;
        dataInvitados = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemInvitadoBinding recyclerBinding =
                ItemInvitadoBinding.inflate(layoutInflater, parent,
                        false);
        return new ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Invitado objInvitado = dataInvitados.get(position);
        System.out.println(objInvitado.getId_invitados());
        ListaInvitadosActivity.invitadoid=null;
        holder.recyclerBinding.nombresInvitado.setText(objInvitado.getNombre_invitado()+" "+objInvitado.getApellidos_invitado());
        holder.recyclerBinding.idfechaingreso.setText("Fecha ingreso: "+objInvitado.getFecha_ingreso());
        holder.recyclerBinding.idautorizadopor.setText("Autoriza: "+objInvitado.getAutoriza());
        holder.recyclerBinding.iddepartamento.setText("Nº departamento: "+objInvitado.getNum_depa().toString());
        holder.recyclerBinding.idnumedificio.setText("Nº edificio: "+objInvitado.getNum_edi());
        Glide.with(context)
                .load(objInvitado.getUrlFoto())
                .into(holder.recyclerBinding.imageninvitado);
    }

    public void agregarLista(ArrayList<Invitado> listaInvitados){
        dataInvitados.addAll(listaInvitados);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataInvitados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemInvitadoBinding recyclerBinding;
        public ViewHolder(@NonNull ItemInvitadoBinding itemView) {
            super(itemView.getRoot());
            recyclerBinding = itemView;
        }
    }
}
