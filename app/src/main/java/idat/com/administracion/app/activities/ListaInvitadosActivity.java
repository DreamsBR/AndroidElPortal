package idat.com.administracion.app.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import idat.com.administracion.app.Entidades.Invitado;
import idat.com.administracion.app.common.Constantes;
import idat.com.administracion.app.databinding.ActivityListaInvitadosBinding;

public class ListaInvitadosActivity extends AppCompatActivity {

    private ActivityListaInvitadosBinding binding;
    private AdapterInvitados adapter;
    public static String numDepa;
    public static String numEdi;
    public static Integer invitadoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaInvitadosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        adapter = new AdapterInvitados(this);
        binding.rvinvitados.setLayoutManager(
                new GridLayoutManager(ListaInvitadosActivity.this,1)
        );
        binding.rvinvitados.setAdapter(adapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        numDepa=preferences.getString("numdepa","nada") ;
        numEdi=preferences.getString("numedi","nada") ;
        System.out.println(numDepa+" "+numEdi);

        if (numDepa==null&&numEdi==null)
            obtenerInvitados(new Constantes().URL_LISTAR_INVI); ///para obtener lista de invitados general
        else
            obtenerInvitados(new Constantes().URL_LISTAR_INVI_FILTRO+numDepa+"/"+numEdi);



    }

    private void obtenerInvitados(String url_listar_invi) {
        RequestQueue colapeticiones= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url_listar_invi,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Invitado> miListaInvitados = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Invitado nuevoInvitado = new Invitado(
                                    jsonObject.getString("nombre"),
                                        jsonObject.getString("apellidos"),
                                        jsonObject.getString("fecha_ingreso"),
                                        jsonObject.getString("autoriza"),
                                        jsonObject.getInt("num_depa"),
                                        jsonObject.getString("num_edi"),
                                        jsonObject.getString("urlFoto")
                                );
                                miListaInvitados.add(nuevoInvitado);
                            }
                            adapter.agregarLista(miListaInvitados);
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