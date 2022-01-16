package idat.com.administracion.app.pasarela;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import idat.com.administracion.app.R;


public class TarjetaFragment extends Fragment {

    private int mes,ano,dia;
    EditText edtTarjeta, edtCcv, edtFechaVenci;
    TextView tvmonto, tvtipo;
    SharedPreferences preferences;

    public TarjetaFragment() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarjeta, container, false);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        edtTarjeta = view.findViewById(R.id.edtTarjeta);
        edtCcv = view.findViewById(R.id.edtCCVU);
        edtFechaVenci = view.findViewById(R.id.edtFechaVencimientoU);
        tvmonto = view.findViewById(R.id.tvTarjetaMontoPago);
        tvtipo = view.findViewById(R.id.tvTarjetaTipoPago);

        //obteniendo datos tarjeta
        edtCcv.setText(preferences.getString("numCCVTar",""));
        edtFechaVenci.setText(preferences.getString("fechaVenciTar",""));
        edtTarjeta.setText(preferences.getString("numTarjetaHabi",""));


        edtFechaVenci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==edtFechaVenci){
                    final Calendar c= Calendar.getInstance();
                    dia=c.get(Calendar.DAY_OF_MONTH);
                    mes=c.get(Calendar.MONTH);
                    ano=c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month,int day) {
                                    edtFechaVenci.setText((month+1)+"/"+year);
                                }
                            }
                            ,ano,mes,dia);

                    datePickerDialog.show();
                }
            }
        });



        return view;
    }
}