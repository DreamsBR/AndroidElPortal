package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import idat.com.administracion.app.databinding.ActivityDecisionBinding;
import idat.com.administracion.app.fragmentos.HomeFragment;

public class DecisionActivity extends AppCompatActivity {

    ActivityDecisionBinding binding;

    HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDecisionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.idbtnloginfirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var) {
                startActivity(new Intent(DecisionActivity.this,LoginActivity.class));
               /* homeFragment.deshabilitarConLoginFirebase();*/
            }
        });

        binding.idbtnloginmysql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                startActivity(new Intent(DecisionActivity.this,LoginActivityMysql.class));
              /*  homeFragment.deshabilitarConLoginMySQL();*/
            }
        });
    }

}