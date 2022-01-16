package idat.com.administracion.app.fragmentos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
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
import idat.com.administracion.app.activities.ListaReservaActivity;
import idat.com.administracion.app.common.Constantes;

public class ReservarZonasComunesFragment extends Fragment implements View.OnClickListener {

    Button btnfecha, btnhora, btnreservar;
    EditText efecha, ehora, edtnombre, edtapellidos, edtcelular, edtmotivo ;
    RadioButton rbgimnasio, rbauditorio, rbsalon;
    String zona ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    private int dia,mes,ano,hor,min;

    @Override
    public void onClick(View v){
        if(v==btnfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                }
            }
                    ,ano,mes,dia);
            datePickerDialog.show();
        }

        if(v==btnhora){
            final Calendar c= Calendar.getInstance();
            hor=c.get(Calendar.HOUR_OF_DAY);
            min=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":"+minute);
                }
            }
                    ,hor,min,false);
            timePickerDialog.show();
        }


        switch (v.getId()){
            case R.id.idSalonJuegos: zona="Zona de juegos";break;
            case R.id.idGimnasio: zona="Gimanasio";break;
            case R.id.idAuditorio: zona="Auditorio";break;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservar_zonas_comunes,container,false);
        btnfecha=view.findViewById(R.id.fecha);
        btnhora=view.findViewById(R.id.hora);
        efecha= view.findViewById(R.id.efecha);
        ehora= view.findViewById(R.id.ehora);
        edtcelular = view.findViewById(R.id.celular);
        edtnombre= view.findViewById(R.id.nombres);
        edtapellidos = view.findViewById(R.id.apellidos);
        edtmotivo = view.findViewById(R.id.cajatexto);
        rbsalon = view.findViewById(R.id.idSalonJuegos);
        rbauditorio = view.findViewById(R.id.idAuditorio);
        rbgimnasio = view.findViewById(R.id.idGimnasio);
        btnreservar = view.findViewById(R.id.btnreservar);

        btnfecha.setOnClickListener(this);
        btnhora.setOnClickListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        edtapellidos.setText(preferences.getString("usuarioApePat","nada")+" "+preferences.getString("usuarioApeMat","nada"));
        edtnombre.setText(preferences.getString("usuarioNombre","nada"));
        edtcelular.setText(preferences.getString("numcelular","nada"));


        btnreservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                Toast.makeText(getContext(), "fecha: "+efecha.getText()+"hora:"+ehora.getText()+"edtcelular: "+edtcelular.getText()+"edtnombre:"+edtnombre.getText(), Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "edtapellidos: "+edtapellidos.getText()+"motivo: "+edtmotivo.getText(), Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "rbsalon: "+rbsalon.getText()+"rbauditorio: "+rbauditorio.getText()+"rbgimnasio: "+rbgimnasio.getText(), Toast.LENGTH_LONG).show();
                System.out.println(zona);
                insertarReserva();
            }
        });

        return view;
    }

    private void insertarReserva() {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        Map<String, String> parametros = new HashMap<>();
        parametros.put("habitante",edtnombre.getText().toString()+" "+edtapellidos.getText().toString());
        parametros.put("celular",edtcelular.getText().toString());
        parametros.put("motivo",edtmotivo.getText().toString());
        parametros.put("zona_comun",zona);
        parametros.put("fecha",efecha.getText().toString());
        parametros.put("hora",ehora.getText().toString());
        JSONObject jsonObjectParametro = new JSONObject(parametros);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_POST_RESERVA,
                jsonObjectParametro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        startActivity(new Intent(getActivity(), ListaReservaActivity.class));
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
}