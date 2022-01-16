package idat.com.administracion.app.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.badoualy.stepperindicator.StepperIndicator;

import java.util.Map;
import java.util.Set;

import idat.com.administracion.app.Adaptadores.PagerAdapter;
import idat.com.administracion.app.R;
import idat.com.administracion.app.databinding.ActivityPasarelaPagosBinding;

public class PasarelaPagosActivity extends AppCompatActivity {

    private ActivityPasarelaPagosBinding binding;

    ViewPager pager;
    StepperIndicator indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasarelaPagosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        pager=findViewById(R.id.viewPager);
        indicator = findViewById(R.id.stepper_indicator);
        assert pager != null;
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        indicator.setViewPager(pager,pager.getAdapter().getCount());

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                }else if (position == 1) {

                }else if (position == 2) {

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



}