package idat.com.administracion.app.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.viewpager.widget.ViewPager;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import idat.com.administracion.app.R;
import idat.com.administracion.app.activities.ChatActivity;
import idat.com.administracion.app.activities.LoginActivity;
import idat.com.administracion.app.activities.LoginActivityMysql;
import idat.com.administracion.app.activities.ProfileActivity;
import idat.com.administracion.app.baseclass.enunciado.ModoIngreso;
import idat.com.administracion.app.handler.Memoria;
import idat.com.administracion.app.interfaces.IComunicaFragments;

public class HomeFragment extends Fragment {

    View vista;
    Activity activity; //REPRESENTA RELACION CON EL MAIN ACTIVITY
    CardView cardChat, cardRegistrar, cardReservar, cardHogar, cardSalir, cardNosotros;
    IComunicaFragments iComunicaFragments;
    TextView textUsuario;
    ImageView imageUsuario1;
    GridLayout grid;
    ModoIngreso modo = Memoria.getInstance().getModoIngreso();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_home, container, false);
        textUsuario = vista.findViewById(R.id.idTextUsername);
        imageUsuario1 = vista.findViewById(R.id.imageUsuario);
        grid = (GridLayout) vista.findViewById(R.id.idGrid);

        cardChat = vista.findViewById(R.id.idCardChat);
        cardNosotros = vista.findViewById(R.id.idCardNosotros);
        cardRegistrar = vista.findViewById(R.id.idCardRegistrarInvitados);
        cardReservar = vista.findViewById(R.id.idCardReservar);
        cardHogar = vista.findViewById(R.id.idCardHogar);
        cardSalir = vista.findViewById(R.id.idCardSalir);

        if (modo==ModoIngreso.LOGIN_CHAT){
            if (Memoria.getInstance().getUsuario() == null)
                textUsuario.setText("vacio");
            else
                textUsuario.setText(Memoria.getInstance().getUsuario().getNombre());
        }else{
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            textUsuario.setText(preferences.getString("email","nada"));
        }

        //cardNosotros = vista.findViewById(R.id.idCardNosotros);

        eventosMenu();
        refreshUI();
        return vista;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity = (Activity) context;
            iComunicaFragments = (IComunicaFragments) context;
        }
    }

    private void eventosMenu() {
        cardChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                iComunicaFragments.iniciarChat();

            }
        });

        cardRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                iComunicaFragments.iniciarRegistrar();
            }
        });

        cardReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                iComunicaFragments.iniciarReservar();
            }
        });

        cardHogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                iComunicaFragments.iniciarHogar();
            }
        });

       cardNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                iComunicaFragments.iniciarNosotros();
            }
        });

        cardSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                iComunicaFragments.iniciarLogout();
            }
        });
    }

    private void refreshUI(){
        ModoIngreso modo = Memoria.getInstance().getModoIngreso();

        cardChat.setEnabled(modo == ModoIngreso.LOGIN_CHAT);
        cardChat.setAlpha(modo == ModoIngreso.LOGIN_CHAT ? 1 : 0.5f);
        cardNosotros.setEnabled(modo == ModoIngreso.LOGIN_MYSQL);
        cardNosotros.setAlpha(modo == ModoIngreso.LOGIN_MYSQL ? 1 : 0.5f);
        cardRegistrar.setEnabled(modo == ModoIngreso.LOGIN_MYSQL);
        cardRegistrar.setAlpha(modo == ModoIngreso.LOGIN_MYSQL ? 1 : 0.5f);
        cardReservar.setEnabled(modo == ModoIngreso.LOGIN_MYSQL);
        cardReservar.setAlpha(modo == ModoIngreso.LOGIN_MYSQL ? 1 : 0.5f);
        cardHogar.setEnabled(modo == ModoIngreso.LOGIN_MYSQL);
        cardHogar.setAlpha(modo == ModoIngreso.LOGIN_MYSQL ? 1 : 0.5f);
        cardSalir = vista.findViewById(R.id.idCardSalir);
    }
}

