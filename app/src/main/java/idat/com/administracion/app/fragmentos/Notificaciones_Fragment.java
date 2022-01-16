package idat.com.administracion.app.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import idat.com.administracion.app.Adaptadores.Adapter_notif;
import idat.com.administracion.app.Entidades.Notificaciones;
import idat.com.administracion.app.R;


public class Notificaciones_Fragment extends Fragment {

    Adapter_notif adapter_notif;
    RecyclerView recyclerView;
    List<Notificaciones> notificaciones;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @NonNull
            Bundle savedInstanceState ) {
        View view =inflater.inflate(R.layout.fragment_notificaciones_,container, false);
        recyclerView=view.findViewById(R.id.rvdepartamentos);
        notificaciones=new ArrayList<>();

        cargarLista();
        // Inflate the layout for this fragment
        mostrarData();
        return view;
    }

    public void cargarLista(){
        notificaciones.add(new Notificaciones("Comite 36 a realizado una publicacion","Hace una hora","not2"));
        notificaciones.add(new Notificaciones("Valeria Chavez a reacionado a tu publicacion","Hace dos horas","not3"));
        notificaciones.add(new Notificaciones("Hay una publicacion popular en Junta Vecinal","Hace seis horas","not1"));
        notificaciones.add(new Notificaciones("Jose Quispe publico en Comite 36","Hace 30 minutos","not4"));

    }

    public void mostrarData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_notif=new Adapter_notif(getContext(),notificaciones);
        recyclerView.setAdapter(adapter_notif);



    }
}