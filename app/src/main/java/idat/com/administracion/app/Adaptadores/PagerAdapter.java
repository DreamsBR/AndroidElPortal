package idat.com.administracion.app.Adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import idat.com.administracion.app.pasarela.DatosConMontoFragment;
import idat.com.administracion.app.pasarela.DetalleFragment;
import idat.com.administracion.app.pasarela.TarjetaFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DatosConMontoFragment();
            case 1: return new TarjetaFragment();
            case 2: return new DetalleFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
