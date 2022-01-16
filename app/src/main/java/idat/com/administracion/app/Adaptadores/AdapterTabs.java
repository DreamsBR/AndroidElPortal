package idat.com.administracion.app.Adaptadores;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import idat.com.administracion.app.fragmentos.MuroFragment;
import idat.com.administracion.app.R;
import idat.com.administracion.app.fragmentos.HomeFragment;


public class AdapterTabs extends FragmentPagerAdapter {

    @StringRes
    private static int[] TAB_TITULOS = new int[]{R.string.idettabhome,R.string.idettabmuro,R.string.idettabhogar};
    private final Context context;


    public AdapterTabs(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new HomeFragment();
                break;
            case 1 :
                fragment = new MuroFragment();
                break;
            case 2 :
                fragment = new MuroFragment();
                break;
        }
        return  fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITULOS[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITULOS.length;
    }
}
