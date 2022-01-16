package idat.com.administracion.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import idat.com.administracion.app.R;

public class ProfileActivity extends AppCompatActivity {
    private  TextView username_textview, nombres_textview, apellidos_textview;
    private  TextView email_textview, phone_textview, dni_textview, edad_textview;
    private Button btnMostrarDatos;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final  String USERS = "users";


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        IniciarComponentes();

        btnMostrarDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null){
                    username_textview.setText(documentSnapshot.getString("username"));
                    nombres_textview.setText(documentSnapshot.getString("nombre"));
                    edad_textview.setText(documentSnapshot.getString("edad"));
                    apellidos_textview.setText(documentSnapshot.getString("apellidos"));
                    email_textview.setText(email);
                    phone_textview.setText(documentSnapshot.getString("celular"));
                    dni_textview.setText(documentSnapshot.getString("dni"));

                }
            }
        });
    }

    private void IniciarComponentes(){
        username_textview = findViewById(R.id.username_textview);
        nombres_textview = findViewById(R.id.nombres_textview);
        edad_textview = findViewById(R.id.edad_textview);
        apellidos_textview = findViewById(R.id.apellidos_textview);
        email_textview = findViewById(R.id.email_textview);
        phone_textview = findViewById(R.id.phone_textview);
        dni_textview = findViewById(R.id.dni_textview);
        btnMostrarDatos = findViewById(R.id.btnMostrarDatos);

    }
}