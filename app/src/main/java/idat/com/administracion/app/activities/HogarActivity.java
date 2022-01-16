package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import idat.com.administracion.app.databinding.ActivityHogarBinding;

public class HogarActivity extends AppCompatActivity {

    ActivityHogarBinding binding;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHogarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();



        binding.idbtnpagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var) {
                startActivity(new Intent(HogarActivity.this, PasarelaPagosActivity.class));
            }
        });

        binding.idbtndepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                startActivity(new Intent(HogarActivity.this, Depa_Disponible_Activity.class));
            }
        });
        binding.idbtnMidepartamentos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HogarActivity.this, MiDepartamento_Activity.class));
            }

        });

        binding.idbtnsolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                startActivity(new Intent(HogarActivity.this, RegistroSolicitudActivity.class));
            }
        });
        binding.idbtnlistainvitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                startActivity(new Intent(HogarActivity.this, ListaInvitadosActivity.class));
            }
        });

        binding.idbtnlistareservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                startActivity(new Intent(HogarActivity.this, ListaReservaActivity.class));
            }
        });

        binding.idbtnpagos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HogarActivity.this, HistorialActivity.class));
            }

        });
    }
}