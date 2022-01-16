package idat.com.administracion.app.pasarela;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import idat.com.administracion.app.R;
import idat.com.administracion.app.activities.HogarActivity;
import idat.com.administracion.app.activities.ListaInvitadosActivity;
import idat.com.administracion.app.common.Constantes;
import idat.com.administracion.app.fragmentos.PagoSatisfactorioFragment;


public class DetalleFragment extends Fragment {

    View view;

    TextView tvMontoPagar,tvMotivoPago,tvNombreApellido;
    TextView tvnumhabitacion, tvnumedificio, tvnumdni, tvnumcelular, tvnumtarjeta, tvfechavencimiento, tvccvtarjeta;
    Button botonPagar;
    SharedPreferences preferences;

    public DetalleFragment() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalle, container, false);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());

        tvNombreApellido=view.findViewById(R.id.idtvNombreApellido);
        tvMotivoPago=view.findViewById(R.id.idMotivoPago);
        tvMontoPagar=view.findViewById(R.id.idMontoPagar);
        //visibility gone
        tvnumhabitacion=view.findViewById(R.id.idtvnumhabi);
        tvnumedificio=view.findViewById(R.id.idtvnumedi);
        tvnumdni=view.findViewById(R.id.idtvnumdni);
        tvnumcelular=view.findViewById(R.id.idtvnumcelu);
        tvnumtarjeta=view.findViewById(R.id.idtvnumtarjeta);
        tvfechavencimiento=view.findViewById(R.id.idtvfechavencimiento);
        tvccvtarjeta=view.findViewById(R.id.idtvccvtarjeta);
        //boton pagar
        botonPagar = view.findViewById(R.id.btnPAGAR);
        //

        obtenerDatos();
        System.out.println(tvNombreApellido.getText().toString());
        System.out.println(tvnumdni.getText().toString());
        System.out.println(tvfechavencimiento.getText().toString());
        System.out.println(tvnumtarjeta.getText().toString());
        System.out.println(tvccvtarjeta.getText().toString());
        System.out.println(tvnumcelular.getText().toString());
        System.out.println(tvnumhabitacion.getText().toString());
        System.out.println(tvnumedificio.getText().toString());
        System.out.println(tvMotivoPago.getText().toString());
        System.out.println(tvMontoPagar.getText().toString());

        botonPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                if (tvfechavencimiento.getText().toString().isEmpty()|| tvccvtarjeta.getText().toString().isEmpty() || tvnumtarjeta.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "completa los datos de tu tarjeta", Toast.LENGTH_SHORT).show();
                }else {
                    insertarDetallePago();
                }

            }
        });


        return view;
    }


    public void obtenerDatos(){
        tvNombreApellido.setText(preferences.getString("usuario",""));
        //visibility gone
        tvnumhabitacion.setText(preferences.getString("numdepa",""));
        tvnumedificio.setText(preferences.getString("numedi",""));
        tvnumdni.setText(preferences.getString("usuarioDni",""));
        tvnumcelular.setText(preferences.getString("numcelular",""));
        tvMotivoPago.setText(preferences.getString("tipoPagoSeleccionado","NADA"));
        tvMontoPagar.setText(preferences.getString("montoApagar","5"));
        tvnumtarjeta.setText(preferences.getString("numTarjetaHabi",""));
        tvfechavencimiento.setText(preferences.getString("fechaVenciTar",""));
        tvccvtarjeta.setText(preferences.getString("numCCVTar",""));
    }

    private void insertarDetallePago() {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        Map<String, String> parametros = new HashMap<>();
        parametros.put("habitante",tvNombreApellido.getText().toString());
        parametros.put("ccv",tvccvtarjeta.getText().toString());
        parametros.put("fecha_v",tvfechavencimiento.getText().toString());
        parametros.put("n_tarjeta",tvnumtarjeta.getText().toString());
        parametros.put("dni",tvnumdni.getText().toString());
        parametros.put("celular",tvnumcelular.getText().toString());
        parametros.put("num_depa",tvnumhabitacion.getText().toString());
        parametros.put("num_edi",tvnumedificio.getText().toString());
        parametros.put("tipo_pago",tvMotivoPago.getText().toString());
        parametros.put("monto_pagar",tvMontoPagar.getText().toString());
        JSONObject jsonObjectParametro = new JSONObject(parametros);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_POST_PAGO,
                jsonObjectParametro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        successMessage();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        colaPeticiones.add(request);
    }

    public void successMessage(String message) {
        new SweetAlertDialog(getContext(),
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setConfirmText(message)
                .setConfirmClickListener(sweetAlertDialog -> startActivity(new Intent(getContext(), PagoSatisfactorioFragment.class)))
                .show();
    }

    private void successMessage() {
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.fragment_pago_satisfactorio);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        ((TextView)dialog.findViewById(R.id.montototal)).setText("S/"+tvMontoPagar.getText().toString());
        ((TextView)dialog.findViewById(R.id.fechatransaccion)).setText(mydate.format(mydate));
        ((TextView)dialog.findViewById(R.id.numerotarjeta)).setText(tvnumtarjeta.getText().toString());

        dialog.findViewById(R.id.fabdetalle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
            }
        });

        dialog.show();
    }
}