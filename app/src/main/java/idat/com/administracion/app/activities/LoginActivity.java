package idat.com.administracion.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import idat.com.administracion.app.Entidades.Usuario;
import idat.com.administracion.app.R;
import idat.com.administracion.app.baseclass.enunciado.ModoIngreso;
import idat.com.administracion.app.databinding.ActivityLoginBinding;
import idat.com.administracion.app.handler.Memoria;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser usuarioActual = firebaseAuth.getCurrentUser();
        /// Si autentico en firebase OK
        if(usuarioActual != null){
           startActivity(new Intent(getApplicationContext(),
                    MainActivity.class));
        }
        binding.btnloginfirebase.setOnClickListener(view -> {
            if(binding.etemaillogin.getText().toString().equals("") ||
                    binding.etpasswordlogin.getText().toString().equals("")){
                enviarMensaje(getString(R.string.valformlogin));
            }else{
                binding.btnloginfirebase.setEnabled(false);
                autenticacionFirabase(binding.etemaillogin.getText().toString(),
                        binding.etpasswordlogin.getText().toString());
            }
        });
        binding.tvregistrarusuario.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),
                    RegistroActivity.class));
        });
        binding.btnloginfirebase.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1888){
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
            }catch (ApiException ex){

            }
        }
    }


    private void enviarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    private void autenticacionFirabase(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Memoria.getInstance().setModoIngreso(ModoIngreso.LOGIN_CHAT);
                    FirebaseUser currentUser = Memoria.getInstance().getmAuth().getCurrentUser();
                    if(currentUser!=null){
                        DatabaseReference reference = Memoria.getInstance().getDatabase().getReference("Usuarios/"+currentUser.getUid());

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Usuario usuario = snapshot.getValue(Usuario.class);
                                Memoria.getInstance().setUsuario(usuario);
                                startActivity(
                                        new Intent(getApplicationContext(), MainActivity.class)
                                );
                                cleardata();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        }); }
                    else{
                        Toast.makeText(getApplicationContext(),"Autorizacion denegada",Toast.LENGTH_LONG).show();
                    }


                }else{
                    enviarMensaje(getString(R.string.valerrorloginfb));
                }
            }
        });
    }

    private void cleardata(){
        binding.etemaillogin.setText("");
        binding.etpasswordlogin.setText("");
    }

}