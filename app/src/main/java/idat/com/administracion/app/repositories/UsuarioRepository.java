package idat.com.administracion.app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import idat.com.administracion.app.Entidades.GenericResponse;
import idat.com.administracion.app.Entidades.UsuarioMySql;
import idat.com.administracion.app.api.ConfigApi;
import idat.com.administracion.app.api.UsuarioApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {

    private static UsuarioRepository repository;
    private final UsuarioApi api;


    public UsuarioRepository() {
        this.api = ConfigApi.getUsuarioApi();
    }

    public static UsuarioRepository getInstance(){
        if (repository == null){
            repository = new UsuarioRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<UsuarioMySql>> login(String email, String password){
        final MutableLiveData<GenericResponse<UsuarioMySql>> mld = new MutableLiveData<>();
        this.api.login(email,password).enqueue(new Callback<GenericResponse<UsuarioMySql>>() {
            @Override
            public void onResponse(Call<GenericResponse<UsuarioMySql>> call, Response<GenericResponse<UsuarioMySql>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<UsuarioMySql>> call, Throwable t) {
                mld.setValue(new GenericResponse());
                System.out.println(t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }

    public LiveData<GenericResponse<UsuarioMySql>> save(UsuarioMySql umsql){
        final MutableLiveData<GenericResponse<UsuarioMySql>> mld = new MutableLiveData<>();
        this.api.save(umsql).enqueue(new Callback<GenericResponse<UsuarioMySql>>() {
            @Override
            public void onResponse(Call<GenericResponse<UsuarioMySql>> call, Response<GenericResponse<UsuarioMySql>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<UsuarioMySql>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Se ha producido un error: "+ t.getMessage());
            }
        });
        return mld;
    }
}
