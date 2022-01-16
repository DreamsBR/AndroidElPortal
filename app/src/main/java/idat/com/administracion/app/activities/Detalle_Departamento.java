package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import idat.com.administracion.app.Entidades.Disponible;
import idat.com.administracion.app.databinding.ActivityDetalleDepartamentoBinding;

public class Detalle_Departamento extends AppCompatActivity {

    private ActivityDetalleDepartamentoBinding binding;

    TextView tvdescripcion_disponible,tvprecio_diponible;
    Button regresar;
    ImageView imagendepartamento_diponible;
    private Disponible disponibleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleDepartamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvdescripcion_disponible = binding.tvdescripcionDisponible;
        tvprecio_diponible = binding.tvprecioDiponible;
        regresar = binding.regresar;
        imagendepartamento_diponible = binding.imagendepartamentoDiponible;



        initValues();

        regresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detalle_Departamento
                        .this, Depa_Disponible_Activity.class);
                startActivity(intent);
            }
        });

        tvprecio_diponible.setEnabled(false);
        tvdescripcion_disponible.setEnabled(false);
    }

    private void initValues(){
        disponibleList = (Disponible) getIntent().getExtras().getSerializable("disponibleList");
        tvdescripcion_disponible.setText(disponibleList.getDescripcion());
        tvprecio_diponible.setText(disponibleList.getPrecio().toString());
        Glide.with(this)
                .load(disponibleList.getFoto())
                .into(imagendepartamento_diponible);

    }

}