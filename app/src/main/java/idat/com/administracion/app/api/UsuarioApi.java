package idat.com.administracion.app.api;

import idat.com.administracion.app.Entidades.GenericResponse;
import idat.com.administracion.app.Entidades.UsuarioMySql;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioApi {
    //RUTA DEL CONTROLADOR USUARIO
    String base = "/api/usuario";

    //RUTA DEL CONTROLADOR USUARIO + LA RUTA DEL MÉTODO
    @FormUrlEncoded
    @POST(base + "/login")
    Call<GenericResponse<UsuarioMySql>> login(@Field("email") String email, @Field("password") String contrasenia);

    @POST(base)
    Call<GenericResponse<UsuarioMySql>> save(@Body UsuarioMySql u);

}
