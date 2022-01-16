package idat.com.administracion.app.pasarela;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import idat.com.administracion.app.R;

public class DatosConMontoFragment extends Fragment {

    EditText edtnombre, edtapePat, edtapeMat, edtDni, edtCelu, edtNumHabi, edtNumEdi, edtMonto;
    private AutoCompleteTextView dropdownTipoPago;
    View view;


    public DatosConMontoFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datos_con_monto, container, false);

        setId();
        obtenerDatos();
        deshabilitar();
        cargarLista();

        return view;
    }

    public void deshabilitar(){
        edtnombre.setEnabled(false);
        edtapePat.setEnabled(false);
        edtapeMat.setEnabled(false);
        edtDni.setEnabled(false);
        edtCelu.setEnabled(false);
        edtNumHabi.setEnabled(false);
        edtNumEdi.setEnabled(false);
        edtMonto.setEnabled(false);
    }

    public void obtenerDatos(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        edtnombre.setText(preferences.getString("usuarioNombre",""));
        edtapePat.setText(preferences.getString("usuarioApePat",""));
        edtapeMat.setText(preferences.getString("usuarioApeMat",""));
        edtDni.setText(preferences.getString("usuarioDni",""));
        edtCelu.setText(preferences.getString("numcelular",""));
        edtNumHabi.setText(preferences.getString("numdepa",""));
        edtNumEdi.setText(preferences.getString("numedi",""));
    }

    public void cargarLista(){
        //LISTA DE TIPOS DE PAGO DE SERVICIOS(MENSUALIDAD-PAGO...)
        String[] tipoPagoSer = getResources().getStringArray(R.array.tiposervicios);
        ArrayAdapter arrayTipoPagoSer = new ArrayAdapter(getContext(), R.layout.dropdown_item, tipoPagoSer);
        dropdownTipoPago.setAdapter(arrayTipoPagoSer);

        dropdownTipoPago.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                switch (count){
                    case 12:
                        edtMonto.setText("60");
                        editor.putString("tipoPagoSeleccionado", dropdownTipoPago.getText().toString());
                        editor.putString("montoApagar",edtMonto.getText().toString());
                        editor.apply();
                        System.out.println(dropdownTipoPago.getText().toString()+edtMonto.getText().toString());
                        break;
                    case 13:
                        edtMonto.setText("50");
                        editor.putString("tipoPagoSeleccionado", dropdownTipoPago.getText().toString());
                        editor.putString("montoApagar",edtMonto.getText().toString());
                        editor.apply();
                        System.out.println(dropdownTipoPago.getText().toString()+edtMonto.getText().toString());
                        break;
                    case 11:
                    default:
                        edtMonto.setText("1500");
                        editor.putString("tipoPagoSeleccionado", dropdownTipoPago.getText().toString());
                        editor.putString("montoApagar",edtMonto.getText().toString());
                        editor.apply();
                        System.out.println(dropdownTipoPago.getText().toString()+edtMonto.getText().toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    public void setId(){
        edtnombre = view.findViewById(R.id.edtTarjeta);
        edtapePat = view.findViewById(R.id.edtApellidoPaternoU);
        edtapeMat = view.findViewById(R.id.edtApellidoMaternoU);
        edtDni = view.findViewById(R.id.edtDniU);
        edtCelu = view.findViewById(R.id.edtCelularU);
        edtNumHabi = view.findViewById(R.id.edtNumDepaU);
        edtNumEdi = view.findViewById(R.id.edtNumEdiU);
        edtMonto = view.findViewById(R.id.edtMontoU);
        dropdownTipoPago = view.findViewById(R.id.dropdownTipoPago);
    }
}