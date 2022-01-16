package idat.com.administracion.app.utils;

import java.util.List;

import idat.com.administracion.app.Entidades.Departamentos;
import idat.com.administracion.app.Entidades.Disponible;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DepartamentoService {

    @GET("listarDepartamentos")
    Call<List<Departamentos>> getDepartamento();

    @GET("disponible")
    Call<List<Disponible>> getDisponible();
}
