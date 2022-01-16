package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import idat.com.administracion.app.Adaptadores.Adapter_Disponible;
import idat.com.administracion.app.Entidades.Disponible;
import idat.com.administracion.app.R;
import idat.com.administracion.app.utils.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Depa_Disponible_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView2;
    Adapter_Disponible adapter_disponible;
    SearchView txtbuscar;
    ArrayList<Disponible> disponibles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depa_disponible);
        txtbuscar = findViewById(R.id.txtbuscar);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        adapter_disponible = new Adapter_Disponible();
        getAllDepartamento();
        initListener();
    }

    private void initListener(){
        txtbuscar.setOnQueryTextListener(this);
    }


    public void getAllDepartamento(){
        Call<List<Disponible>> departamentoList = Cliente.getDepartamentoService().getDisponible();

        departamentoList.enqueue(new Callback<List<Disponible>>() {
            @Override
            public void onResponse(Call<List<Disponible>> call, Response<List<Disponible>> response) {
                if(response.isSuccessful()){
                    List<Disponible> disponible = response.body();
                    adapter_disponible.setData(disponible);
                    recyclerView2.setAdapter(adapter_disponible);

                }
            }

            @Override
            public void onFailure(Call<List<Disponible>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter_disponible.filtrado(s);
        return false;
    }
}

