package idat.com.administracion.app.fragmentos;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import idat.com.administracion.app.R;

public class PagoSatisfactorioFragment extends DialogFragment {

    private View root_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_pago_satisfactorio, container, false);

        ((FloatingActionButton) root_view.findViewById(R.id.fabdetalle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dismiss();
            }
        });

        return root_view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}