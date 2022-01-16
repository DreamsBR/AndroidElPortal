package idat.com.administracion.app.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import idat.com.administracion.app.Entidades.Disponible;
import idat.com.administracion.app.R;
import idat.com.administracion.app.activities.Detalle_Departamento;


public class Adapter_Disponible extends
            RecyclerView.Adapter<Adapter_Disponible.Adapter_DisponibleVH>{

    private List<Disponible> disponibles;
    private List<Disponible> originalDisponible;
    private Context context;

    public Adapter_Disponible() {
        this.originalDisponible = new ArrayList<>();
    }

    public void setData(List<Disponible> disponibles){
        this.disponibles = disponibles;
        originalDisponible = new ArrayList<>();
        originalDisponible.addAll(disponibles);


    }

    @NotNull
    @Override
    public Adapter_DisponibleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Adapter_Disponible.Adapter_DisponibleVH(LayoutInflater.from(context)
                .inflate(R.layout.lista_disponible,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_DisponibleVH holder, int position) {
        Disponible disponible = disponibles.get(position);
        holder.tvEdificio_disponible.setText(disponible.getEdificio().getNombre_edificios());

        Glide.with(context)
                .load(disponible.getFoto())
                .into(holder.imagendepartamento_diponible);

        holder.ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),
                        Detalle_Departamento.class);
                intent.putExtra("disponibleList", disponible);
                holder.itemView.getContext().startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        return disponibles.size();
    }

    public void filtrado(String txtbuscar){
        int longitud=txtbuscar.length();
        if(longitud == 0){
            disponibles.clear();
            disponibles.addAll(originalDisponible);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Disponible> collect = disponibles.stream().filter(i -> i.getEdificio().getNombre_edificios().toLowerCase()
                        .contains(txtbuscar.toLowerCase())).collect(Collectors.toList());
                disponibles.clear();
                disponibles.addAll(collect);
            }else{
                disponibles.clear();
                for(Disponible d: originalDisponible){
                    if(d.getEdificio().getNombre_edificios().toLowerCase().contains(txtbuscar.toLowerCase())){
                        disponibles.add(d);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }

    public class Adapter_DisponibleVH extends RecyclerView.ViewHolder {

        private ImageView ir;
        private ImageView imagendepartamento_diponible;
        private TextView tvEdificio_disponible;


        public Adapter_DisponibleVH(@NonNull View itemView) {
            super(itemView);
            ir = itemView.findViewById(R.id.ir);
            imagendepartamento_diponible = itemView.findViewById(R.id.imagendepartamento_diponible);
            tvEdificio_disponible = itemView.findViewById(R.id.tvEdificio_disponible);

        }
    }
}
