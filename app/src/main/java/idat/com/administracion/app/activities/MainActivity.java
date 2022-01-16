package idat.com.administracion.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import idat.com.administracion.app.R;
import idat.com.administracion.app.fragmentos.HomeFragment;
import idat.com.administracion.app.fragmentos.MuroFragment;
import idat.com.administracion.app.fragmentos.RegistroInvitadosFragment;
import idat.com.administracion.app.fragmentos.ReservarZonasComunesFragment;
import idat.com.administracion.app.interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments {

    FragmentTransaction transaction;
    Fragment homeFragment, muroFragment, registroInviFragment, reservaFragment;
    TextView tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //colocar el HomeFragment como fragmento inicial
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,homeFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //El activity main será el contenedor que administrará el paso de las pantallas
    //implementando métodos para iniciar los fragmentos al presionar cada CarView
    @Override
    public void iniciarChat() {
        Intent intentChat= new Intent(MainActivity.this,ChatActivity.class);
        startActivity(intentChat);
    }

    @Override
    public void iniciarMuro() {
        muroFragment = new MuroFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,muroFragment).addToBackStack(null).commit();
    }

    @Override
    public void iniciarRegistrar() {
        registroInviFragment = new RegistroInvitadosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,registroInviFragment).addToBackStack(null).commit();
    }

    @Override
    public void iniciarReservar() {
        reservaFragment = new ReservarZonasComunesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,reservaFragment).addToBackStack(null).commit();
    }

    @Override
    public void iniciarHogar() {
        Intent intentHogar= new Intent(MainActivity.this,HogarActivity.class);
        startActivity(intentHogar);
    }

    @Override
    public void iniciarLogout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(),"Cerrar sesión",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void iniciarNosotros() {
        Intent intent= new Intent(MainActivity.this,NosotrosActivity.class);
        startActivity(intent);
    }
}