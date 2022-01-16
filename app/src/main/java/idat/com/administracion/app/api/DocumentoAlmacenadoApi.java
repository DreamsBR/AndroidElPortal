package idat.com.administracion.app.api;

import idat.com.administracion.app.Entidades.DocumentoAlmacenado;
import idat.com.administracion.app.Entidades.GenericResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DocumentoAlmacenadoApi {
    //RUTA DEL CONTROLADOR USUARIO
    String base = "api/documento-almacenado";

    @Multipart
    @POST(base)
    Call<GenericResponse<DocumentoAlmacenado>> save(@Part MultipartBody.Part file, @Part("nombre") RequestBody requestBody);


}
