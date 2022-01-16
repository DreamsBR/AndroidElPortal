package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import idat.com.administracion.app.Entidades.UsuarioMySql;
import idat.com.administracion.app.R;
import idat.com.administracion.app.baseclass.enunciado.ModoIngreso;
import idat.com.administracion.app.common.Constantes;
import idat.com.administracion.app.databinding.ActivityHogarBinding;
import idat.com.administracion.app.databinding.ActivityLoginMysqlBinding;
import idat.com.administracion.app.fragmentos.HomeFragment;
import idat.com.administracion.app.handler.Memoria;
import idat.com.administracion.app.utils.DateSerializer;
import idat.com.administracion.app.utils.TimeSerializer;
import idat.com.administracion.app.viewmodel.UsuarioMySqlViewModel;

public class LoginActivityMysql extends AppCompatActivity {

    private ActivityLoginMysqlBinding binding;
    private EditText edtMail, edtPassword;
    private TextView tvnuevoUsuario;
    private Button btnIniciarSesion;
    private UsuarioMySqlViewModel viewModel;
    private TextInputLayout txtInputUsuario, txtInputPassword;
    private UsuarioMySql usuarioMySql = new UsuarioMySql();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginMysqlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.initViewModel();
        this.init();
        getSupportActionBar().hide();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UsuarioMySqlViewModel.class);
    }

    private void init() {
        edtMail = binding.edtMail;
        edtPassword = binding.edtPassword;
        txtInputUsuario = findViewById(R.id.txtInputUsuario);
        txtInputPassword = findViewById(R.id.txtInputPassword);
        tvnuevoUsuario = findViewById(R.id.idNuevoUsuario);
        btnIniciarSesion = binding.btnIniciarSesion;
        btnIniciarSesion.setOnClickListener(v -> {
            try {
                if (validar()) {
                    viewModel.login(edtMail.getText().toString(), edtPassword.getText().toString()).observe(this, response -> {
                        if (response.getRpta() == 1) {
                            //Toast.makeText(this,response.getMessage(),Toast.LENGTH_SHORT).show();
                            toastCorrecto(response.getMessage());
                            UsuarioMySql umsql = response.getBody();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                            SharedPreferences.Editor editor = preferences.edit(); //almacenar el usuario en sesión
                            final Gson gson = new GsonBuilder()
                                    .registerTypeAdapter(Date.class, new DateSerializer())
                                    .registerTypeAdapter(Time.class, new TimeSerializer())
                                    .create();
                            editor.putString("UsuarioJson", gson.toJson(umsql, new TypeToken<UsuarioMySql>() {
                            }.getType()));
                            editor.putString("email", "Hola, "+response.getBody().getHabitante().getNombres()); //guardando en el sharedPreferences
                            editor.putString("usuario", response.getBody().getHabitante().getNombres()+" "+response.getBody().getHabitante().getApellidoPaterno()+" "+response.getBody().getHabitante().getApellidoMaterno());
                            editor.putString("usuarioNombre", response.getBody().getHabitante().getNombres());
                            editor.putString("usuarioApePat", response.getBody().getHabitante().getApellidoPaterno());
                            editor.putString("usuarioApeMat", response.getBody().getHabitante().getApellidoMaterno());
                            editor.putString("numdepa",response.getBody().getHabitante().getNumDepartamento());
                            editor.putString("distrito",response.getBody().getHabitante().getDistrito());
                            editor.putString("direccion",response.getBody().getHabitante().getDireccion());
                            editor.putString("numedi",response.getBody().getHabitante().getNumEdificio());
                            editor.putString("numcelular",response.getBody().getHabitante().getTelefono());
                            editor.putString("usuarioDni",response.getBody().getHabitante().getNumDoc());
                            //tarjeta
                            editor.putString("numCCVTar",response.getBody().getHabitante().getCcv());
                            editor.putString("fechaVenciTar",response.getBody().getHabitante().getFecha_v());
                            editor.putString("numTarjetaHabi",response.getBody().getHabitante().getN_tarjeta());
                            //aplicar cambios
                            editor.apply();
                            edtMail.setText("");
                            edtPassword.setText("");
                            Memoria.getInstance().setModoIngreso(ModoIngreso.LOGIN_MYSQL);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            //Toast.makeText(this,response.getMessage(),Toast.LENGTH_SHORT).show();
                            toastIncorrecto(response.getMessage());
                        }
                    });
                } else {
                    toastIncorrecto("Error, completar todos los campos");
                }
            } catch (Exception ex) {
                toastIncorrecto("Error al intentar iniciar sesión "+ ex.getMessage());
            }

        });
        //al escribir - eliminar error
        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputUsuario.setErrorEnabled(false); //quitar error al iniciar a escribir
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvnuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                Intent intent = new Intent(LoginActivityMysql.this,RegistrarUsuarioActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_in,R.anim.left_out);
            }
        });
    }

    private void toastIncorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error, findViewById(R.id.ll_custom_toast_error));
        TextView tvmensaje = view.findViewById(R.id.txtMensajeToast2);
        tvmensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public void toastCorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_ok, findViewById(R.id.ll_custom_toast_ok));
        TextView tvmensaje = view.findViewById(R.id.txtMensajeToast1);
        tvmensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    private boolean validar() {
        boolean retorno = true;
        String usuario, password;
        usuario = edtMail.getText().toString();
        password = edtPassword.getText().toString();
        if (usuario.isEmpty()) {
            txtInputUsuario.setError("Completar correo electrónico");
            retorno = false;
        } else {
            txtInputUsuario.setErrorEnabled(false);
        }
        if (password.isEmpty()) {
            txtInputPassword.setError("Completar contraseña");
            retorno = false;
        } else {
            txtInputPassword.setErrorEnabled(false);
        }
        return retorno;
    }
}