package idat.com.administracion.app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import idat.com.administracion.app.Entidades.GenericResponse;
import idat.com.administracion.app.Entidades.Habitante;
import idat.com.administracion.app.Entidades.UsuarioMySql;
import idat.com.administracion.app.api.ConfigApi;
import idat.com.administracion.app.api.HabitanteApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabitanteRepository {

    private static HabitanteRepository repository;
    public static HabitanteApi api;

    public static HabitanteRepository getInstance(){
        if (repository == null){
            repository = new HabitanteRepository();
        }
        return repository;
    }

    private HabitanteRepository(){
        api = ConfigApi.getHabitanteApi();
    }

    public LiveData<GenericResponse<Habitante>> guardarHabitante (Habitante h){
        final MutableLiveData<GenericResponse<Habitante>> mld = new MutableLiveData<>();
        this.api.guardarHabitante(h).enqueue(new Callback<GenericResponse<Habitante>>() {
            @Override
            public void onResponse(Call<GenericResponse<Habitante>> call, Response<GenericResponse<Habitante>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Habitante>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Se ha producido un error : " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
