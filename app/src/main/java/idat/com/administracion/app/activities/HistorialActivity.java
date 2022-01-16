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
import idat.com.administracion.app.Adaptadores.AdapterPago;
import idat.com.administracion.app.Entidades.Invitado;
import idat.com.administracion.app.Entidades.Pago;
import idat.com.administracion.app.R;
import idat.com.administracion.app.common.Constantes;
import idat.com.administracion.app.databinding.ActivityHistorialBinding;
import idat.com.administracion.app.databinding.ActivityListaInvitadosBinding;

public class HistorialActivity extends AppCompatActivity {

    private ActivityHistorialBinding binding;

    public static String numDepa;
    public static String numEdi;
    public static Integer idPago;
    private AdapterPago adapterPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistorialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        adapterPago = new AdapterPago(this);
        binding.rvPagos.setLayoutManager(
                new GridLayoutManager(HistorialActivity.this,1)
        );
        binding.rvPagos.setAdapter(adapterPago);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        numDepa=preferences.getString("numdepa","nada") ;
        numEdi=preferences.getString("numedi","nada") ;
        System.out.println(numDepa+" "+numEdi);

        obtenerInvitados(new Constantes().URL_LISTAR_PAGO_USUARIO+numDepa+"/"+numEdi);


    }

    private void obtenerInvitados(String url_lista_pago_usuario) {
        RequestQueue colapeticiones= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url_lista_pago_usuario,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Pago> miListaPago = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Pago nuevoPago = new Pago(
                                        jsonObject.getString("habitante"),
                                        jsonObject.getString("ccv"),
                                        jsonObject.getString("fecha_v"),
                                        jsonObject.getString("n_tarjeta"),
                                        jsonObject.getString("dni"),
                                        jsonObject.getString("celular"),
                                        jsonObject.getString("num_depa"),
                                        jsonObject.getString("num_edi"),
                                        jsonObject.getString("tipo_pago"),
                                        jsonObject.getDouble("monto_pagar")
                                );
                                miListaPago.add(nuevoPago);
                            }
                            adapterPago.agregarLista(miListaPago);
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