package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

import idat.com.administracion.app.R;

public class RegistroSolicitudActivity extends AppCompatActivity {

    EditText nombre, apellido;
    TextView numEdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_solicitud);

        nombre = findViewById(R.id.nom);
        apellido = findViewById(R.id.ape);
        numEdi = findViewById(R.id.numedi);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        nombre.setText(preferences.getString("usuarioNombre", ""));
        apellido.setText(preferences.getString("usuarioApePat", "")+" "+preferences.getString("usuarioApeMat", ""));
        numEdi.setText(preferences.getString("numedi", ""));
    }
}