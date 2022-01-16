package idat.com.administracion.app.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import idat.com.administracion.app.Entidades.Usuario;
import idat.com.administracion.app.R;

public class RegistroActivity extends AppCompatActivity {

    private EditText  etNombre, etApellido, etUsername, etCelular, etDni, etEdad, etCorreo, etContraseña, etRepetirContraseña;
    private Button btnRegistrarse;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference referenceUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etNombre= findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etUsername = findViewById(R.id.etUsername);
        etCelular = findViewById(R.id.etCelular);
        etDni = findViewById(R.id.etDni);
        etEdad = findViewById(R.id.etEdad);
        etCorreo= findViewById(R.id.etCorreo);
        etContraseña= findViewById(R.id.etContraseña);
        etRepetirContraseña= findViewById(R.id.etRepetirContraseña);
        btnRegistrarse= (Button) findViewById(R.id.btnRegistrarse);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        referenceUsuarios = database.getReference("Usuarios");



        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = etCorreo.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellidos = etApellido.getText().toString();
                String username = etUsername.getText().toString();
                String celular = etCelular.getText().toString();
                String dni = etDni.getText().toString();
                String edad = etEdad.getText().toString();
                if (isValidEmail(correo) && validarContraseña() && validarNombre(nombre) && validarCelular(celular) && validarDni(dni)){
                    String contraseña = etContraseña.getText().toString();
                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistroActivity.this,"SE REGISTRO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                                        Usuario usuario = new Usuario();
                                        usuario.setCorreo(correo);
                                        usuario.setNombre(nombre);
                                        usuario.setApellidos(apellidos);
                                        usuario.setUsername(username);
                                        usuario.setCelular(celular);
                                        usuario.setDni(dni);
                                        usuario.setEdad(edad);
                                        referenceUsuarios.push().setValue(usuario);
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                        reference.setValue(usuario);
                                        finish();
                                    } else {
                                        Toast.makeText(RegistroActivity.this, "ERROR AL REGISTRARSE", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(RegistroActivity.this,"Validacion corriendo, Porfavor ingrese los datos correctamente",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //valida el correo
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validarCelular(String celular){
        celular = etCelular.getText().toString();
        if (celular.length() == 9) {
            return true;
        }else {
            return false;
        }
    }

    public boolean validarDni(String dni){
        dni = etCelular.getText().toString();
        if (dni.length() == 9){
            return true;
        }else {
            return false;
        }
    }

    public boolean validarContraseña(){
        String contraseña, contraseñaRepetida;
        contraseña = etContraseña.getText().toString();
        contraseñaRepetida = etRepetirContraseña.getText().toString();
        if (contraseña.equals(contraseñaRepetida)){
            if(contraseña.length()>=6 && contraseña.length()<=16){
                return true;
            } else return false;
        }else return  false;
    }


    public boolean validarNombre(String nombre){
        return !nombre.isEmpty();
    }


}