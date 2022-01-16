package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import idat.com.administracion.app.Adaptadores.AdapterInvitados;
import idat.com.administracion.app.Adaptadores.AdapterReserva;
import idat.com.administracion.app.Entidades.Invitado;
import idat.com.administracion.app.Entidades.ReservasZonas;
import idat.com.administracion.app.R;
import idat.com.administracion.app.common.Constantes;
import idat.com.administracion.app.databinding.ActivityListaInvitadosBinding;
import idat.com.administracion.app.databinding.ActivityListaReservaBinding;

public class ListaReservaActivity extends AppCompatActivity {

    private ActivityListaReservaBinding binding;
    private AdapterReserva adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaReservaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        adapter = new AdapterReserva(this);
        binding.rvreservas.setLayoutManager(
                new GridLayoutManager(ListaReservaActivity.this,1)
        );
        binding.rvreservas.setAdapter(adapter);

        obtenerInvitados(new Constantes().URL_LISTAR_RESERVAS);
    }

    private void obtenerInvitados(String url_listar_RESERVAS) {
        RequestQueue colapeticiones= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url_listar_RESERVAS,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<ReservasZonas> milistareservas = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                ReservasZonas nuevaReserva = new ReservasZonas(
                                        jsonObject.getString("habitante"),
                                        jsonObject.getString("celular"),
                                        jsonObject.getString("motivo"),
                                        jsonObject.getString("zona_comun"),
                                        jsonObject.getString("fecha"),
                                        jsonObject.getString("hora"),
                                        jsonObject.getString("fotoReserva")
                                );
                                milistareservas.add(nuevaReserva);
                            }
                            adapter.agregarLista(milistareservas);
                        } catch (JSONException ex) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        colapeticiones.add(jsonArrayRequest);
    }
}