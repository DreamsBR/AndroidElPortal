package idat.com.administracion.app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import de.hdodenhof.circleimageview.CircleImageView;
import idat.com.administracion.app.Adaptadores.Adaptermensajes;
import idat.com.administracion.app.Entidades.MensajeEnviar;
import idat.com.administracion.app.Entidades.MensajeRecibir;
import idat.com.administracion.app.Entidades.Usuario;
import idat.com.administracion.app.R;
import idat.com.administracion.app.handler.Memoria;

public class ChatActivity extends AppCompatActivity {

    private CircleImageView fotoperfil;
    private TextView nombre;
    private RecyclerView rvmensaje;
    private EditText txtmensaje;
    private ImageView btnenviar;
    private Adaptermensajes adapter;
    private ImageView btnenviarfoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_SEND = 1;

    private FirebaseAuth mAuth;
    private String NOMBRE_USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();



        fotoperfil = (CircleImageView) findViewById(R.id.fotoperfil);
        nombre = (TextView) findViewById(R.id.nombreperfil);
        rvmensaje = (RecyclerView) findViewById(R.id.rvmensaje);
        txtmensaje = (EditText) findViewById(R.id.txtmensaje);
        btnenviar = (ImageView) findViewById(R.id.btnenviar);
        btnenviarfoto =(ImageView) findViewById(R.id.btnarchivos);

        database = Memoria.getInstance().getDatabase();
        databaseReference = database.getReference("chat");//Sala de chat(nombre)
        storage = Memoria.getInstance().getStorage();
        mAuth = Memoria.getInstance().getmAuth();

        adapter = new Adaptermensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvmensaje.setLayoutManager(l);
        rvmensaje.setAdapter(adapter);

        btnenviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseReference.push().setValue(new MensajeEnviar(txtmensaje.getText().toString(),NOMBRE_USUARIO,"","1", ServerValue.TIMESTAMP));
                txtmensaje.setText("");
            }
        });

        btnenviarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("imagen/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_SEND);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnaphot, @Nullable String previousChildName) {
                MensajeRecibir m = dataSnaphot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnaphot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnaphot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnaphot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        verifyStoragePermissions(this);
    }

    private void setScrollbar(){
        rvmensaje.scrollToPosition(adapter.getItemCount()-1);
    }

    public static boolean verifyStoragePermissions(Activity activity) {
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int REQUEST_EXTERNAL_STORAGE = 1;
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return false;
        }else{
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(requestCode==PHOTO_SEND && resultCode==RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes_chat");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> u = taskSnapshot.getStorage().getDownloadUrl();
                    while(!u.isSuccessful());
                    final Uri taskResult = u.getResult();
                    final String downloadUrl = taskResult.toString();
                    //Task<Uri> u = taskSnapshot.getStorage().getDownloadUrl();
                    MensajeEnviar m = new MensajeEnviar(NOMBRE_USUARIO+" ha enviado una foto",downloadUrl,NOMBRE_USUARIO,"","2",ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            btnenviar.setEnabled(false);
            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
            
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    Memoria.getInstance().setUsuario(usuario);
                    NOMBRE_USUARIO = usuario.getNombre();

                    btnenviar.setEnabled(true);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            startActivity(new Intent(ChatActivity.this, LoginActivity.class));
            finish();
        }
    }


}
