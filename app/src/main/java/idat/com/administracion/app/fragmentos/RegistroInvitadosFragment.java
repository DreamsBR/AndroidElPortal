package idat.com.administracion.app.fragmentos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import idat.com.administracion.app.R;
import idat.com.administracion.app.activities.ListaInvitadosActivity;
import idat.com.administracion.app.common.Constantes;
import idat.com.administracion.app.databinding.FragmentRegistroInvitadosBinding;

public class RegistroInvitadosFragment extends Fragment {

    private FragmentRegistroInvitadosBinding binding;

    private int dia,mes,ano;

    Button pasarregistro, cancelarRegistro;
    EditText edtnombre, edtapellidos, edtfechaingreso, edtautoriza, edtnumdepa, edtnumedi;

    public static RegistroInvitadosFragment newInstance(String param1, String param2) {
        RegistroInvitadosFragment fragment = new RegistroInvitadosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroInvitadosBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        pasarregistro= binding.idregistrarinvitado;
        cancelarRegistro = binding.idcancelarregistro;

        edtnombre = binding.txtnombreinvitado;
        edtapellidos = binding.txtapellidoinvitado;
        edtautoriza = binding.txtautorizadopor;
        edtfechaingreso = binding.txtfechaingreso;
        edtnumdepa = binding.txtnumerodepa;
        edtnumedi = binding.txtnumeroedi;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        edtautoriza.setText(preferences.getString("usuario","nada"));
        edtnumdepa.setText(preferences.getString("numdepa","nada"));
        edtnumedi.setText(preferences.getString("numedi","nada"));
        edtautoriza.setEnabled(false);
        edtnumdepa.setEnabled(false);
        edtnumedi.setEnabled(false);

        edtfechaingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==edtfechaingreso){
                    final Calendar c= Calendar.getInstance();
                    dia=c.get(Calendar.DAY_OF_MONTH);
                    mes=c.get(Calendar.MONTH);
                    ano=c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edtfechaingreso.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }
                            ,ano,mes,dia);
                    datePickerDialog.show();
                }
            }
        });

        pasarregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                Intent intent = new Intent(getActivity(), ListaInvitadosActivity.class);
                ListaInvitadosActivity.numEdi=null;
                ListaInvitadosActivity.numDepa=null;
                startActivity(intent);
                if (verificar()){
                    insertarInvitado();
                    Toast.makeText(getContext(),"Invitado registrado",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }else{
                    Toast.makeText(getContext(),"Completa los campos",Toast.LENGTH_LONG).show();
                }


            }
        });

        cancelarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                HomeFragment homeFragment = new HomeFragment();
                manager.beginTransaction().replace(R.id.fragmentoRegistro, homeFragment).commit();
            }
        });



        return view;
    }

    private void insertarInvitado() {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        Map<String, String> parametros = new HashMap<>();
        parametros.put("nombre",binding.txtnombreinvitado.getText().toString());
        parametros.put("apellidos",binding.txtapellidoinvitado.getText().toString());
        parametros.put("fecha_ingreso",binding.txtfechaingreso.getText().toString());
        parametros.put("autoriza",binding.txtautorizadopor.getText().toString());
        parametros.put("num_depa",binding.txtnumerodepa.getText().toString());
        parametros.put("num_edi",binding.txtnumeroedi.getText().toString());
        JSONObject jsonObjectParametro = new JSONObject(parametros);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_POST_INVI,
                jsonObjectParametro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        startActivity(new Intent(getActivity(),ListaInvitadosActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        colaPeticiones.add(request);
    }

    private void limpiarCajas() {
        edtnumdepa.setText("");
        edtfechaingreso.setText("");
        edtautoriza.setText("");
        edtapellidos.setText("");
        edtnombre.setText("");
        edtnumedi.setText("");
    }

    public Boolean verificar(){
        if (edtnombre.getText().toString().equals("") ||
            edtapellidos.getText().toString().equals("") ||
            edtautoriza.getText().toString().equals("") ||
            edtfechaingreso.getText().toString().equals("") ||
            edtnumdepa.getText().toString().equals("") ||
                edtnumedi.getText().toString().equals("")    ) {
            return  false;
        }else{
            return true;
        }

    }
}