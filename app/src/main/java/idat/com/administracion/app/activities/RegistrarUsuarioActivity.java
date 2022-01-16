package idat.com.administracion.app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.time.LocalDateTime;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import idat.com.administracion.app.Entidades.DocumentoAlmacenado;
import idat.com.administracion.app.Entidades.Habitante;
import idat.com.administracion.app.Entidades.UsuarioMySql;
import idat.com.administracion.app.viewmodel.DocumentoAlmacenadoViewModel;
import idat.com.administracion.app.viewmodel.HabitanteViewModel;
import idat.com.administracion.app.viewmodel.UsuarioMySqlViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import idat.com.administracion.app.R;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    private File f;
    private HabitanteViewModel habitanteViewModel;
    private UsuarioMySqlViewModel usuarioViewModel;
    private DocumentoAlmacenadoViewModel documentoAlmacenadoViewModel;
    private Button btnSubirImagen, btnGuardarDatos;
    private CircleImageView imageUser;
    private AutoCompleteTextView dropdownTipoDoc, dropdownDepartamento, dropdownEdificio, dropdownDistrito;
    private EditText edtNameUser, edtApellidoPaternoU, edtApellidoMaternoU, edtNumDocU, edtTelefonoU,
            edtDireccionU, edtPasswordUser, edtEmailUser,edtTarjetaUser,edtFechaVencimientoU,edtCCVU;
    private TextInputLayout txtInputNameUser, txtInputApellidoPaternoU, txtInputApellidoMaternoU,
            txtInputTipoDoc, txtInputNumeroDocU, txtInputDepartamento, txtInputEdificio, txtInputCCVU,txtInputTarjetaUser,txtInputFechaVencimientoU,
            txtInputDistrito, txtInputTelefonoU, txtInputDireccionU, txtInputEmailUser, txtInputPasswordUser;
    private final static int LOCATION_REQUEST_CODE = 23;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        this.init();
        this.initViewModel();
        this.spinners();
        getSupportActionBar().hide();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            final String realPath = getRealPathFromURI(uri);
            this.f = new File(realPath);
            this.imageUser.setImageURI(uri);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String result;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void spinners() {
        //LISTA DE TIPOS DE DOCUMENTOS
        String[] tipoDoc = getResources().getStringArray(R.array.tipoDoc);
        ArrayAdapter arrayTipoDoc = new ArrayAdapter(this, R.layout.dropdown_item, tipoDoc);
        dropdownTipoDoc.setAdapter(arrayTipoDoc);
        //LISTA DE DEPARTAMENTOS
        String[] departamentos = getResources().getStringArray(R.array.departamentos);
        ArrayAdapter arrayDepartamentos = new ArrayAdapter(this, R.layout.dropdown_item, departamentos);
        dropdownDepartamento.setAdapter(arrayDepartamentos);
        //LISTA DE EDIFICIOS
        String[] edificios = getResources().getStringArray(R.array.edificios);
        ArrayAdapter arrayEdificios = new ArrayAdapter(this, R.layout.dropdown_item, edificios);
        dropdownEdificio.setAdapter(arrayEdificios);
        //LISTA DE DISTRITOS
        String[] distritos = getResources().getStringArray(R.array.distritos);
        ArrayAdapter arrayDistritos = new ArrayAdapter(this, R.layout.dropdown_item, distritos);
        dropdownDistrito.setAdapter(arrayDistritos);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {
        btnGuardarDatos = findViewById(R.id.btnGuardarDatos);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        imageUser = findViewById(R.id.imageUser);
        edtNameUser = findViewById(R.id.edtTarjeta);
        edtApellidoPaternoU = findViewById(R.id.edtApellidoPaternoU);
        edtApellidoMaternoU = findViewById(R.id.edtApellidoMaternoU);
        edtNumDocU = findViewById(R.id.edtNumDocU);
        edtTelefonoU = findViewById(R.id.edtTelefonoU);
        edtDireccionU = findViewById(R.id.edtDireccionU);
        edtPasswordUser = findViewById(R.id.edtPasswordUser);
        edtEmailUser = findViewById(R.id.edtEmailUser);
        edtCCVU = findViewById(R.id.edtCCVU);
        edtFechaVencimientoU = findViewById(R.id.edtFechaVencimientoU);
        edtTarjetaUser = findViewById(R.id.edtTarjetaUser);
        //AutoCompleteTextView
        dropdownTipoDoc = findViewById(R.id.dropdownTipoDoc);
        dropdownDepartamento = findViewById(R.id.dropdownDepartamento);
        dropdownEdificio = findViewById(R.id.dropdownEdificio);
        dropdownDistrito = findViewById(R.id.dropdownDistrito);
        //TextInputLayout
        txtInputNameUser = findViewById(R.id.txtInputNameUser);
        txtInputApellidoPaternoU = findViewById(R.id.txtInputApellidoPaternoU);
        txtInputApellidoMaternoU = findViewById(R.id.txtInputApellidoMaternoU);
        txtInputTipoDoc = findViewById(R.id.txtInputTipoDoc);
        txtInputNumeroDocU = findViewById(R.id.txtInputNumeroDocU);
        txtInputDepartamento = findViewById(R.id.txtInputDepartamento);
        txtInputEdificio = findViewById(R.id.txtInputEdificio);
        txtInputDistrito = findViewById(R.id.txtInputDistrito);
        txtInputFechaVencimientoU = findViewById(R.id.txtInputFechaVencimientoU);
        txtInputTarjetaUser = findViewById(R.id.txtInputTarjetaUser);
        txtInputCCVU = findViewById(R.id.txtInputCCVU);
        txtInputTelefonoU = findViewById(R.id.txtInputTelefonoU);
        txtInputDireccionU = findViewById(R.id.txtInputDireccionU);
        txtInputEmailUser = findViewById(R.id.txtInputEmailUser);
        txtInputPasswordUser = findViewById(R.id.txtInputPasswordUser);
        btnSubirImagen.setOnClickListener(v -> {
            this.cargarImagen();
        });
        btnGuardarDatos.setOnClickListener(v -> {
            this.guardarDatos();
        });
        ///ONCHANGE LISTENER A LOS EDITEXT
        edtNameUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputNameUser.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtApellidoPaternoU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputApellidoPaternoU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtApellidoMaternoU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputApellidoMaternoU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtNumDocU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputNumeroDocU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtTelefonoU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputTelefonoU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtDireccionU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputDireccionU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtTarjetaUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputTarjetaUser.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtFechaVencimientoU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputFechaVencimientoU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtCCVU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputCCVU.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dropdownTipoDoc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputTipoDoc.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dropdownDepartamento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputDepartamento.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dropdownEdificio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputEdificio.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dropdownDistrito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputDistrito.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void guardarDatos() {
        Habitante h;
        if (validar()) {
            h = new Habitante();
            try {
                h.setNombres(edtNameUser.getText().toString());
                h.setApellidoPaterno(edtApellidoPaternoU.getText().toString());
                h.setApellidoMaterno(edtApellidoMaternoU.getText().toString());
                h.setTipoDoc(dropdownTipoDoc.getText().toString());
                h.setNumDoc(edtNumDocU.getText().toString());
                h.setNumDepartamento(dropdownDepartamento.getText().toString());
                h.setNumEdificio(dropdownEdificio.getText().toString());
                h.setDistrito(dropdownDistrito.getText().toString());
                h.setTelefono(edtTelefonoU.getText().toString());
                h.setDireccion(edtDireccionU.getText().toString());
                h.setCcv(edtCCVU.getText().toString());
                h.setFecha_v(edtFechaVencimientoU.getText().toString());
                h.setN_tarjeta(edtTarjetaUser.getText().toString());
                h.setId(0);
                LocalDateTime ldt = LocalDateTime.now(); //Para generar el nombre al archivo en base a la fecha, hora, año
                RequestBody rb = RequestBody.create(f, MediaType.parse("multipart/form-data")), somedata; //Le estamos enviando un archivo (imagen) desde el formulario
                String filename = "" + ldt.getDayOfMonth() + (ldt.getMonthValue() + 1) +
                        ldt.getYear() + ldt.getHour()
                        + ldt.getMinute() + ldt.getSecond(); //Asignar un nombre al archivo (imagen)
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", f.getName(), rb);
                somedata = RequestBody.create("profilePh" + filename, MediaType.parse("text/plain")); //Le estamos enviando un nombre al archivo.
                this.documentoAlmacenadoViewModel.save(part, somedata).observe(this, response -> {
                    if (response.getRpta() == 1) {
                        h.setFoto(new DocumentoAlmacenado());
                        h.getFoto().setId(response.getBody().getId());//Asignamos la foto al habitante

                        this.habitanteViewModel.guardarHabitante(h).observe(this, cResponse -> {
                            if (cResponse.getRpta() == 1) {
                                Toast.makeText(this, response.getMessage() + ", ahora procederemos a registrar sus credenciales.", Toast.LENGTH_SHORT).show();
                                int idc = cResponse.getBody().getId();//Obtener el id del habitante.
                                UsuarioMySql u = new UsuarioMySql();
                                u.setEmail(edtEmailUser.getText().toString());
                                u.setPassword(edtPasswordUser.getText().toString());
                                u.setVigencia(true);
                                u.setHabitante(new Habitante(idc));
                                this.usuarioViewModel.save(u).observe(this, uResponse -> {
                                    Toast.makeText(this, uResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    if (uResponse.getRpta() == 1) {
                                        Toast.makeText(this, "Sus Datos y credenciales fueron creados correctamente", Toast.LENGTH_SHORT).show();
                                        this.finish();
                                    } else {
                                        Toast.makeText(this, "No se pudo guardar los datos, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(this, "No se ha podido guardar los datos, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "No se ha podido guardar los datos, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                warningMessage("Se ha producido un error : " + e.getMessage());
            }
        } else {
            errorMessage("Por favor, complete todos los campos del formulario");
        }
    }


    private void cargarImagen() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i, "Seleccione la Aplicación"), 10);
    }

    @Override
    protected void onStart() { //solicitar permisos al dispositivo para acceder al archivo de la imagen
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Gracias por conceder los permisos para " +
                            "leer el almacenamiento, estos permisos son necesarios para poder " +
                            "escoger tu foto de perfil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No podemos realizar el registro si no nos concedes los permisos para leer el almacenamiento.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.habitanteViewModel = vmp.get(HabitanteViewModel.class);
        this.usuarioViewModel = vmp.get(UsuarioMySqlViewModel.class);
        this.documentoAlmacenadoViewModel = vmp.get(DocumentoAlmacenadoViewModel.class);
    }

    private boolean validar() {
        boolean retorno = true;
        String nombres, apellidoPaterno, apellidoMaterno, numDoc, telefono, direccion, correo, clave,numtarjeta,numCCV,fechaVenci,
                dropTipoDoc, dropDepartamento, dropProvincia, dropDistrito;
        nombres = edtNameUser.getText().toString();
        apellidoPaterno = edtApellidoPaternoU.getText().toString();
        apellidoMaterno = edtApellidoMaternoU.getText().toString();
        numDoc = edtNumDocU.getText().toString();
        telefono = edtTelefonoU.getText().toString();
        direccion = edtDireccionU.getText().toString();
        correo = edtEmailUser.getText().toString();
        clave = edtPasswordUser.getText().toString();
        numCCV = edtCCVU.getText().toString();
        numtarjeta = edtTarjetaUser.getText().toString();
        fechaVenci = edtFechaVencimientoU.getText().toString();
        dropTipoDoc = dropdownTipoDoc.getText().toString();
        dropDepartamento = dropdownDepartamento.getText().toString();
        dropProvincia = dropdownEdificio.getText().toString();
        dropDistrito = dropdownDistrito.getText().toString();
        if (this.f == null) {
            errorMessage("debe selecionar una foto de perfil");
            retorno = false;
        }
        if (nombres.isEmpty()) {
            txtInputNameUser.setError("Ingresar nombres");
            retorno = false;
        } else {
            txtInputNameUser.setErrorEnabled(false);
        }
        if (apellidoPaterno.isEmpty()) {
            txtInputApellidoPaternoU.setError("Ingresar apellido paterno");
            retorno = false;
        } else {
            txtInputApellidoPaternoU.setErrorEnabled(false);
        }
        if (apellidoMaterno.isEmpty()) {
            txtInputApellidoMaternoU.setError("Ingresar apellido materno");
            retorno = false;
        } else {
            txtInputApellidoMaternoU.setErrorEnabled(false);
        }
        if (numDoc.isEmpty()) {
            txtInputNumeroDocU.setError("Ingresar número documento");
            retorno = false;
        } else {
            txtInputNumeroDocU.setErrorEnabled(false);
        }
        if (telefono.isEmpty()) {
            txtInputTelefonoU.setError("Ingresar número telefónico");
            retorno = false;
        } else {
            txtInputTelefonoU.setErrorEnabled(false);
        }
        if (direccion.isEmpty()) {
            txtInputDireccionU.setError("Ingresar dirección de su casa");
            retorno = false;
        } else {
            txtInputDireccionU.setErrorEnabled(false);
        }
        if (correo.isEmpty()) {
            txtInputEmailUser.setError("Ingresar correo electrónico");
            retorno = false;
        } else {
            txtInputEmailUser.setErrorEnabled(false);
        }
        if (numCCV.isEmpty()) {
            txtInputCCVU.setError("Ingresar CCV");
            retorno = false;
        } else {
            txtInputCCVU.setErrorEnabled(false);
        }
        if (numtarjeta.isEmpty()) {
            txtInputTarjetaUser.setError("Ingresar Nº de tarjeta");
            retorno = false;
        } else {
            txtInputTarjetaUser.setErrorEnabled(false);
        }
        if (fechaVenci.isEmpty()) {
            txtInputFechaVencimientoU.setError("Ingresar fecha vencimiento");
            retorno = false;
        } else {
            txtInputFechaVencimientoU.setErrorEnabled(false);
        }
        if (clave.isEmpty()) {
            txtInputPasswordUser.setError("Ingresar clave para el inicio de sesión");
            retorno = false;
        } else {
            txtInputPasswordUser.setErrorEnabled(false);
        }
        if (dropTipoDoc.isEmpty()) {
            txtInputTipoDoc.setError("Seleccionar Tipo Doc");
            retorno = false;
        } else {
            txtInputTipoDoc.setErrorEnabled(false);
        }
        if (dropDepartamento.isEmpty()) {
            txtInputDepartamento.setError("Seleccionar Departamento");
            retorno = false;
        } else {
            txtInputDepartamento.setErrorEnabled(false);
        }
        if (dropProvincia.isEmpty()) {
            txtInputEdificio.setError("Seleccionar Provincia");
            retorno = false;
        } else {
            txtInputEdificio.setErrorEnabled(false);
        }
        if (dropDistrito.isEmpty()) {
            txtInputDistrito.setError("Seleccionar Distrito");
            retorno = false;
        } else {
            txtInputDistrito.setErrorEnabled(false);
        }
        return retorno;
    }

    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setContentText(message).show();
    }

    public void errorMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(message).show();
    }

    public void warningMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.WARNING_TYPE).setTitleText("Notificación del Sistema")
                .setContentText(message).setConfirmText("Ok").show();
    }

}