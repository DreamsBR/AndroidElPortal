package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import idat.com.administracion.app.databinding.ActivityMiDepartamentoBinding;


public class MiDepartamento_Activity extends AppCompatActivity {

    private ActivityMiDepartamentoBinding binding;

    TextView txtEdificio,txtdistrito,txtdireccion,txtdepartamento;
    Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMiDepartamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtEdificio = binding.txtEdificio;
        txtdistrito = binding.txtdistrito;
        txtdireccion = binding.txtdireccion;
        txtdepartamento = binding.txtdepartamento;

        btnVolver = binding.btnVolver;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        txtEdificio.setText(preferences.getString("numedi","nada"));
        txtdistrito.setText(preferences.getString("distrito","nada"));
        txtdireccion.setText(preferences.getString("direccion","nada"));
        txtdepartamento.setText(preferences.getString("numdepa","nada"));

        binding.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MiDepartamento_Activity.this, HogarActivity.class));
            }

        });

        txtEdificio.setEnabled(false);
        txtdistrito.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtdepartamento.setEnabled(false);
    }
}