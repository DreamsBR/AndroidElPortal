package idat.com.administracion.app.api;

import idat.com.administracion.app.Entidades.GenericResponse;
import idat.com.administracion.app.Entidades.Habitante;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HabitanteApi {

    String base = "api/habitante";

    @POST(base)
    Call<GenericResponse<Habitante>> guardarHabitante(@Body Habitante h);

}
